package com.kp.beautytips.activity

import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.kp.beautytips.R
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CheckInActivity : BaseActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var txtStreakCount: AppCompatTextView
    private lateinit var txtStreakLabel: AppCompatTextView
    private lateinit var btnCheckIn: MaterialButton

    // Calendar references
    private val dayNameViews = ArrayList<AppCompatTextView>()
    private val dayCircleViews = ArrayList<FrameLayout>()
    private val dayNumViews = ArrayList<AppCompatTextView>()
    private val dayCheckViews = ArrayList<AppCompatImageView>()

    // Badge references
    private val badgeCards = ArrayList<MaterialCardView>()
    private val badgeIcons = ArrayList<AppCompatImageView>()
    private val badgeStatuses = ArrayList<AppCompatTextView>()

    private val PREFS_FILE = "DailyBeautyCarePrefs"
    private val KEY_LAST_CHECK_IN = "last_check_in_date"
    private val KEY_STREAK = "check_in_streak"
    private val KEY_MAX_STREAK = "max_check_in_streak"
    private val KEY_CHECK_IN_DATES = "check_in_dates"

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in)

        sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)

        initViews()
        setupToolbar()
        loadAdBanner()
        updateUI()
    }

    private fun initViews() {
        txtStreakCount = findViewById(R.id.txtStreakCount)
        txtStreakLabel = findViewById(R.id.txtStreakLabel)
        btnCheckIn = findViewById(R.id.btnCheckIn)

        // Initialize calendar view lists
        for (i in 1..7) {
            val nameId = resources.getIdentifier("txtDayName$i", "id", packageName)
            val circleId = resources.getIdentifier("frameDayCircle$i", "id", packageName)
            val numId = resources.getIdentifier("txtDayNum$i", "id", packageName)
            val checkId = resources.getIdentifier("imgDayCheck$i", "id", packageName)

            dayNameViews.add(findViewById(nameId))
            dayCircleViews.add(findViewById(circleId))
            dayNumViews.add(findViewById(numId))
            dayCheckViews.add(findViewById(checkId))
        }

        // Initialize badge lists
        for (i in 1..4) {
            val cardId = resources.getIdentifier("cardBadge$i", "id", packageName)
            val iconId = resources.getIdentifier("imgBadge$i", "id", packageName)
            val statusId = resources.getIdentifier("txtBadgeStatus$i", "id", packageName)

            badgeCards.add(findViewById(cardId))
            badgeIcons.add(findViewById(iconId))
            badgeStatuses.add(findViewById(statusId))
        }

        btnCheckIn.setOnClickListener {
            performCheckIn()
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

    private fun updateUI() {
        val currentStreak = sharedPreferences.getInt(KEY_STREAK, 0)
        val maxStreak = sharedPreferences.getInt(KEY_MAX_STREAK, 0)
        val checkInDates = sharedPreferences.getStringSet(KEY_CHECK_IN_DATES, emptySet()) ?: emptySet()

        // Streak Count
        txtStreakCount.text = currentStreak.toString()
        txtStreakLabel.text = String.format(getString(R.string.check_in_streak_format), currentStreak)

        // Check-in Button state for today
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val todayStr = dateFormat.format(Calendar.getInstance().time)

        if (checkInDates.contains(todayStr)) {
            btnCheckIn.isEnabled = false
            btnCheckIn.text = getString(R.string.checked_in)
            btnCheckIn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#BDC3C7")) // gray
        } else {
            btnCheckIn.isEnabled = true
            btnCheckIn.text = getString(R.string.check_in_now)
            btnCheckIn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#2E7D32")) // green
        }

        // Draw Weekly Calendar
        setupWeeklyCalendar(checkInDates, todayStr)

        // Check Achievements
        setupAchievements(maxStreak)
    }

    private fun setupWeeklyCalendar(checkInDates: Set<String>, todayStr: String) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val dayOfWeekFormat = SimpleDateFormat("EEE", Locale.getDefault())

        // Calculate Monday of the current week
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val daysToSubtract = if (dayOfWeek == Calendar.SUNDAY) 6 else dayOfWeek - Calendar.MONDAY
        calendar.add(Calendar.DAY_OF_YEAR, -daysToSubtract)

        for (i in 0 until 7) {
            val dateStr = dateFormat.format(calendar.time)
            val dayName = dayOfWeekFormat.format(calendar.time)
            val dayNum = calendar.get(Calendar.DAY_OF_MONTH).toString()

            dayNameViews[i].text = dayName
            dayNumViews[i].text = dayNum

            if (checkInDates.contains(dateStr)) {
                // Checked In
                dayCheckViews[i].visibility = View.VISIBLE
                dayNumViews[i].visibility = View.GONE
                dayCircleViews[i].setBackgroundResource(android.R.color.transparent)
            } else {
                // Unchecked
                dayCheckViews[i].visibility = View.GONE
                dayNumViews[i].visibility = View.VISIBLE

                if (dateStr == todayStr) {
                    // Today
                    dayCircleViews[i].setBackgroundResource(R.drawable.circle_today_border)
                    dayNumViews[i].setTextColor(Color.parseColor("#E87A8D")) // toolBarColor/Accent
                } else {
                    // Other days
                    dayCircleViews[i].setBackgroundResource(R.drawable.circle_gray_border)
                    dayNumViews[i].setTextColor(Color.parseColor("#555555"))
                }
            }

            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
    }

    private fun setupAchievements(maxStreak: Int) {
        // Thresholds: 3, 7, 15, 30
        val thresholds = intArrayOf(3, 7, 15, 30)

        for (i in 0 until 4) {
            val isUnlocked = maxStreak >= thresholds[i]
            val card = badgeCards[i]
            val icon = badgeIcons[i]
            val status = badgeStatuses[i]

            if (isUnlocked) {
                card.alpha = 1.0f
                icon.alpha = 1.0f
                status.text = getString(R.string.unlocked)
                status.setTextColor(Color.parseColor("#2E7D32")) // Green
            } else {
                card.alpha = 0.5f
                icon.alpha = 0.3f
                status.text = getString(R.string.locked)
                status.setTextColor(Color.parseColor("#E87A8D")) // Pink/Red accent
            }
        }
    }

    private fun performCheckIn() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val calendar = Calendar.getInstance()
        val todayStr = dateFormat.format(calendar.time)

        calendar.add(Calendar.DAY_OF_YEAR, -1)
        val yesterdayStr = dateFormat.format(calendar.time)

        var currentStreak = sharedPreferences.getInt(KEY_STREAK, 0)
        val lastCheckIn = sharedPreferences.getString(KEY_LAST_CHECK_IN, "") ?: ""

        if (lastCheckIn == yesterdayStr) {
            currentStreak += 1
        } else if (lastCheckIn == todayStr) {
            // Already checked in today, do nothing (should be blocked by disabled button but check as fallback)
            return
        } else {
            currentStreak = 1
        }

        val maxStreak = sharedPreferences.getInt(KEY_MAX_STREAK, 0)
        val newMaxStreak = maxOf(currentStreak, maxStreak)

        val checkInDates = HashSet(sharedPreferences.getStringSet(KEY_CHECK_IN_DATES, emptySet()) ?: emptySet())
        checkInDates.add(todayStr)

        sharedPreferences.edit().apply {
            putInt(KEY_STREAK, currentStreak)
            putInt(KEY_MAX_STREAK, newMaxStreak)
            putString(KEY_LAST_CHECK_IN, todayStr)
            putStringSet(KEY_CHECK_IN_DATES, checkInDates)
            apply()
        }

        // Haptic feedback
        triggerVibration()

        // Toast feedback
        Toast.makeText(
            this,
            String.format(getString(R.string.check_in_success_toast), currentStreak),
            Toast.LENGTH_LONG
        ).show()

        // Refresh UI
        updateUI()
    }

    private fun triggerVibration() {
        try {
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(300)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
