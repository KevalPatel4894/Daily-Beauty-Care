package com.kp.beautytips.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.AppCompatRadioButton
import com.google.android.gms.ads.*
import com.kp.beautytips.R
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class SkinTestActivity : BaseActivity() {

    private lateinit var txtTitle: AppCompatTextView
    private lateinit var txtProgress: AppCompatTextView
    private lateinit var txtQuestion: AppCompatTextView
    private lateinit var rgAnswers: RadioGroup
    private lateinit var rbAnswerA: AppCompatRadioButton
    private lateinit var rbAnswerB: AppCompatRadioButton
    private lateinit var rbAnswerC: AppCompatRadioButton
    private lateinit var rbAnswerD: AppCompatRadioButton
    private lateinit var rbAnswerE: AppCompatRadioButton
    private lateinit var btnNext: View

    private lateinit var cardQuiz: View
    private lateinit var cardResult: View
    private lateinit var imgSkinResult: ImageView
    private lateinit var txtResultType: AppCompatTextView
    private lateinit var txtResultDesc: AppCompatTextView
    private lateinit var btnExploreRemedies: View

    private var adRequest: AdRequest? = null
    private var currentQuestionIndex = 0
    private val selectedAnswers = ArrayList<String>()

    // Questions and Options Structure
    private class Question(
        val questionResId: Int,
        val optionAResId: Int,
        val optionBResId: Int,
        val optionCResId: Int,
        val optionDResId: Int,
        val optionEResId: Int
    )

    private val questions = arrayOf(
        Question(R.string.quiz_q1, R.string.quiz_q1_a, R.string.quiz_q1_b, R.string.quiz_q1_c, R.string.quiz_q1_d, R.string.quiz_q1_e),
        Question(R.string.quiz_q2, R.string.quiz_q2_a, R.string.quiz_q2_b, R.string.quiz_q2_c, R.string.quiz_q2_d, R.string.quiz_q2_e),
        Question(R.string.quiz_q3, R.string.quiz_q3_a, R.string.quiz_q3_b, R.string.quiz_q3_c, R.string.quiz_q3_d, R.string.quiz_q3_e),
        Question(R.string.quiz_q4, R.string.quiz_q4_a, R.string.quiz_q4_b, R.string.quiz_q4_c, R.string.quiz_q4_d, R.string.quiz_q4_e),
        Question(R.string.quiz_q5, R.string.quiz_q5_a, R.string.quiz_q5_b, R.string.quiz_q5_c, R.string.quiz_q5_d, R.string.quiz_q5_e)
    )

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skin_test)
        init()
    }

    private fun init() {
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        txtTitle = findViewById(R.id.txtTitle)
        txtTitle.text = getString(R.string.skin_type_test)

        // Bind Views
        txtProgress = findViewById(R.id.txtProgress)
        txtQuestion = findViewById(R.id.txtQuestion)
        rgAnswers = findViewById(R.id.rgAnswers)
        rbAnswerA = findViewById(R.id.rbAnswerA)
        rbAnswerB = findViewById(R.id.rbAnswerB)
        rbAnswerC = findViewById(R.id.rbAnswerC)
        rbAnswerD = findViewById(R.id.rbAnswerD)
        rbAnswerE = findViewById(R.id.rbAnswerE)
        btnNext = findViewById(R.id.btnNext)

        cardQuiz = findViewById(R.id.cardQuiz)
        cardResult = findViewById(R.id.cardResult)
        imgSkinResult = findViewById(R.id.imgSkinResult)
        txtResultType = findViewById(R.id.txtResultType)
        txtResultDesc = findViewById(R.id.txtResultDesc)
        btnExploreRemedies = findViewById(R.id.btnExploreRemedies)

        // Ads
        adRequest = AdRequest.Builder().build()
        val adContainer = findViewById<View>(R.id.ad_view)
        val mAdView = AdView(this)
        mAdView.setAdSize(AdSize.BANNER)
        mAdView.adUnitId = getString(R.string.banner_home_footer)
        (adContainer as RelativeLayout).addView(mAdView)
        mAdView.loadAd(adRequest!!)

        displayQuestion()

        btnNext.setOnClickListener {
            handleNextClick()
        }
    }

    private fun displayQuestion() {
        if (currentQuestionIndex < questions.size) {
            val q = questions[currentQuestionIndex]
            txtProgress.text = String.format(getString(R.string.quiz_progress), currentQuestionIndex + 1)
            txtQuestion.text = getString(q.questionResId)
            rbAnswerA.text = getString(q.optionAResId)
            rbAnswerB.text = getString(q.optionBResId)
            rbAnswerC.text = getString(q.optionCResId)
            rbAnswerD.text = getString(q.optionDResId)
            rbAnswerE.text = getString(q.optionEResId)
            rgAnswers.clearCheck()

            if (currentQuestionIndex == questions.size - 1) {
                (btnNext as? android.widget.Button)?.text = getString(R.string.calculate)
            }
        }
    }

    private fun handleNextClick() {
        val checkedId = rgAnswers.checkedRadioButtonId
        if (checkedId == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            return
        }

        val answer = when (checkedId) {
            R.id.rbAnswerA -> "A"
            R.id.rbAnswerB -> "B"
            R.id.rbAnswerC -> "C"
            R.id.rbAnswerD -> "D"
            else -> "E"
        }

        selectedAnswers.add(answer)

        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            displayQuestion()
        } else {
            showResult()
        }
    }

    private fun showResult() {
        cardQuiz.visibility = View.GONE

        // Tally scores
        val counts = mutableMapOf("A" to 0, "B" to 0, "C" to 0, "D" to 0, "E" to 0)
        for (ans in selectedAnswers) {
            counts[ans] = counts.getOrDefault(ans, 0) + 1
        }

        // Determine dominant option
        var dominant = "E"
        var maxCount = -1
        for ((key, value) in counts) {
            if (value > maxCount) {
                maxCount = value
                dominant = key
            }
        }

        // Map dominant option to Skin Type
        // A -> Dry, B -> Combination, C -> Oily, D -> Sensitive, E -> Normal
        val skinTypeKey = when (dominant) {
            "A" -> "Dry"
            "B" -> "Combination"
            "C" -> "Oily"
            "D" -> "Sensitive"
            else -> "Normal"
        }
        val prefs = getSharedPreferences("BeautyProfile", Context.MODE_PRIVATE)
        prefs.edit().putString("profile_skin_type", skinTypeKey).putBoolean("profile_is_configured", true).apply()

        when (dominant) {
            "A" -> {
                txtResultType.text = getString(R.string.skin_type_result) + ": " + getString(R.string.skin_type_dry)
                txtResultDesc.text = getString(R.string.dry_skin_desc)
                imgSkinResult.setImageResource(R.drawable.dry)
                btnExploreRemedies.setOnClickListener {
                    Intent(this, ListActivity::class.java).also {
                        it.putExtra("position", 0)
                        it.putExtra("categoryName", getString(R.string.glowingskin_title))
                        startActivity(it)
                        AppUtils.startFromRightToLeft(this)
                    }
                }
            }
            "B" -> {
                txtResultType.text = getString(R.string.skin_type_result) + ": " + getString(R.string.skin_type_combination)
                txtResultDesc.text = getString(R.string.combination_skin_desc)
                imgSkinResult.setImageResource(R.drawable.combination)
                btnExploreRemedies.setOnClickListener {
                    Intent(this, ListActivity::class.java).also {
                        it.putExtra("position", 3)
                        it.putExtra("categoryName", getString(R.string.blackheads_title))
                        startActivity(it)
                        AppUtils.startFromRightToLeft(this)
                    }
                }
            }
            "C" -> {
                txtResultType.text = getString(R.string.skin_type_result) + ": " + getString(R.string.skin_type_oily)
                txtResultDesc.text = getString(R.string.oily_skin_desc)
                imgSkinResult.setImageResource(R.drawable.oily)
                btnExploreRemedies.setOnClickListener {
                    Intent(this, ListActivity::class.java).also {
                        it.putExtra("position", 1)
                        it.putExtra("categoryName", getString(R.string.acne_title))
                        startActivity(it)
                        AppUtils.startFromRightToLeft(this)
                    }
                }
            }
            "D" -> {
                txtResultType.text = getString(R.string.skin_type_result) + ": " + getString(R.string.skin_type_sensitive)
                txtResultDesc.text = getString(R.string.sensitive_skin_desc)
                imgSkinResult.setImageResource(R.drawable.sensitive)
                btnExploreRemedies.setOnClickListener {
                    Intent(this, ListActivity::class.java).also {
                        it.putExtra("position", 1)
                        it.putExtra("categoryName", getString(R.string.unevenskin_title))
                        startActivity(it)
                        AppUtils.startFromRightToLeft(this)
                    }
                }
            }
            else -> {
                txtResultType.text = getString(R.string.skin_type_result) + ": " + getString(R.string.skin_type_normal)
                txtResultDesc.text = getString(R.string.skin_type_normal_desc)
                imgSkinResult.setImageResource(R.drawable.normal)
                btnExploreRemedies.setOnClickListener {
                    Intent(this, SubCategoryActivity::class.java).also {
                        it.putExtra("position", 0)
                        it.putExtra("categoryName", getString(R.string.face))
                        startActivity(it)
                        AppUtils.startFromRightToLeft(this)
                    }
                }
            }
        }

        cardResult.visibility = View.VISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(activity)
        return true
    }
}
