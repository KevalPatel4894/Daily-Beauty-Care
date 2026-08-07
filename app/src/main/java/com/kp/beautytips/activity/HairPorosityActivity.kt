package com.kp.beautytips.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton
import com.kp.beautytips.R
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class HairPorosityActivity : BaseActivity() {

    private lateinit var rgFloatTest: RadioGroup
    private lateinit var rgDryingTime: RadioGroup
    private lateinit var rgProductAbsorption: RadioGroup
    private lateinit var cardPorosityResult: View
    private lateinit var txtResultTitle: TextView
    private lateinit var txtResultDesc: TextView
    private lateinit var txtResultRemedies: TextView
    private lateinit var txtResultAvoid: TextView

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hair_porosity)
        initViews()
    }

    private fun initViews() {
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.setNavigationOnClickListener { onBackPressed() }

        rgFloatTest = findViewById(R.id.rgFloatTest)
        rgDryingTime = findViewById(R.id.rgDryingTime)
        rgProductAbsorption = findViewById(R.id.rgProductAbsorption)
        cardPorosityResult = findViewById(R.id.cardPorosityResult)
        txtResultTitle = findViewById(R.id.txtResultTitle)
        txtResultDesc = findViewById(R.id.txtResultDesc)
        txtResultRemedies = findViewById(R.id.txtResultRemedies)
        txtResultAvoid = findViewById(R.id.txtResultAvoid)

        val btnCalculate = findViewById<MaterialButton>(R.id.btnCalculatePorosity)
        btnCalculate.setOnClickListener {
            calculatePorosity()
        }

        val adContainer = findViewById<ViewGroup>(R.id.ad_view)
        AdManager.loadBanner(this, adContainer, getString(R.string.banner_home_footer))
    }

    private fun calculatePorosity() {
        val floatId = rgFloatTest.checkedRadioButtonId
        val dryId = rgDryingTime.checkedRadioButtonId
        val absId = rgProductAbsorption.checkedRadioButtonId

        // Score system: Low = 1 point, Normal = 2 points, High = 3 points
        var totalScore = 0

        totalScore += when (floatId) {
            R.id.rbFloatTop -> 1
            R.id.rbFloatMiddle -> 2
            else -> 3
        }

        totalScore += when (dryId) {
            R.id.rbDrySlow -> 1
            R.id.rbDryNormal -> 2
            else -> 3
        }

        totalScore += when (absId) {
            R.id.rbProductSits -> 1
            R.id.rbProductAbsorbs -> 2
            else -> 3
        }

        when {
            totalScore <= 4 -> {
                displayResult(
                    titleRes = R.string.porosity_low_title,
                    descRes = R.string.porosity_low_desc,
                    remediesRes = R.string.porosity_low_remedies,
                    avoidRes = R.string.porosity_low_avoid
                )
            }
            totalScore in 5..7 -> {
                displayResult(
                    titleRes = R.string.porosity_normal_title,
                    descRes = R.string.porosity_normal_desc,
                    remediesRes = R.string.porosity_normal_remedies,
                    avoidRes = R.string.porosity_normal_avoid
                )
            }
            else -> {
                displayResult(
                    titleRes = R.string.porosity_high_title,
                    descRes = R.string.porosity_high_desc,
                    remediesRes = R.string.porosity_high_remedies,
                    avoidRes = R.string.porosity_high_avoid
                )
            }
        }
    }

    private fun displayResult(
        titleRes: Int,
        descRes: Int,
        remediesRes: Int,
        avoidRes: Int
    ) {
        cardPorosityResult.visibility = View.VISIBLE
        txtResultTitle.text = getString(titleRes)
        txtResultDesc.text = getString(descRes)
        txtResultRemedies.text = getString(remediesRes)
        txtResultAvoid.text = getString(avoidRes)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(this)
        return true
    }
}
