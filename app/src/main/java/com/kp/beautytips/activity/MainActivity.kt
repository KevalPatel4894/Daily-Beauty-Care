package com.kp.beautytips.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.kp.beautytips.R
import com.kp.beautytips.adapter.CategoryAdapter
import com.kp.beautytips.model.CategoryModel
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import com.kp.beautytips.utils.Constants
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import io.github.inflationx.viewpump.ViewPumpContextWrapper



class MainActivity : BaseActivity(), CategoryAdapter.OnItemClick {
    private var adRequest: AdRequest? = null
    private var mInterstitialAd: InterstitialAd? = null
    private var mAdIsLoading: Boolean = false
    private lateinit var toolbar: Toolbar


    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        val rvCategory = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rvCategory)
        val imgSetting = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.imgSetting)

        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        adRequest = AdRequest.Builder()
            .build()

        val adContainer = findViewById<View>(R.id.ad_view)
        val mAdView = AdView(this)
        mAdView.setAdSize(AdSize.BANNER)
        mAdView.adUnitId = getString(R.string.banner_home_footer)
        (adContainer as RelativeLayout).addView(mAdView)
        mAdView.loadAd(adRequest!!)

        loadFullScreenAds()

        val category = ArrayList<CategoryModel>()
        val categoryModel = CategoryModel()
        categoryModel.categoryName = getString(R.string.face)
        categoryModel.colorCode = "#F0FBFF"
        categoryModel.viewColorCode = "#BEF0F7"
        categoryModel.image = R.drawable.ic_face
        category.add(categoryModel)
        val categoryModel1 = CategoryModel()
        categoryModel1.categoryName = getString(R.string.hair)
        categoryModel1.colorCode = "#FEF3EF"
        categoryModel1.viewColorCode = "#F7DBD7"
        categoryModel1.image = R.drawable.ic_hair
        category.add(categoryModel1)
        val categoryModel2 = CategoryModel()
        categoryModel2.categoryName = getString(R.string.skin)
        categoryModel2.colorCode = "#F4F6F6"
        categoryModel2.viewColorCode = "#D5DBDB"
        categoryModel2.image = R.drawable.ic_skin
        category.add(categoryModel2)
        val categoryModel3 = CategoryModel()
        categoryModel3.categoryName = getString(R.string.eyes)
        categoryModel3.colorCode = "#EFFBD7"
        categoryModel3.viewColorCode = "#E5F7A3"
        categoryModel3.image = R.drawable.beautifuleyes_title
        category.add(categoryModel3)
        val categoryModel4 = CategoryModel()
        categoryModel4.categoryName = getString(R.string.handsfeet)
        categoryModel4.colorCode = "#FBEEE6"
        categoryModel4.viewColorCode = "#EDBB99"
        categoryModel4.image = R.drawable.ic_hand_feet
        category.add(categoryModel4)

        rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvCategory.adapter = CategoryAdapter(category, this)

        val imgSearch = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.imgSearch)
        imgSearch.setOnClickListener {
            Intent(this, SearchActivity::class.java).also {
                startActivity(it)
                AppUtils.startFromRightToLeft(this)
            }
        }

        imgSetting.setOnClickListener { Intent(this, SettingActivity::class.java).also {
            startActivity(it)
            AppUtils.startFromRightToLeft(this)
        } }
    }

    override fun onItemClick(category: CategoryModel, position: Int) {
        Intent(this, SubCategoryActivity::class.java).also {
            it.putExtra("position", position)
            it.putExtra("categoryName", category.categoryName)
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