package com.kp.beautytips.activity

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import com.kp.beautytips.R
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import com.kp.beautytips.utils.Constants
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import io.github.inflationx.viewpump.ViewPumpContextWrapper


class SettingActivity : BaseActivity() {
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
        setContentView(R.layout.activity_setting)
        init()
    }

    private fun init() {
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        txtTabTitle = findViewById(R.id.txtTabTitle)
        txtTabTitle.text = getString(R.string.settings)

        adRequest = AdRequest.Builder()
            .build()

        val adContainer = findViewById<View>(R.id.ad_view)
        val mAdView = AdView(this)
        mAdView.setAdSize(AdSize.BANNER)
        mAdView.adUnitId = getString(R.string.banner_home_footer)
        (adContainer as RelativeLayout).addView(mAdView)
        mAdView.loadAd(adRequest!!)

        loadFullScreenAds()

        val rlRateUs = findViewById<View>(R.id.rlRateUs)
        rlRateUs.setOnClickListener {
            val uri = Uri.parse("market://details?id=$packageName")
            val goToMarket = Intent(Intent.ACTION_VIEW, uri)
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
            try {
                startActivity(goToMarket)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
                    )
                )
            }
        }

        val rlMoreApp = findViewById<View>(R.id.rlMoreApp)
        rlMoreApp.setOnClickListener {
            val stringPub = "KpAppslab"
            val uri1 = Uri.parse("market://search?q=pub:$stringPub")
            val goToMarket1 = Intent(Intent.ACTION_VIEW, uri1)
            goToMarket1.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
            try {
                startActivity(goToMarket1)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
                    )
                )
            }
        }

        val rlShare = findViewById<View>(R.id.rlShare)
        rlShare.setOnClickListener {
            val text = getString(R.string.app_share) + ": http://play.google.com/store/apps/details?id=" + packageName
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, text)
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share)))
        }

        val rlFavorites = findViewById<View>(R.id.rlFavorites)
        rlFavorites.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            intent.putExtra("position", -1)
            intent.putExtra("categoryName", getString(R.string.favorite_tips))
            startActivity(intent)
            AppUtils.startFromRightToLeft(this)
        }

        val rlCustomTips = findViewById<View>(R.id.rlCustomTips)
        rlCustomTips.setOnClickListener {
            Intent(this, CustomTipsActivity::class.java).also {
                startActivity(it)
                AppUtils.startFromRightToLeft(this)
            }
        }

        val rlReminders = findViewById<View>(R.id.rlReminders)
        rlReminders.setOnClickListener {
            Intent(this, ReminderActivity::class.java).also {
                startActivity(it)
                AppUtils.startFromRightToLeft(this)
            }
        }

        val rlLanguages = findViewById<View>(R.id.rlLanguages)
        rlLanguages.setOnClickListener {
            Intent(this, LanguagesActivity::class.java).also {
                startActivity(it)
                AppUtils.startFromRightToLeft(this)
            }
        }

        val rlDiary = findViewById<View>(R.id.rlDiary)
        rlDiary.setOnClickListener {
            Intent(this, DiaryActivity::class.java).also {
                startActivity(it)
                AppUtils.startFromRightToLeft(this)
            }
        }

        val rlProfile = findViewById<View>(R.id.rlProfile)
        rlProfile.setOnClickListener {
            Intent(this, ProfileActivity::class.java).also {
                startActivity(it)
                AppUtils.startFromRightToLeft(this)
            }
        }

        val rlAchievements = findViewById<View>(R.id.rlAchievements)
        rlAchievements.setOnClickListener {
            Intent(this, BadgesActivity::class.java).also {
                startActivity(it)
                AppUtils.startFromRightToLeft(this)
            }
        }

        val rlIngredientGlossary = findViewById<View>(R.id.rlIngredientGlossary)
        rlIngredientGlossary.setOnClickListener {
            Intent(this, IngredientGlossaryActivity::class.java).also {
                startActivity(it)
                AppUtils.startFromRightToLeft(this)
            }
        }

        val rlCalculators = findViewById<View>(R.id.rlCalculators)
        rlCalculators.setOnClickListener {
            Intent(this, CalculatorActivity::class.java).also {
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


    private fun loadFullScreenAds() {
        val sharedPreferences =
            getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if (sharedPreferences.getInt(
                Constants.FULL_ADS_COUNT,
                0
            ) == Constants.ADS_FULL_COUNTER
        ) {
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