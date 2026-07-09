package com.kp.beautytips.utils

import android.app.Activity
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.kp.beautytips.R

/**
 * Centralized AdMob helper.
 * - loadBanner()       : attaches an AdMob BANNER to the given container with proper LayoutParams.
 * - loadInterstitial() : loads & optionally shows an interstitial, respecting a counter gate.
 */
object AdManager {

    // ─── Banner ──────────────────────────────────────────────────────────────

    /**
     * Attaches a banner AdView to [container] (any ViewGroup) with MATCH_PARENT width.
     * Call this after setContentView() so the container is already measured.
     */
    fun loadBanner(activity: Activity, container: ViewGroup, adUnitId: String) {
        try {
            val adView = AdView(activity)
            adView.adUnitId = adUnitId
            adView.setAdSize(AdSize.BANNER)

            // Explicit LayoutParams: full width, wrap height — critical for banner to render
            val params = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            adView.layoutParams = params

            adView.adListener = object : AdListener() {
                override fun onAdLoaded() {
                    adView.visibility = android.view.View.VISIBLE
                }
                override fun onAdFailedToLoad(error: LoadAdError) {
                    // Hide container when no fill to avoid blank space
                    container.visibility = android.view.View.GONE
                }
            }

            container.removeAllViews()
            container.addView(adView)
            adView.loadAd(AdRequest.Builder().build())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // ─── Interstitial ─────────────────────────────────────────────────────────

    /**
     * Loads an interstitial and shows it if counter threshold is reached.
     * Tracks the count in SharedPreferences under key [Constants.FULL_ADS_COUNT].
     */
    fun loadInterstitialIfReady(
        activity: Activity,
        adUnitId: String,
        onDismissed: (() -> Unit)? = null
    ) {
        val prefs = activity.getSharedPreferences(activity.getString(R.string.app_name), android.content.Context.MODE_PRIVATE)
        val count = prefs.getInt(Constants.FULL_ADS_COUNT, 0)

        if (count >= Constants.ADS_FULL_COUNTER) {
            prefs.edit().putInt(Constants.FULL_ADS_COUNT, 0).apply()
            InterstitialAd.load(
                activity, adUnitId, AdRequest.Builder().build(),
                object : InterstitialAdLoadCallback() {
                    override fun onAdLoaded(ad: InterstitialAd) {
                        ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                            override fun onAdDismissedFullScreenContent() {
                                onDismissed?.invoke()
                            }
                        }
                        ad.show(activity)
                    }
                    override fun onAdFailedToLoad(error: LoadAdError) { /* silent */ }
                }
            )
        } else {
            prefs.edit().putInt(Constants.FULL_ADS_COUNT, count + 1).apply()
        }
    }
}
