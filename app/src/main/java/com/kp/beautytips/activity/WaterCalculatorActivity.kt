package com.kp.beautytips.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.gms.ads.*
import com.kp.beautytips.R
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.util.Locale

class WaterCalculatorActivity : BaseActivity() {

    private lateinit var txtTitle: AppCompatTextView
    private lateinit var edtWeight: EditText
    private lateinit var rgWeightUnit: RadioGroup
    private lateinit var rgClimate: RadioGroup
    private lateinit var rgActivity: RadioGroup
    private lateinit var btnCalculate: View
    private lateinit var cardResult: View
    private lateinit var txtResultGoal: AppCompatTextView
    private lateinit var btnSetGoal: View

    private var adRequest: AdRequest? = null
    private var calculatedGlasses: Int = 8

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_calculator)
        init()
    }

    private fun init() {
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        txtTitle = findViewById(R.id.txtTitle)
        txtTitle.text = getString(R.string.water_intake_calculator)

        // Bind Views
        edtWeight = findViewById(R.id.edtWeight)
        rgWeightUnit = findViewById(R.id.rgWeightUnit)
        rgClimate = findViewById(R.id.rgClimate)
        rgActivity = findViewById(R.id.rgActivity)
        btnCalculate = findViewById(R.id.btnCalculate)
        cardResult = findViewById(R.id.cardResult)
        txtResultGoal = findViewById(R.id.txtResultGoal)
        btnSetGoal = findViewById(R.id.btnSetGoal)

        // Ads
        adRequest = AdRequest.Builder().build()
        val adContainer = findViewById<ViewGroup>(R.id.ad_view)
        AdManager.loadBanner(this, adContainer, getString(R.string.banner_home_footer))

        btnCalculate.setOnClickListener {
            performCalculation()
        }

        btnSetGoal.setOnClickListener {
            saveCalculatedGoal()
        }
    }

    private fun performCalculation() {
        val weightStr = edtWeight.text.toString().trim()
        if (weightStr.isEmpty()) {
            Toast.makeText(this, getString(R.string.weight_hint), Toast.LENGTH_SHORT).show()
            return
        }

        val weight = weightStr.toDoubleOrNull()
        if (weight == null || weight <= 0) {
            Toast.makeText(this, "Please enter a valid weight", Toast.LENGTH_SHORT).show()
            return
        }

        // Convert to kg if in lbs
        val isLbs = findViewById<RadioButton>(R.id.rbLbs).isChecked
        val weightInKg = if (isLbs) weight * 0.453592 else weight

        // Base requirement: 35ml per kg of body weight
        var waterInMl = weightInKg * 35.0

        // Climate Adjustment
        val selectedClimateId = rgClimate.checkedRadioButtonId
        when (selectedClimateId) {
            R.id.rbHot -> waterInMl += 500.0
            R.id.rbCold -> waterInMl -= 250.0
        }

        // Activity Level Adjustment
        val selectedActivityId = rgActivity.checkedRadioButtonId
        when (selectedActivityId) {
            R.id.rbMedium -> waterInMl += 350.0
            R.id.rbHigh -> waterInMl += 700.0
        }

        // Convert to glasses (1 glass = 250 ml)
        var glasses = Math.round(waterInMl / 250.0).toInt()

        // Clamp values between 6 and 20 glasses
        if (glasses < 6) glasses = 6
        if (glasses > 20) glasses = 20

        calculatedGlasses = glasses
        val liters = glasses * 0.25

        txtResultGoal.text = "$glasses " + getString(R.string.glasses) + String.format(Locale.US, " (%.1fL)", liters)
        cardResult.visibility = View.VISIBLE
    }

    private fun saveCalculatedGoal() {
        try {
            val waterPrefs = getSharedPreferences("water_tracker_prefs", Context.MODE_PRIVATE)
            waterPrefs.edit().putInt("water_water_goal", calculatedGlasses).apply()
            Toast.makeText(this, getString(R.string.water_goal_saved), Toast.LENGTH_SHORT).show()
            finish()
            AppUtils.finishFromLeftToRight(this)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error saving goal", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(activity)
        return true
    }
}
