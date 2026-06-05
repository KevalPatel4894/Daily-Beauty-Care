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
import com.kp.beautytips.data.TipRepository
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
        allTips.clear()
        allTips.addAll(TipRepository.getAllTips(this))
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
