package com.kp.beautytips.activity

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.switchmaterial.SwitchMaterial
import com.kp.beautytips.R
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import com.kp.beautytips.utils.WaterReminderScheduler
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.random.Random

class WaterTrackerActivity : BaseActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    
    private lateinit var progressWater: CircularProgressIndicator
    private lateinit var txtWaterCups: AppCompatTextView
    private lateinit var txtWaterPercent: AppCompatTextView
    private lateinit var btnRemoveGlass: MaterialButton
    private lateinit var btnAddGlass: MaterialButton
    private lateinit var txtHydrationTip: AppCompatTextView
    private lateinit var switchWaterReminder: SwitchMaterial
    private lateinit var radioGroupInterval: RadioGroup
    private lateinit var layoutInterval: LinearLayoutCompat

    private val glassViews = ArrayList<AppCompatImageView>()

    private val PREFS_FILE = "water_tracker_prefs"
    private val KEY_WATER_COUNT = "water_today_count"
    private val KEY_LAST_DATE = "water_last_date"
    private val KEY_WATER_GOAL = "water_water_goal" // default 8

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_tracker)

        sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)

        initViews()
        setupToolbar()
        loadAdBanner()
        checkNewDayReset()
        setupRandomTip()
        updateUI()
    }

    private fun initViews() {
        progressWater = findViewById(R.id.progressWater)
        txtWaterCups = findViewById(R.id.txtWaterCups)
        txtWaterPercent = findViewById(R.id.txtWaterPercent)
        btnRemoveGlass = findViewById(R.id.btnRemoveGlass)
        btnAddGlass = findViewById(R.id.btnAddGlass)
        txtHydrationTip = findViewById(R.id.txtHydrationTip)
        switchWaterReminder = findViewById(R.id.switchWaterReminder)
        radioGroupInterval = findViewById(R.id.radioGroupInterval)
        layoutInterval = findViewById(R.id.layoutInterval)

        // Bind 8 glass icons
        for (i in 1..8) {
            val resId = resources.getIdentifier("imgGlass$i", "id", packageName)
            val glassImg = findViewById<AppCompatImageView>(resId)
            glassViews.add(glassImg)

            // Direct tap on glass sets the water count to that number of glasses
            glassImg.setOnClickListener {
                updateWaterCount(i)
            }
        }

        btnAddGlass.setOnClickListener {
            val currentCount = sharedPreferences.getInt(KEY_WATER_COUNT, 0)
            if (currentCount < 8) {
                updateWaterCount(currentCount + 1)
            }
        }

        btnRemoveGlass.setOnClickListener {
            val currentCount = sharedPreferences.getInt(KEY_WATER_COUNT, 0)
            if (currentCount > 0) {
                updateWaterCount(currentCount - 1)
            }
        }

        // Reminders switch setup
        val reminderEnabled = sharedPreferences.getBoolean(WaterReminderScheduler.KEY_WATER_REMINDER_ENABLED, true)
        switchWaterReminder.isChecked = reminderEnabled
        layoutInterval.visibility = if (reminderEnabled) View.VISIBLE else View.GONE

        switchWaterReminder.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean(WaterReminderScheduler.KEY_WATER_REMINDER_ENABLED, isChecked).apply()
            layoutInterval.visibility = if (isChecked) View.VISIBLE else View.GONE
            WaterReminderScheduler.scheduleWaterReminder(this)
        }

        // Interval radios setup
        val savedInterval = sharedPreferences.getInt(WaterReminderScheduler.KEY_WATER_REMINDER_INTERVAL, 2)
        when (savedInterval) {
            2 -> radioGroupInterval.check(R.id.radio2h)
            3 -> radioGroupInterval.check(R.id.radio3h)
            4 -> radioGroupInterval.check(R.id.radio4h)
        }

        radioGroupInterval.setOnCheckedChangeListener { _, checkedId ->
            val interval = when (checkedId) {
                R.id.radio2h -> 2
                R.id.radio3h -> 3
                R.id.radio4h -> 4
                else -> 2
            }
            sharedPreferences.edit().putInt(WaterReminderScheduler.KEY_WATER_REMINDER_INTERVAL, interval).apply()
            WaterReminderScheduler.scheduleWaterReminder(this)
        }
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.navigationIcon?.setTint(Color.WHITE)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(this)
        return true
    }

    private fun loadAdBanner() {
        try {
            val adContainer = findViewById<RelativeLayout>(R.id.ad_view)
            val adRequest = AdRequest.Builder().build()
            val mAdView = AdView(this)
            mAdView.setAdSize(AdSize.BANNER)
            mAdView.adUnitId = getString(R.string.banner_home_footer)
            adContainer.addView(mAdView)
            mAdView.loadAd(adRequest)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun checkNewDayReset() {
        val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Calendar.getInstance().time)
        val lastDate = sharedPreferences.getString(KEY_LAST_DATE, "") ?: ""

        if (todayStr != lastDate) {
            sharedPreferences.edit().apply {
                putInt(KEY_WATER_COUNT, 0)
                putString(KEY_LAST_DATE, todayStr)
                apply()
            }
        }
    }

    private fun setupRandomTip() {
        val tipsList = arrayOf(
            getString(R.string.hydration_tip_1),
            getString(R.string.hydration_tip_2),
            getString(R.string.hydration_tip_3),
            getString(R.string.hydration_tip_4),
            getString(R.string.hydration_tip_5)
        )
        val randomIndex = Random.nextInt(tipsList.size)
        txtHydrationTip.text = tipsList[randomIndex]
    }

    private fun updateWaterCount(newCount: Int) {
        val oldCount = sharedPreferences.getInt(KEY_WATER_COUNT, 0)
        sharedPreferences.edit().putInt(KEY_WATER_COUNT, newCount).apply()

        // If goal is newly reached
        if (newCount == 8 && oldCount < 8) {
            Toast.makeText(this, getString(R.string.water_goal_achieved), Toast.LENGTH_LONG).show()
        }

        updateUI()
    }

    private fun updateUI() {
        val count = sharedPreferences.getInt(KEY_WATER_COUNT, 0)
        val goal = sharedPreferences.getInt(KEY_WATER_GOAL, 8)

        // Progress Text and Circle Meter
        txtWaterCups.text = String.format(getString(R.string.glasses_format), count, goal)
        val percent = (count * 100) / goal
        txtWaterPercent.text = "$percent%"
        progressWater.progress = count

        // Update 8 Glass Icons
        for (i in 0 until 8) {
            if (i < count) {
                glassViews[i].setImageResource(R.drawable.ic_glass_full)
            } else {
                glassViews[i].setImageResource(R.drawable.ic_glass_empty)
            }
        }
    }
}
