package com.kp.beautytips.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kp.beautytips.R
import com.kp.beautytips.adapter.ListAdapter
import com.kp.beautytips.model.ListModel
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import com.kp.beautytips.utils.Constants
import com.kp.beautytips.data.eyes.*
import com.kp.beautytips.data.face.*
import com.kp.beautytips.data.hair.*
import com.kp.beautytips.data.handsfeet.*
import com.kp.beautytips.data.skin.*
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.util.Locale

class SearchActivity : BaseActivity() {

    private lateinit var etSearch: AppCompatEditText
    private lateinit var imgClear: AppCompatImageView
    private lateinit var imgBack: AppCompatImageView
    private lateinit var rvResults: RecyclerView
    private lateinit var tvNoResults: AppCompatTextView

    private var adRequest: AdRequest? = null
    private var mInterstitialAd: InterstitialAd? = null
    private var mAdIsLoading: Boolean = false

    private val allTips = ArrayList<ListModel>()

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        init()
        loadAllTips()
    }

    private fun init() {
        etSearch = findViewById(R.id.etSearch)
        imgClear = findViewById(R.id.imgClear)
        imgBack = findViewById(R.id.imgBack)
        rvResults = findViewById(R.id.rvResults)
        tvNoResults = findViewById(R.id.tvNoResults)

        rvResults.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        imgBack.setOnClickListener {
            onBackPressed()
            AppUtils.finishFromLeftToRight(this)
        }

        imgClear.setOnClickListener {
            etSearch.setText("")
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val query = s?.toString()?.trim() ?: ""
                if (query.isNotEmpty()) {
                    imgClear.visibility = View.VISIBLE
                    performSearch(query)
                } else {
                    imgClear.visibility = View.GONE
                    rvResults.adapter = ListAdapter(ArrayList(), "Search Results")
                    tvNoResults.visibility = View.GONE
                }
            }
        })

        adRequest = AdRequest.Builder().build()
        val adContainer = findViewById<View>(R.id.ad_view)
        val mAdView = AdView(this)
        mAdView.setAdSize(AdSize.BANNER)
        mAdView.adUnitId = getString(R.string.banner_home_footer)
        (adContainer as RelativeLayout).addView(mAdView)
        mAdView.loadAd(adRequest!!)

        loadFullScreenAds()
    }

    private fun loadAllTips() {
        val context = this
        // Face
        allTips.addAll(FaceWrinkles.remedy(context))
        allTips.addAll(FaceWrinkles.diet(context))
        allTips.addAll(FaceWrinkles.exercise(context))
        allTips.addAll(Acne.remedy(context))
        allTips.addAll(Acne.diet(context))
        allTips.addAll(Acne.exercise(context))
        allTips.addAll(FairSkin.remedy(context))
        allTips.addAll(FairSkin.diet(context))
        allTips.addAll(FairSkin.exercise(context))
        allTips.addAll(BlackHeads.remedy(context))
        allTips.addAll(BlackHeads.diet(context))
        allTips.addAll(BlackHeads.exercise(context))
        allTips.addAll(FacialHairRemoval.remedy(context))
        allTips.addAll(FacialHairRemoval.diet(context))
        allTips.addAll(FacialHairRemoval.exercise(context))
        allTips.addAll(DeTanning.remedy(context))
        allTips.addAll(DeTanning.diet(context))
        allTips.addAll(DeTanning.exercise(context))
        allTips.addAll(TeethWhitening.remedy(context))
        allTips.addAll(TeethWhitening.diet(context))
        allTips.addAll(TeethWhitening.exercise(context))
        allTips.addAll(DarkLips.remedy(context))
        allTips.addAll(DarkLips.diet(context))
        allTips.addAll(DarkLips.exercise(context))
        allTips.addAll(Blemishes.remedy(context))
        allTips.addAll(Blemishes.diet(context))
        allTips.addAll(Blemishes.exercise(context))

        // Hair
        allTips.addAll(Dandruff.remedy(context))
        allTips.addAll(Dandruff.diet(context))
        allTips.addAll(Dandruff.exercise(context))
        allTips.addAll(Silky.remedy(context))
        allTips.addAll(Silky.diet(context))
        allTips.addAll(Silky.exercise(context))
        allTips.addAll(Straight.remedy(context))
        allTips.addAll(Straight.diet(context))
        allTips.addAll(Straight.exercise(context))
        allTips.addAll(SplitEnds.remedy(context))
        allTips.addAll(SplitEnds.diet(context))
        allTips.addAll(SplitEnds.exercise(context))
        allTips.addAll(Greying.remedy(context))
        allTips.addAll(Greying.diet(context))
        allTips.addAll(Greying.exercise(context))
        allTips.addAll(HairFall.remedy(context))
        allTips.addAll(HairFall.diet(context))
        allTips.addAll(HairFall.exercise(context))
        allTips.addAll(FrizzyHair.remedy(context))
        allTips.addAll(FrizzyHair.diet(context))
        allTips.addAll(FrizzyHair.exercise(context))
        allTips.addAll(HeadLice.remedy(context))
        allTips.addAll(HeadLice.diet(context))
        allTips.addAll(HeadLice.exercise(context))
        allTips.addAll(DryAndDamagedHair.remedy(context))
        allTips.addAll(DryAndDamagedHair.diet(context))
        allTips.addAll(DryAndDamagedHair.exercise(context))

        // Skin
        allTips.addAll(GlowingSkin.remedy(context))
        allTips.addAll(GlowingSkin.diet(context))
        allTips.addAll(GlowingSkin.exercise(context))
        allTips.addAll(UnevenSkin.remedy(context))
        allTips.addAll(UnevenSkin.diet(context))
        allTips.addAll(UnevenSkin.exercise(context))
        allTips.addAll(Warts.remedy(context))
        allTips.addAll(Warts.diet(context))
        allTips.addAll(Warts.exercise(context))
        allTips.addAll(StretchMarks.remedy(context))
        allTips.addAll(StretchMarks.diet(context))
        allTips.addAll(StretchMarks.exercise(context))
        allTips.addAll(PricklyHeat.remedy(context))
        allTips.addAll(PricklyHeat.diet(context))
        allTips.addAll(PricklyHeat.exercise(context))
        allTips.addAll(BodyScrubs.remedy(context))
        allTips.addAll(BodyScrubs.diet(context))
        allTips.addAll(BodyScrubs.exercise(context))
        allTips.addAll(BodyPolish.remedy(context))
        allTips.addAll(BodyPolish.diet(context))
        allTips.addAll(BodyPolish.exercise(context))

        // Eyes
        allTips.addAll(DarkCircles.remedy(context))
        allTips.addAll(DarkCircles.diet(context))
        allTips.addAll(DarkCircles.exercise(context))
        allTips.addAll(PuffyEyes.remedy(context))
        allTips.addAll(PuffyEyes.diet(context))
        allTips.addAll(PuffyEyes.exercise(context))
        allTips.addAll(BeautifulEyes.remedy(context))
        allTips.addAll(BeautifulEyes.diet(context))
        allTips.addAll(BeautifulEyes.exercise(context))
        allTips.addAll(SunkenEyes.remedy(context))
        allTips.addAll(SunkenEyes.diet(context))
        allTips.addAll(SunkenEyes.exercise(context))
        allTips.addAll(BetterEyeBrows.remedy(context))
        allTips.addAll(BetterEyeBrows.diet(context))
        allTips.addAll(BetterEyeBrows.exercise(context))
        allTips.addAll(ThickerAndLongerEye.remedy(context))
        allTips.addAll(ThickerAndLongerEye.diet(context))
        allTips.addAll(ThickerAndLongerEye.exercise(context))

        // Hands & Feet
        allTips.addAll(WaxingAtHome.remedy(context))
        allTips.addAll(WaxingAtHome.diet(context))
        allTips.addAll(WaxingAtHome.exercise(context))
        allTips.addAll(DryAndRoughHand.remedy(context))
        allTips.addAll(DryAndRoughHand.diet(context))
        allTips.addAll(DryAndRoughHand.exercise(context))
        allTips.addAll(NailGrowth.remedy(context))
        allTips.addAll(NailGrowth.diet(context))
        allTips.addAll(NailGrowth.exercise(context))
        allTips.addAll(PinkyShinyNail.remedy(context))
        allTips.addAll(PinkyShinyNail.diet(context))
        allTips.addAll(PinkyShinyNail.exercise(context))
        allTips.addAll(DarkInnerThighs.remedy(context))
        allTips.addAll(DarkInnerThighs.diet(context))
        allTips.addAll(DarkInnerThighs.exercise(context))
        allTips.addAll(DarkUnderArms.remedy(context))
        allTips.addAll(DarkUnderArms.diet(context))
        allTips.addAll(DarkUnderArms.exercise(context))
        allTips.addAll(DarkPrivateAreas.remedy(context))
        allTips.addAll(DarkPrivateAreas.diet(context))
        allTips.addAll(DarkPrivateAreas.exercise(context))
        allTips.addAll(CrackedHeels.remedy(context))
        allTips.addAll(CrackedHeels.diet(context))
        allTips.addAll(CrackedHeels.exercise(context))
        allTips.addAll(DarkHandsAndFeet.remedy(context))
        allTips.addAll(DarkHandsAndFeet.diet(context))
        allTips.addAll(DarkHandsAndFeet.exercise(context))

        // Deduplicate list by title to ensure unique search results
        val uniqueTips = ArrayList<ListModel>()
        val titlesSeen = HashSet<String>()
        for (tip in allTips) {
            if (!titlesSeen.contains(tip.title)) {
                uniqueTips.add(tip)
                titlesSeen.add(tip.title)
            }
        }
        allTips.clear()
        allTips.addAll(uniqueTips)
    }

    private fun performSearch(query: String) {
        val filteredList = ArrayList<ListModel>()
        val lowercaseQuery = query.lowercase(Locale.getDefault())

        for (tip in allTips) {
            if (tip.title.lowercase(Locale.getDefault()).contains(lowercaseQuery) ||
                tip.details.lowercase(Locale.getDefault()).contains(lowercaseQuery)
            ) {
                filteredList.add(tip)
            }
        }

        if (filteredList.isEmpty()) {
            rvResults.visibility = View.GONE
            tvNoResults.visibility = View.VISIBLE
        } else {
            rvResults.visibility = View.VISIBLE
            tvNoResults.visibility = View.GONE
            rvResults.adapter = ListAdapter(filteredList, "Search Results")
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
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                    mAdIsLoading = false
                    showInterstitial()
                }
            }
        )
    }

    private fun showInterstitial() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    mInterstitialAd = null
                }
                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    mInterstitialAd = null
                }
                override fun onAdShowedFullScreenContent() {
                }
            }
            mInterstitialAd?.show(this)
        }
    }
}
