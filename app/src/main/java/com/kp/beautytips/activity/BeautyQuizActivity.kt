package com.kp.beautytips.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton
import com.kp.beautytips.R
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper

data class BeautyQuizQuestion(
    val questionResId: Int,
    val isTrue: Boolean,
    val explanationResId: Int
)

class BeautyQuizActivity : BaseActivity() {

    private lateinit var layoutQuizContent: LinearLayout
    private lateinit var layoutQuizResult: LinearLayout
    private lateinit var txtQuestionCounter: TextView
    private lateinit var progressQuiz: ProgressBar
    private lateinit var txtQuestionText: TextView
    private lateinit var layoutExplanation: LinearLayout
    private lateinit var txtAnswerStatus: TextView
    private lateinit var txtExplanation: TextView
    private lateinit var layoutAnswerButtons: LinearLayout
    private lateinit var btnTrue: MaterialButton
    private lateinit var btnFalse: MaterialButton
    private lateinit var btnNextQuestion: MaterialButton

    private lateinit var txtResultScore: TextView
    private lateinit var txtResultBadge: TextView
    private lateinit var btnRestartQuiz: MaterialButton
    private lateinit var btnShareScore: MaterialButton

    private val questions = listOf(
        BeautyQuizQuestion(R.string.quiz_myth_1_q, false, R.string.quiz_myth_1_ans),
        BeautyQuizQuestion(R.string.quiz_myth_2_q, false, R.string.quiz_myth_2_ans),
        BeautyQuizQuestion(R.string.quiz_myth_3_q, true, R.string.quiz_myth_3_ans),
        BeautyQuizQuestion(R.string.quiz_myth_4_q, true, R.string.quiz_myth_4_ans),
        BeautyQuizQuestion(R.string.quiz_myth_5_q, false, R.string.quiz_myth_5_ans)
    )

    private var currentQuestionIndex = 0
    private var score = 0
    private var isAnswered = false

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beauty_quiz)
        initViews()
        loadQuestion(0)
    }

    private fun initViews() {
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.setNavigationOnClickListener { onBackPressed() }

        val txtTabTitle = findViewById<TextView>(R.id.txtTabTitle)
        txtTabTitle.text = getString(R.string.beauty_quiz_title)

        layoutQuizContent = findViewById(R.id.layoutQuizContent)
        layoutQuizResult = findViewById(R.id.layoutQuizResult)
        txtQuestionCounter = findViewById(R.id.txtQuestionCounter)
        progressQuiz = findViewById(R.id.progressQuiz)
        txtQuestionText = findViewById(R.id.txtQuestionText)
        layoutExplanation = findViewById(R.id.layoutExplanation)
        txtAnswerStatus = findViewById(R.id.txtAnswerStatus)
        txtExplanation = findViewById(R.id.txtExplanation)
        layoutAnswerButtons = findViewById(R.id.layoutAnswerButtons)
        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        btnNextQuestion = findViewById(R.id.btnNextQuestion)

        txtResultScore = findViewById(R.id.txtResultScore)
        txtResultBadge = findViewById(R.id.txtResultBadge)
        btnRestartQuiz = findViewById(R.id.btnRestartQuiz)
        btnShareScore = findViewById(R.id.btnShareScore)

        btnTrue.setOnClickListener { checkAnswer(true) }
        btnFalse.setOnClickListener { checkAnswer(false) }

        btnNextQuestion.setOnClickListener {
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                loadQuestion(currentQuestionIndex)
            } else {
                showResults()
            }
        }

        btnRestartQuiz.setOnClickListener {
            currentQuestionIndex = 0
            score = 0
            layoutQuizContent.visibility = View.VISIBLE
            layoutQuizResult.visibility = View.GONE
            loadQuestion(0)
        }

        btnShareScore.setOnClickListener {
            shareScore()
        }

        val adContainer = findViewById<ViewGroup>(R.id.ad_view)
        AdManager.loadBanner(this, adContainer, getString(R.string.banner_home_footer))
    }

    private fun loadQuestion(index: Int) {
        isAnswered = false
        val item = questions[index]
        txtQuestionCounter.text = getString(R.string.quiz_question_counter, index + 1, questions.size)
        progressQuiz.progress = index + 1
        txtQuestionText.text = getString(item.questionResId)

        layoutExplanation.visibility = View.GONE
        btnNextQuestion.visibility = View.GONE
        layoutAnswerButtons.visibility = View.VISIBLE
        btnTrue.isEnabled = true
        btnFalse.isEnabled = true
    }

    private fun checkAnswer(userChoice: Boolean) {
        if (isAnswered) return
        isAnswered = true

        val item = questions[currentQuestionIndex]
        val isCorrect = (userChoice == item.isTrue)

        if (isCorrect) {
            score++
            txtAnswerStatus.text = "Correct! 🎉"
            txtAnswerStatus.setTextColor(0xFF16A34A.toInt())
            layoutExplanation.setBackgroundColor(0xFFF0FDF4.toInt())
        } else {
            txtAnswerStatus.text = "Incorrect ❌"
            txtAnswerStatus.setTextColor(0xFFDC2626.toInt())
            layoutExplanation.setBackgroundColor(0xFFFEF2F2.toInt())
        }

        txtExplanation.text = getString(item.explanationResId)
        layoutExplanation.visibility = View.VISIBLE
        btnTrue.isEnabled = false
        btnFalse.isEnabled = false
        btnNextQuestion.visibility = View.VISIBLE
    }

    private fun showResults() {
        layoutQuizContent.visibility = View.GONE
        layoutQuizResult.visibility = View.VISIBLE

        txtResultScore.text = getString(R.string.quiz_score_result, score, questions.size)
        txtResultBadge.text = when {
            score >= 4 -> getString(R.string.quiz_result_expert)
            score >= 3 -> getString(R.string.quiz_result_good)
            else -> getString(R.string.quiz_result_learn)
        }
    }

    private fun shareScore() {
        val shareMsg = "I scored $score/${questions.size} on the Beauty Myths Quiz in Daily Beauty Care! Test your skincare knowledge too! 🌟"
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareMsg)
        }
        startActivity(Intent.createChooser(shareIntent, getString(R.string.btn_share_score)))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(this)
        return true
    }
}
