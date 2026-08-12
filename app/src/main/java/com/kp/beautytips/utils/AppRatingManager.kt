package com.kp.beautytips.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import com.google.android.play.core.review.ReviewManagerFactory

object AppRatingManager {

    /**
     * Shows official Google Play In-App Review dialog.
     * If the in-app review API is unavailable or fails, falls back to opening the Play Store app details page.
     */
    fun showRatingDialog(activity: Activity) {
        try {
            val manager = ReviewManagerFactory.create(activity)
            val request = manager.requestReviewFlow()
            request.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val reviewInfo = task.result
                    val flow = manager.launchReviewFlow(activity, reviewInfo)
                    flow.addOnCompleteListener { _ ->
                        // Review flow finished
                    }
                } else {
                    openPlayStorePage(activity)
                }
            }
        } catch (e: Exception) {
            openPlayStorePage(activity)
        }
    }

    /**
     * Direct fallback to open Play Store listing.
     */
    fun openPlayStorePage(activity: Activity) {
        val uri = Uri.parse("market://details?id=${activity.packageName}")
        val goToMarket = Intent(Intent.ACTION_VIEW, uri).apply {
            addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        }
        try {
            activity.startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=${activity.packageName}")
                )
            )
        }
    }
}
