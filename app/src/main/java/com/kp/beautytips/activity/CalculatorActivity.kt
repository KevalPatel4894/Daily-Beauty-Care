package com.kp.beautytips.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.gms.ads.*
import com.kp.beautytips.R
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class CalculatorActivity : BaseActivity() {

    private lateinit var txtTitle: AppCompatTextView
    private var adRequest: AdRequest? = null

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        init()
    }

    private fun init() {
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        txtTitle = findViewById(R.id.txtTitle)
        txtTitle.text = getString(R.string.beauty_calculators)

        // Banner Ads Setup
        adRequest = AdRequest.Builder().build()
        val adContainer = findViewById<View>(R.id.ad_view)
        val mAdView = AdView(this)
        mAdView.setAdSize(AdSize.BANNER)
        mAdView.adUnitId = getString(R.string.banner_home_footer)
        (adContainer as RelativeLayout).addView(mAdView)
        mAdView.loadAd(adRequest!!)

        // Clicks Setup
        val cardWaterCalc = findViewById<View>(R.id.cardWaterCalc)
        cardWaterCalc.setOnClickListener {
            Intent(this, WaterCalculatorActivity::class.java).also {
                startActivity(it)
                AppUtils.startFromRightToLeft(this)
            }
        }

        val cardSkinCalc = findViewById<View>(R.id.cardSkinCalc)
        cardSkinCalc.setOnClickListener {
            Intent(this, SkinTestActivity::class.java).also {
                startActivity(it)
                AppUtils.startFromRightToLeft(this)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(activity)
        return true
    }
}
