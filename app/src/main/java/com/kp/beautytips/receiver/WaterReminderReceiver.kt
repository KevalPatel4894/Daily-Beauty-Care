package com.kp.beautytips.receiver

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.kp.beautytips.R
import com.kp.beautytips.activity.WaterTrackerActivity
import com.kp.beautytips.utils.WaterReminderScheduler

class WaterReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            WaterReminderScheduler.scheduleWaterReminder(context)
            return
        }

        showNotification(context)

        // Re-schedule for next interval
        WaterReminderScheduler.scheduleWaterReminder(context)
    }

    private fun showNotification(context: Context) {
        WaterReminderScheduler.createNotificationChannel(context)

        val notificationIntent = Intent(context, WaterTrackerActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            2002, // Unique request code for water notifications
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val title = context.getString(R.string.water_notification_title)
        val body = context.getString(R.string.water_notification_body)

        val notification = NotificationCompat.Builder(context, WaterReminderScheduler.WATER_CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(2003, notification)
    }
}
