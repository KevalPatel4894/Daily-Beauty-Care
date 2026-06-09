package com.kp.beautytips.utils

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.kp.beautytips.R
import com.kp.beautytips.receiver.WaterReminderReceiver
import java.util.Calendar

object WaterReminderScheduler {
    const val WATER_CHANNEL_ID = "water_intake_reminders"
    const val PREFS_FILE = "water_tracker_prefs"
    const val KEY_WATER_REMINDER_ENABLED = "water_reminder_enabled"
    const val KEY_WATER_REMINDER_INTERVAL = "water_reminder_interval"

    fun scheduleWaterReminder(context: Context) {
        val sharedPrefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val enabled = sharedPrefs.getBoolean(KEY_WATER_REMINDER_ENABLED, true)
        val interval = sharedPrefs.getInt(KEY_WATER_REMINDER_INTERVAL, 2)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, WaterReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            2001,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        if (!enabled) {
            alarmManager.cancel(pendingIntent)
            return
        }

        // Calculate target alarm time using Smart Awake-Hours logic (9:00 AM to 9:00 PM)
        val now = Calendar.getInstance()
        val target = Calendar.getInstance()
        val currentHour = now.get(Calendar.HOUR_OF_DAY)

        if (currentHour < 9) {
            // Today at 9:00 AM
            target.set(Calendar.HOUR_OF_DAY, 9)
            target.set(Calendar.MINUTE, 0)
            target.set(Calendar.SECOND, 0)
            target.set(Calendar.MILLISECOND, 0)
        } else if (currentHour >= 21) {
            // Tomorrow at 9:00 AM
            target.add(Calendar.DAY_OF_YEAR, 1)
            target.set(Calendar.HOUR_OF_DAY, 9)
            target.set(Calendar.MINUTE, 0)
            target.set(Calendar.SECOND, 0)
            target.set(Calendar.MILLISECOND, 0)
        } else {
            // Between 9 AM and 9 PM
            target.add(Calendar.HOUR_OF_DAY, interval)
            val targetHour = target.get(Calendar.HOUR_OF_DAY)
            val targetDay = target.get(Calendar.DAY_OF_YEAR)
            val nowDay = now.get(Calendar.DAY_OF_YEAR)

            if (targetHour >= 21 || targetDay != nowDay) {
                // Too late for today, reset to tomorrow at 9:00 AM
                target.timeInMillis = System.currentTimeMillis()
                target.add(Calendar.DAY_OF_YEAR, 1)
                target.set(Calendar.HOUR_OF_DAY, 9)
                target.set(Calendar.MINUTE, 0)
                target.set(Calendar.SECOND, 0)
                target.set(Calendar.MILLISECOND, 0)
            }
        }

        val canScheduleExact = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            alarmManager.canScheduleExactAlarms()
        } else {
            true
        }

        if (canScheduleExact) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    target.timeInMillis,
                    pendingIntent
                )
            } else {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    target.timeInMillis,
                    pendingIntent
                )
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    target.timeInMillis,
                    pendingIntent
                )
            } else {
                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    target.timeInMillis,
                    pendingIntent
                )
            }
        }
    }

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.water_channel_name)
            val descriptionText = context.getString(R.string.water_channel_desc)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(WATER_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
