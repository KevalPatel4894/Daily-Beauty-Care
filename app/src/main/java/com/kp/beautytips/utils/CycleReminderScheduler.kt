package com.kp.beautytips.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.kp.beautytips.receiver.CycleReminderReceiver
import java.util.Calendar
import java.util.concurrent.TimeUnit

object CycleReminderScheduler {

    fun scheduleCycleReminder(context: Context) {
        val prefs = context.getSharedPreferences("cycle_advisor_prefs", Context.MODE_PRIVATE)
        val lastPeriodMillis = prefs.getLong("last_period_millis", 0L)
        val cycleLengthDays = prefs.getInt("cycle_length", 28)

        if (lastPeriodMillis <= 0) return

        // Day 17 is the start of Luteal phase (acne risk window)
        val acneRiskStartMillis = lastPeriodMillis + TimeUnit.DAYS.toMillis(16)
        
        var targetCal = Calendar.getInstance().apply {
            timeInMillis = acneRiskStartMillis
            set(Calendar.HOUR_OF_DAY, 10) // 10:00 AM notification
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        // If target time is already in the past, schedule for the next cycle
        if (targetCal.timeInMillis <= System.currentTimeMillis()) {
            targetCal.add(Calendar.DAY_OF_YEAR, cycleLengthDays)
        }

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, CycleReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            4001,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, targetCal.timeInMillis, pendingIntent)
            } else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.timeInMillis, pendingIntent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
