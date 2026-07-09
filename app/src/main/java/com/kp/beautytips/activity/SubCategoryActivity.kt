package com.kp.beautytips.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.kp.beautytips.R
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.adapter.SubCategoryAdapter
import com.kp.beautytips.model.SubCategoryModel
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import com.kp.beautytips.utils.Constants
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import io.github.inflationx.viewpump.ViewPumpContextWrapper



class SubCategoryActivity : BaseActivity(), SubCategoryAdapter.OnItemClick {
    private var position: Int = 0
    private var categoryName: String = ""
    private var adRequest: AdRequest? = null
    private var mInterstitialAd: InterstitialAd? = null
    private var mAdIsLoading: Boolean = false

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)
        init()
    }

    private fun init() {
        val txtTitle = findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtTitle)
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        val rvCategory = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rvCategory)

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

        val adContainer = findViewById<ViewGroup>(R.id.ad_view)
        AdManager.loadBanner(this, adContainer, getString(R.string.banner_home_footer))

        loadFullScreenAds()

        val subCategory = ArrayList<SubCategoryModel>()
        when (position) {
            0 -> {
                val subCategoryModel = SubCategoryModel()
                subCategoryModel.categoryName = getString(R.string.wrinkles_title)
                subCategoryModel.image = R.drawable.wrinkles_title
                subCategory.add(subCategoryModel)
                val subCategoryModel1 = SubCategoryModel()
                subCategoryModel1.categoryName = getString(R.string.acne_title)
                subCategoryModel1.image = R.drawable.ic_acne
                subCategory.add(subCategoryModel1)
                val subCategoryModel2 = SubCategoryModel()
                subCategoryModel2.categoryName = getString(R.string.fairskin_title)
                subCategoryModel2.image = R.drawable.ic_fair_skin
                subCategory.add(subCategoryModel2)
                val subCategoryModel3 = SubCategoryModel()
                subCategoryModel3.categoryName = getString(R.string.blackheads_title)
                subCategoryModel3.image = R.drawable.blackheads_title
                subCategory.add(subCategoryModel3)
                val subCategoryModel4 = SubCategoryModel()
                subCategoryModel4.categoryName = getString(R.string.facialhair_title)
                subCategoryModel4.image = R.drawable.ic_facial_hair_remove
                subCategory.add(subCategoryModel4)
                val subCategoryModel5 = SubCategoryModel()
                subCategoryModel5.categoryName = getString(R.string.detan_title)
                subCategoryModel5.image = R.drawable.ic_de_tanning
                subCategory.add(subCategoryModel5)
                val subCategoryModel6 = SubCategoryModel()
                subCategoryModel6.categoryName = getString(R.string.teethwhitening_title)
                subCategoryModel6.image = R.drawable.ic_teeth_whitening
                subCategory.add(subCategoryModel6)
                val subCategoryModel7 = SubCategoryModel()
                subCategoryModel7.categoryName = getString(R.string.darklips_title)
                subCategoryModel7.image = R.drawable.ic_dark_lips
                subCategory.add(subCategoryModel7)
                val subCategoryModel8 = SubCategoryModel()
                subCategoryModel8.categoryName = getString(R.string.blemishes_title)
                subCategoryModel8.image = R.drawable.ic_blemishes
                subCategory.add(subCategoryModel8)
            }
            1 -> {
                val subCategoryModel = SubCategoryModel()
                subCategoryModel.categoryName = getString(R.string.dandruff_title)
                subCategoryModel.image = R.drawable.ic_dandruff
                subCategory.add(subCategoryModel)
                val subCategoryModel1 = SubCategoryModel()
                subCategoryModel1.categoryName = getString(R.string.silky_shiny_bouncy_title)
                subCategoryModel1.image = R.drawable.ic_silky
                subCategory.add(subCategoryModel1)
                val subCategoryModel2 = SubCategoryModel()
                subCategoryModel2.categoryName = getString(R.string.straighthair_title)
                subCategoryModel2.image = R.drawable.ic_straight
                subCategory.add(subCategoryModel2)
                val subCategoryModel3 = SubCategoryModel()
                subCategoryModel3.categoryName = getString(R.string.splitends_title)
                subCategoryModel3.image = R.drawable.ic_split
                subCategory.add(subCategoryModel3)
                val subCategoryModel4 = SubCategoryModel()
                subCategoryModel4.categoryName = getString(R.string.greying_title)
                subCategoryModel4.image = R.drawable.ic_greying
                subCategory.add(subCategoryModel4)
                val subCategoryModel5 = SubCategoryModel()
                subCategoryModel5.categoryName = getString(R.string.hairfall_title)
                subCategoryModel5.image = R.drawable.ic_hair_fall
                subCategory.add(subCategoryModel5)
                val subCategoryModel6 = SubCategoryModel()
                subCategoryModel6.categoryName = getString(R.string.frizzy_hair_title)
                subCategoryModel6.image = R.drawable.ic_frizzy_hair
                subCategory.add(subCategoryModel6)
                val subCategoryModel7 = SubCategoryModel()
                subCategoryModel7.categoryName = getString(R.string.headlice_title)
                subCategoryModel7.image = R.drawable.ic_head
                subCategory.add(subCategoryModel7)
                val subCategoryModel8 = SubCategoryModel()
                subCategoryModel8.categoryName = getString(R.string.drydmghair_title)
                subCategoryModel8.image = R.drawable.ic_dry_damage
                subCategory.add(subCategoryModel8)
            }
            2 -> {
                val subCategoryModel = SubCategoryModel()
                subCategoryModel.categoryName = getString(R.string.glowingskin_title)
                subCategoryModel.image = R.drawable.ic_glowing_skin
                subCategory.add(subCategoryModel)
                val subCategoryModel1 = SubCategoryModel()
                subCategoryModel1.categoryName = getString(R.string.unevenskin_title)
                subCategoryModel1.image = R.drawable.ic_uneven_skin
                subCategory.add(subCategoryModel1)
                val subCategoryModel2 = SubCategoryModel()
                subCategoryModel2.categoryName = getString(R.string.warts)
                subCategoryModel2.image = R.drawable.ic_warts
                subCategory.add(subCategoryModel2)
                val subCategoryModel3 = SubCategoryModel()
                subCategoryModel3.categoryName = getString(R.string.stretchmarks_title)
                subCategoryModel3.image = R.drawable.ic_stretch
                subCategory.add(subCategoryModel3)
                val subCategoryModel4 = SubCategoryModel()
                subCategoryModel4.categoryName = getString(R.string.pricklyheat_title)
                subCategoryModel4.image = R.drawable.ic_prickly
                subCategory.add(subCategoryModel4)
                val subCategoryModel5 = SubCategoryModel()
                subCategoryModel5.categoryName = getString(R.string.body_scrub_title)
                subCategoryModel5.image = R.drawable.ic_body_scrub
                subCategory.add(subCategoryModel5)
                val subCategoryModel6 = SubCategoryModel()
                subCategoryModel6.categoryName = getString(R.string.bodypolish_title)
                subCategoryModel6.image = R.drawable.ic_body_polish
                subCategory.add(subCategoryModel6)
            }
            3 -> {
                val subCategoryModel = SubCategoryModel()
                subCategoryModel.categoryName = getString(R.string.darkcircle_title)
                subCategoryModel.image = R.drawable.ic_dark_circle
                subCategory.add(subCategoryModel)
                val subCategoryModel1 = SubCategoryModel()
                subCategoryModel1.categoryName = getString(R.string.puffyeyes_title)
                subCategoryModel1.image = R.drawable.ic_puffy_eyes
                subCategory.add(subCategoryModel1)
                val subCategoryModel2 = SubCategoryModel()
                subCategoryModel2.categoryName = getString(R.string.beautifuleyes_title)
                subCategoryModel2.image = R.drawable.ic_beautyfull_eyes
                subCategory.add(subCategoryModel2)
                val subCategoryModel3 = SubCategoryModel()
                subCategoryModel3.categoryName = getString(R.string.sunkeneyes_title)
                subCategoryModel3.image = R.drawable.ic_sunken_eyes
                subCategory.add(subCategoryModel3)
                val subCategoryModel4 = SubCategoryModel()
                subCategoryModel4.categoryName = getString(R.string.bettereyebrows_title)
                subCategoryModel4.image = R.drawable.ic_batter_eye_brow
                subCategory.add(subCategoryModel4)
                val subCategoryModel5 = SubCategoryModel()
                subCategoryModel5.categoryName = getString(R.string.thickerlongereyelashes_title)
                subCategoryModel5.image = R.drawable.ic_thicker
                subCategory.add(subCategoryModel5)
            }
            4 -> {
                val subCategoryModel = SubCategoryModel()
                subCategoryModel.categoryName = getString(R.string.waxing_home_title)
                subCategoryModel.image = R.drawable.ic_waxing_home
                subCategory.add(subCategoryModel)
                val subCategoryModel1 = SubCategoryModel()
                subCategoryModel1.categoryName = getString(R.string.dryroughhands_title)
                subCategoryModel1.image = R.drawable.ic_dry_hands
                subCategory.add(subCategoryModel1)
                val subCategoryModel2 = SubCategoryModel()
                subCategoryModel2.categoryName = getString(R.string.nailgrowth_title)
                subCategoryModel2.image = R.drawable.ic_nail_growth
                subCategory.add(subCategoryModel2)
                val subCategoryModel3 = SubCategoryModel()
                subCategoryModel3.categoryName = getString(R.string.pinkynail_title)
                subCategoryModel3.image = R.drawable.ic_pink_nail
                subCategory.add(subCategoryModel3)
                val subCategoryModel4 = SubCategoryModel()
                subCategoryModel4.categoryName = getString(R.string.darkinnerthighs_title)
                subCategoryModel4.image = R.drawable.ic_dark_inner
                subCategory.add(subCategoryModel4)
                val subCategoryModel5 = SubCategoryModel()
                subCategoryModel5.categoryName = getString(R.string.darkunderarms_title)
                subCategoryModel5.image = R.drawable.ic_dark_underarms
                subCategory.add(subCategoryModel5)
                val subCategoryModel6 = SubCategoryModel()
                subCategoryModel6.categoryName = getString(R.string.lightening_vagina_title)
                subCategoryModel6.image = R.drawable.ic_private_part
                subCategory.add(subCategoryModel6)
                val subCategoryModel7 = SubCategoryModel()
                subCategoryModel7.categoryName = getString(R.string.crackedheel_title)
                subCategoryModel7.image = R.drawable.ic_cracked_heels
                subCategory.add(subCategoryModel7)
                val subCategoryModel8 = SubCategoryModel()
                subCategoryModel8.categoryName = getString(R.string.darkhandfeet_title)
                subCategoryModel8.image = R.drawable.ic_white_hands
                subCategory.add(subCategoryModel8)
            }
            5 -> {
                val subCategoryModel = SubCategoryModel()
                subCategoryModel.categoryName = getString(R.string.stretch_marks)
                subCategoryModel.image = R.drawable.ic_stretch
                subCategory.add(subCategoryModel)

                val subCategoryModel1 = SubCategoryModel()
                subCategoryModel1.categoryName = getString(R.string.dark_neck)
                subCategoryModel1.image = R.drawable.ic_de_tanning
                subCategory.add(subCategoryModel1)

                val subCategoryModel2 = SubCategoryModel()
                subCategoryModel2.categoryName = getString(R.string.body_scrub)
                subCategoryModel2.image = R.drawable.ic_body_scrub
                subCategory.add(subCategoryModel2)

                val subCategoryModel3 = SubCategoryModel()
                subCategoryModel3.categoryName = getString(R.string.elbow_knee)
                subCategoryModel3.image = R.drawable.ic_white_hands
                subCategory.add(subCategoryModel3)
            }
            6 -> {
                val subCategoryModel = SubCategoryModel()
                subCategoryModel.categoryName = getString(R.string.beard_growth)
                subCategoryModel.image = R.drawable.ic_beard_growth
                subCategory.add(subCategoryModel)

                val subCategoryModel1 = SubCategoryModel()
                subCategoryModel1.categoryName = getString(R.string.mustache_care)
                subCategoryModel1.image = R.drawable.ic_mustache_care
                subCategory.add(subCategoryModel1)

                val subCategoryModel2 = SubCategoryModel()
                subCategoryModel2.categoryName = getString(R.string.men_hair_fall)
                subCategoryModel2.image = R.drawable.ic_men_hair_fall
                subCategory.add(subCategoryModel2)
            }
        }
        rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvCategory.adapter = SubCategoryAdapter(subCategory, this)
    }

    override fun onItemClick(subCategory: SubCategoryModel, position: Int) {
        Intent(this, ListActivity::class.java).also {
            it.putExtra("position", position)
            it.putExtra("categoryName", subCategory.categoryName)
            startActivity(it)
            AppUtils.startFromRightToLeft(this)
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