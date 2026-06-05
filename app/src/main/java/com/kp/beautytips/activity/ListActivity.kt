package com.kp.beautytips.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.viewpager.widget.ViewPager
import com.kp.beautytips.R
import com.kp.beautytips.adapter.ViewPagerAdapter
import com.kp.beautytips.fragment.DietFragment
import com.kp.beautytips.fragment.ExerciseFragment
import com.kp.beautytips.fragment.RemedyFragment
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import com.kp.beautytips.utils.Constants
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import io.github.inflationx.viewpump.ViewPumpContextWrapper



class ListActivity : BaseActivity() {
    private var position: Int = 0
    private var viewPagerPosition: Int = 0
    private var categoryName: String = ""
    private var viewPagerAdapter: ViewPagerAdapter? = null
    private var adRequest: AdRequest? = null
    private var mInterstitialAd: InterstitialAd? = null
    private var mAdIsLoading: Boolean = false

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        init()
    }

    private fun init() {
        val txtTitle = findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtTitle)
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        val view_pager = findViewById<androidx.viewpager.widget.ViewPager>(R.id.view_pager)
        val top_navigation_constraint = findViewById<com.gauravk.bubblenavigation.BubbleNavigationConstraintView>(R.id.top_navigation_constraint)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            position = bundle.getInt("position")
            categoryName = bundle.getString("categoryName").toString()
            txtTitle.text = categoryName
        }
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        adRequest = AdRequest.Builder()
            .build()

        val adContainer = findViewById<View>(R.id.ad_view)
        val mAdView = AdView(this)
        mAdView.setAdSize(AdSize.BANNER)
        mAdView.adUnitId = getString(R.string.banner_home_footer)
        (adContainer as RelativeLayout).addView(mAdView)
        mAdView.loadAd(adRequest!!)

        loadFullScreenAds()

        // setting up the adapter
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        if (categoryName == "Favorite Tips") {
            top_navigation_constraint.visibility = View.GONE
            viewPagerAdapter!!.add(RemedyFragment(categoryName), "Favorites")
        } else {
            // add the fragments
            viewPagerAdapter!!.add(RemedyFragment(categoryName), "Remedy")
            viewPagerAdapter!!.add(DietFragment(categoryName), "Diet")
            viewPagerAdapter!!.add(ExerciseFragment(categoryName), "Exercise")
        }
        view_pager.adapter = viewPagerAdapter
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                top_navigation_constraint.setCurrentActiveItem(p0)
                viewPagerPosition = p0
            }

        })

        top_navigation_constraint.setNavigationChangeListener { _, position ->
            view_pager.setCurrentItem(position, true)
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