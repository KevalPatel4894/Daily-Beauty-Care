package com.kp.beautytips.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.kp.beautytips.R
import com.kp.beautytips.adapter.LanguagesAdapter
import com.kp.beautytips.model.LanguageModel
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import com.kp.beautytips.utils.Constants
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import io.github.inflationx.viewpump.ViewPumpContextWrapper


class LanguagesActivity : BaseActivity() {
    private var adRequest: AdRequest? = null
    private var mInterstitialAd: InterstitialAd? = null
    private var mAdIsLoading: Boolean = false
    private lateinit var txtTabTitle: AppCompatTextView

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_languages)
        init()
    }

    private fun init() {
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        val rvLanguages = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rvLanguages)

        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        txtTabTitle = findViewById(R.id.txtTabTitle)
        txtTabTitle.text = getString(R.string.languages)

        adRequest = AdRequest.Builder()
            .build()

        val adContainer = findViewById<View>(R.id.ad_view)
        val mAdView = AdView(this)
        mAdView.setAdSize(AdSize.BANNER)
        mAdView.adUnitId = getString(R.string.banner_home_footer)
        (adContainer as RelativeLayout).addView(mAdView)
        mAdView.loadAd(adRequest!!)

        loadFullScreenAds()

        val languages = ArrayList<LanguageModel>()
        val languageModel = LanguageModel()
        languageModel.langName = getString(R.string.english)
        languageModel.isSelected = true
        languageModel.langCode = "en"
        languages.add(languageModel)
        val languageModel1 = LanguageModel()
        languageModel1.langName = getString(R.string.hindi)
        languageModel1.isSelected = false
        languageModel1.langCode = "hi"
        languages.add(languageModel1)
        val languageModel2 = LanguageModel()
        languageModel2.langName = "Spanish"
        languageModel2.isSelected = false
        languageModel2.langCode = "es"
        languages.add(languageModel2)
        val languageModel3 = LanguageModel()
        languageModel3.langName = "Franch"
        languageModel3.isSelected = false
        languageModel3.langCode = "fr"
        languages.add(languageModel3)
        val languageModel4 = LanguageModel()
        languageModel4.langName = "Japanese"
        languageModel4.isSelected = false
        languageModel4.langCode = "ja"
        languages.add(languageModel4)
        val languageModel5 = LanguageModel()
        languageModel5.langName = "Korean"
        languageModel5.isSelected = false
        languageModel5.langCode = "ko"
        languages.add(languageModel5)
        val languageModel6 = LanguageModel()
        languageModel6.langName = "Russian"
        languageModel6.isSelected = false
        languageModel6.langCode = "ru"
        languages.add(languageModel6)
        val languageModel7 = LanguageModel()
        languageModel7.langName = "Português"
        languageModel7.isSelected = false
        languageModel7.langCode = "pt"
        languages.add(languageModel7)
        val languageModel8 = LanguageModel()
        languageModel8.langName = "German"
        languageModel8.isSelected = false
        languageModel8.langCode = "de"
        languages.add(languageModel8)

        rvLanguages.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvLanguages.adapter = LanguagesAdapter(languages, this@LanguagesActivity)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(activity)
        return true
    }

    fun reCreate() {
        Intent(this, MainActivity::class.java).also {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
            AppUtils.startFromRightToLeft(this)
        }
    }


    private fun loadFullScreenAds() {
        val sharedPreferences =
            getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if (sharedPreferences.getInt(Constants.FULL_ADS_COUNT, 0) == Constants.ADS_FULL_COUNTER) {
            editor.putInt(Constants.FULL_ADS_COUNT, 0).apply()
            if (!mAdIsLoading && mInterstitialAd == null) {
                mAdIsLoading = true
                loadAd()
            }
        } else {
            var getCount = sharedPreferences.getInt(Constants.FULL_ADS_COUNT, 0)
            getCount++
            editor.putInt(Constants.FULL_ADS_COUNT, getCount).apply()
        }
    }

    private fun loadAd() {
        InterstitialAd.load(
            this, getString(R.string.interstitial_full_screen), adRequest!!,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    AppUtils.logI(adError.message)
                    mInterstitialAd = null
                    mAdIsLoading = false
                    val error = "domain: ${adError.domain}, code: ${adError.code}, " +
                            "message: ${adError.message}"
                    AppUtils.logI("onAdFailedToLoad() with error $error")
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    AppUtils.logI("Ad was loaded.")
                    mInterstitialAd = interstitialAd
                    mAdIsLoading = false
                    AppUtils.logI("onAdLoaded()")
                    showInterstitial()
                }
            }
        )
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        }
    }

    private fun showInterstitial() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    AppUtils.logI("Ad was dismissed.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mInterstitialAd = null
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    AppUtils.logI("Ad failed to show.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mInterstitialAd = null
                }

                override fun onAdShowedFullScreenContent() {
                    AppUtils.logI("Ad showed fullscreen content.")
                    // Called when ad is dismissed.
                }
            }
            mInterstitialAd?.show(this)
        }
    }

}