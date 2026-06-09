package com.kp.beautytips.receiver

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.kp.beautytips.R
import com.kp.beautytips.activity.MainActivity
import com.kp.beautytips.activity.DetailsActivity
import com.kp.beautytips.data.TipRepository
import com.kp.beautytips.utils.ReminderScheduler

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            ReminderScheduler.scheduleReminder(context)
            return
        }

        showNotification(context)
        
        // Re-schedule for next day
        ReminderScheduler.scheduleReminder(context)
    }

    private fun showNotification(context: Context) {
        ReminderScheduler.createNotificationChannel(context)

        // Fetch a random beauty tip for the notification content
        val allTips = TipRepository.getAllTips(context)
        val (title, content, tip) = if (allTips.isNotEmpty()) {
            val randomTip = allTips.shuffled().first()
            Triple(randomTip.title, randomTip.details, randomTip)
        } else {
            Triple(context.getString(R.string.reminder_default_title), context.getString(R.string.reminder_default_content), null)
        }

        val notificationIntent = if (tip != null) {
            Intent(context, DetailsActivity::class.java).apply {
                putExtra("tabName", context.getString(R.string.daily_tip))
                putExtra("title", tip.title)
                putExtra("image", tip.image)
                putExtra("details", tip.details)
                putExtra("descriptionName", tip.descriptionName)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
        } else {
            Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, ReminderScheduler.REMINDER_CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(context.getString(R.string.notification_title_format, title))
            .setContentText(content)
            .setStyle(NotificationCompat.BigTextStyle().bigText(content))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(2002, notification)
    }
}
