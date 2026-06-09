package com.kp.beautytips.utils

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.kp.beautytips.worker.DailyTipWorker
import java.util.Calendar
import java.util.concurrent.TimeUnit

object DailyTipScheduler {
    private const val WORK_NAME = "DailyTipPeriodicWork"

    fun scheduleDailyTip(context: Context) {
        val sharedPrefs = context.getSharedPreferences(DailyTipWorker.PREFS_FILE, Context.MODE_PRIVATE)
        val enabled = sharedPrefs.getBoolean(DailyTipWorker.KEY_DAILY_TIP_ENABLED, true)

        if (!enabled) {
            cancelDailyTip(context)
            return
        }

        val currentDate = Calendar.getInstance()
        val dueDate = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        if (dueDate.before(currentDate)) {
            dueDate.add(Calendar.HOUR_OF_DAY, 24)
        }

        val initialDelay = dueDate.timeInMillis - currentDate.timeInMillis

        val dailyTipWorkRequest = PeriodicWorkRequestBuilder<DailyTipWorker>(24, TimeUnit.HOURS)
            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP, // Retains existing scheduled work if already created, avoiding delay resets on app launch
            dailyTipWorkRequest
        )
    }

    fun cancelDailyTip(context: Context) {
        WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
    }
}
