package com.kp.beautytips.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.kp.beautytips.R
import com.kp.beautytips.activity.DetailsActivity
import com.kp.beautytips.data.TipRepository

class DailyTipWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {
        const val CHANNEL_ID = "daily_tip_channel"
        const val NOTIFICATION_ID = 3003
        const val PREFS_FILE = "water_tracker_prefs" // we can reuse or use setting/reminder prefs
        const val KEY_DAILY_TIP_ENABLED = "daily_tip_enabled"
    }

    override fun doWork(): Result {
        // Double check if enabled in preferences
        val sharedPrefs = applicationContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val enabled = sharedPrefs.getBoolean(KEY_DAILY_TIP_ENABLED, true)
        if (!enabled) {
            return Result.success()
        }

        try {
            // Load and fetch a random beauty tip
            TipRepository.initialize(applicationContext)
            val allTips = TipRepository.getAllTips(applicationContext)
            
            if (allTips.isEmpty()) {
                return Result.failure()
            }

            val randomTip = allTips.shuffled().first()
            showNotification(randomTip.title, randomTip.details, randomTip.image, randomTip.descriptionName)
            
            return Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.failure()
        }
    }

    private fun showNotification(title: String, details: String, imageResId: Int, durationText: String) {
        val context = applicationContext
        createNotificationChannel(context)

        // Launch DetailsActivity passing the random tip data
        val intent = Intent(context, DetailsActivity::class.java).apply {
            putExtra("tabName", context.getString(R.string.daily_tip))
            putExtra("title", title)
            putExtra("image", imageResId)
            putExtra("details", details)
            putExtra("descriptionName", durationText)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            3001, // Unique request code for daily tip notifications
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationTitle = context.getString(R.string.daily_tip_notification_title)
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(notificationTitle)
            .setContentText(title)
            .setStyle(NotificationCompat.BigTextStyle()
                .setBigContentTitle(notificationTitle)
                .bigText("$title\n\n$details"))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.daily_tip_channel_name)
            val descriptionText = context.getString(R.string.daily_tip_channel_desc)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
