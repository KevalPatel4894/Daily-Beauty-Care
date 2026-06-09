package com.kp.beautytips.activity

import android.Manifest
import android.app.TimePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.gms.ads.*
import com.kp.beautytips.R
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import com.kp.beautytips.utils.ReminderScheduler
import com.kp.beautytips.worker.DailyTipWorker
import com.kp.beautytips.utils.DailyTipScheduler
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.util.Locale

class ReminderActivity : BaseActivity() {
    private lateinit var switchReminder: SwitchCompat
    private lateinit var tvReminderTime: androidx.appcompat.widget.AppCompatTextView
    private lateinit var rlTimePicker: View
    private lateinit var switchDailyTip: SwitchCompat
    private lateinit var sharedPrefs: SharedPreferences
    private var adRequest: AdRequest? = null

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            enableReminder(true)
        } else {
            switchReminder.isChecked = false
            Toast.makeText(this, getString(R.string.notification_permission_required_toast), Toast.LENGTH_LONG).show()
        }
    }

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)
        init()
    }

    private fun init() {
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val txtTabTitle = findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtTabTitle)
        txtTabTitle.text = getString(R.string.daily_reminders)

        switchReminder = findViewById(R.id.switchReminder)
        tvReminderTime = findViewById(R.id.tvReminderTime)
        rlTimePicker = findViewById(R.id.rlTimePicker)
        switchDailyTip = findViewById(R.id.switchDailyTip)

        sharedPrefs = getSharedPreferences(ReminderScheduler.PREFS_NAME, Context.MODE_PRIVATE)
        val enabled = sharedPrefs.getBoolean(ReminderScheduler.KEY_REMINDER_ENABLED, true)
        val hour = sharedPrefs.getInt(ReminderScheduler.KEY_REMINDER_HOUR, 9)
        val minute = sharedPrefs.getInt(ReminderScheduler.KEY_REMINDER_MINUTE, 0)

        switchReminder.isChecked = enabled
        updateTimeText(hour, minute)

        switchReminder.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkNotificationPermissionAndEnable()
            } else {
                enableReminder(false)
            }
        }

        // Daily Tip of the Day switch setup
        val dailyTipPrefs = getSharedPreferences(DailyTipWorker.PREFS_FILE, Context.MODE_PRIVATE)
        val dailyTipEnabled = dailyTipPrefs.getBoolean(DailyTipWorker.KEY_DAILY_TIP_ENABLED, true)
        switchDailyTip.isChecked = dailyTipEnabled

        switchDailyTip.setOnCheckedChangeListener { _, isChecked ->
            dailyTipPrefs.edit().putBoolean(DailyTipWorker.KEY_DAILY_TIP_ENABLED, isChecked).apply()
            DailyTipScheduler.scheduleDailyTip(this)
            if (isChecked) {
                Toast.makeText(this, getString(R.string.reminder_scheduled_toast), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.reminders_disabled_toast), Toast.LENGTH_SHORT).show()
            }
        }

        rlTimePicker.setOnClickListener {
            if (switchReminder.isChecked) {
                val currentHour = sharedPrefs.getInt(ReminderScheduler.KEY_REMINDER_HOUR, 9)
                val currentMinute = sharedPrefs.getInt(ReminderScheduler.KEY_REMINDER_MINUTE, 0)
                
                TimePickerDialog(this, R.style.DialogTheme, { _, h, m ->
                    sharedPrefs.edit()
                        .putInt(ReminderScheduler.KEY_REMINDER_HOUR, h)
                        .putInt(ReminderScheduler.KEY_REMINDER_MINUTE, m)
                        .apply()
                    updateTimeText(h, m)
                    ReminderScheduler.scheduleReminder(this)
                    Toast.makeText(this, getString(R.string.reminder_scheduled_toast), Toast.LENGTH_SHORT).show()
                }, currentHour, currentMinute, false).show()
            } else {
                Toast.makeText(this, getString(R.string.please_enable_reminders_toast), Toast.LENGTH_SHORT).show()
            }
        }

        adRequest = AdRequest.Builder().build()
        val adContainer = findViewById<View>(R.id.ad_view)
        val mAdView = AdView(this)
        mAdView.setAdSize(AdSize.BANNER)
        mAdView.adUnitId = getString(R.string.banner_home_footer)
        (adContainer as RelativeLayout).addView(mAdView)
        mAdView.loadAd(adRequest!!)
    }

    private fun checkNotificationPermissionAndEnable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
                enableReminder(true)
            } else {
                requestPermissionLauncher.launch(permission)
            }
        } else {
            enableReminder(true)
        }
    }

    private fun enableReminder(enable: Boolean) {
        sharedPrefs.edit().putBoolean(ReminderScheduler.KEY_REMINDER_ENABLED, enable).apply()
        ReminderScheduler.scheduleReminder(this)
        if (enable) {
            Toast.makeText(this, getString(R.string.reminders_enabled_toast), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.reminders_disabled_toast), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateTimeText(hour: Int, minute: Int) {
        val amPm = if (hour < 12) "AM" else "PM"
        val displayHour = when {
            hour == 0 -> 12
            hour > 12 -> hour - 12
            else -> hour
        }
        val timeStr = String.format(Locale.getDefault(), "%02d:%02d %s", displayHour, minute, amPm)
        tvReminderTime.text = timeStr
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(this)
        return true
    }
}
