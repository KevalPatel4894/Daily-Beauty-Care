package com.kp.beautytips.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.kp.beautytips.R
import com.kp.beautytips.adapter.CategoryAdapter
import com.kp.beautytips.model.CategoryModel
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import com.kp.beautytips.utils.Constants
import com.kp.beautytips.utils.DailyTipScheduler
import com.kp.beautytips.utils.ReminderScheduler
import com.kp.beautytips.worker.DailyTipWorker
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.RingtoneManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import com.kp.beautytips.data.TipRepository
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import android.widget.Toast
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale



class MainActivity : BaseActivity(), CategoryAdapter.OnItemClick {
    private var adRequest: AdRequest? = null
    private var mInterstitialAd: InterstitialAd? = null
    private var mAdIsLoading: Boolean = false
    private lateinit var toolbar: Toolbar

    private lateinit var appUpdateManager: AppUpdateManager
    private val UPDATE_REQUEST_CODE = 12301

    // Shake Suggestion variables
    private var sensorManager: SensorManager? = null
    private var accelerometer: Sensor? = null
    private var lastShakeTime: Long = 0
    private val SHAKE_THRESHOLD = 1.8f
    private val SHAKE_COOLDOWN_MS = 2000

    // Notification permission launcher (Android 13+)
    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permission granted — schedule default reminders
            ReminderScheduler.scheduleReminder(this)
            DailyTipScheduler.scheduleDailyTip(this)
        } else {
            // Permission denied — turn off default reminder so Settings switch matches reality
            getSharedPreferences(ReminderScheduler.PREFS_NAME, Context.MODE_PRIVATE)
                .edit().putBoolean(ReminderScheduler.KEY_REMINDER_ENABLED, false).apply()
        }
        // Mark that we have already asked so we never ask again
        getSharedPreferences("app_launch_prefs", Context.MODE_PRIVATE)
            .edit().putBoolean("notification_permission_asked", true).apply()
    }

    private val sensorListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if (event == null || event.sensor.type != Sensor.TYPE_ACCELEROMETER) return
            
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            
            val gForce = Math.sqrt((x * x + y * y + z * z).toDouble()) / SensorManager.GRAVITY_EARTH
            if (gForce > SHAKE_THRESHOLD) {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastShakeTime > SHAKE_COOLDOWN_MS) {
                    lastShakeTime = currentTime
                    triggerShakeSuggestion()
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }


    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appUpdateManager = AppUpdateManagerFactory.create(this)
        checkForAppUpdate()

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        // Request POST_NOTIFICATIONS on Android 13+ on first launch
        requestNotificationPermissionIfNeeded()

        init()
    }

    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            // Below Android 13 — no runtime permission needed, just schedule
            ReminderScheduler.scheduleReminder(this)
            DailyTipScheduler.scheduleDailyTip(this)
            return
        }

        val launchPrefs = getSharedPreferences("app_launch_prefs", Context.MODE_PRIVATE)
        val alreadyAsked = launchPrefs.getBoolean("notification_permission_asked", false)
        if (alreadyAsked) {
            // Already asked before — if granted now, re-schedule (handles app reinstalls)
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED) {
                ReminderScheduler.scheduleReminder(this)
                DailyTipScheduler.scheduleDailyTip(this)
            }
            return
        }

        // First launch — check if permission is already granted (rare but possible)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
            == PackageManager.PERMISSION_GRANTED) {
            ReminderScheduler.scheduleReminder(this)
            DailyTipScheduler.scheduleDailyTip(this)
            launchPrefs.edit().putBoolean("notification_permission_asked", true).apply()
        } else {
            // Ask the user — system dialog will appear
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun init() {
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        val rvCategory = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rvCategory)
        val imgSetting = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.imgSetting)

        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        adRequest = AdRequest.Builder()
            .build()

        val adContainer = findViewById<View>(R.id.ad_view)
        val mAdView = AdView(this)
        mAdView.setAdSize(AdSize.BANNER)
        mAdView.adUnitId = getString(R.string.banner_home_footer)
        (adContainer as RelativeLayout).addView(mAdView)
        mAdView.loadAd(adRequest!!)

        loadFullScreenAds()

        val category = ArrayList<CategoryModel>()
        val categoryModel = CategoryModel()
        categoryModel.categoryName = getString(R.string.face)
        categoryModel.colorCode = "#F0FBFF"
        categoryModel.viewColorCode = "#BEF0F7"
        categoryModel.image = R.drawable.ic_face
        category.add(categoryModel)
        val categoryModel1 = CategoryModel()
        categoryModel1.categoryName = getString(R.string.hair)
        categoryModel1.colorCode = "#FEF3EF"
        categoryModel1.viewColorCode = "#F7DBD7"
        categoryModel1.image = R.drawable.ic_hair
        category.add(categoryModel1)
        val categoryModel2 = CategoryModel()
        categoryModel2.categoryName = getString(R.string.skin)
        categoryModel2.colorCode = "#F4F6F6"
        categoryModel2.viewColorCode = "#D5DBDB"
        categoryModel2.image = R.drawable.ic_skin
        category.add(categoryModel2)
        val categoryModel3 = CategoryModel()
        categoryModel3.categoryName = getString(R.string.eyes)
        categoryModel3.colorCode = "#EFFBD7"
        categoryModel3.viewColorCode = "#E5F7A3"
        categoryModel3.image = R.drawable.beautifuleyes_title
        category.add(categoryModel3)
        val categoryModel4 = CategoryModel()
        categoryModel4.categoryName = getString(R.string.handsfeet)
        categoryModel4.colorCode = "#FBEEE6"
        categoryModel4.viewColorCode = "#EDBB99"
        categoryModel4.image = R.drawable.ic_hand_feet
        category.add(categoryModel4)

        rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvCategory.adapter = CategoryAdapter(category, this)

        val cardChallengesBanner = findViewById<View>(R.id.cardChallengesBanner)
        cardChallengesBanner.setOnClickListener {
            Intent(this, ChallengesActivity::class.java).also {
                startActivity(it)
                AppUtils.startFromRightToLeft(this)
            }
        }

        val cardCheckInBanner = findViewById<View>(R.id.cardCheckInBanner)
        cardCheckInBanner.setOnClickListener {
            Intent(this, CheckInActivity::class.java).also {
                startActivity(it)
                AppUtils.startFromRightToLeft(this)
            }
        }

        val cardWaterTrackerBanner = findViewById<View>(R.id.cardWaterTrackerBanner)
        cardWaterTrackerBanner.setOnClickListener {
            Intent(this, WaterTrackerActivity::class.java).also {
                startActivity(it)
                AppUtils.startFromRightToLeft(this)
            }
        }

        val imgSearch = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.imgSearch)
        imgSearch.setOnClickListener {
            Intent(this, SearchActivity::class.java).also {
                startActivity(it)
                AppUtils.startFromRightToLeft(this)
            }
        }

        imgSetting.setOnClickListener { Intent(this, SettingActivity::class.java).also {
            startActivity(it)
            AppUtils.startFromRightToLeft(this)
        } }
    }

    override fun onItemClick(category: CategoryModel, position: Int) {
        Intent(this, SubCategoryActivity::class.java).also {
            it.putExtra("position", position)
            it.putExtra("categoryName", category.categoryName)
            startActivity(it)
            AppUtils.startFromRightToLeft(this)
        }
    }

    private fun loadFullScreenAds() {
        val sharedPreferences =
            getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if (sharedPreferences.getInt(Constants.FULL_ADS_COUNT, 0) == Constants.ADS_FULL_COUNTER) {
            editor.putInt(Constants.FULL_ADS_COUNT, 0).apply()
            if (!mAdIsLoading && mInterstitialAd == null) {
                mAdIsLoading = true
                loadAd()
            }
        } else {
            var getCount = sharedPreferences.getInt(Constants.FULL_ADS_COUNT, 0)
            getCount++
            editor.putInt(Constants.FULL_ADS_COUNT, getCount).apply()
        }
    }

    private fun loadAd() {
        InterstitialAd.load(
            this, getString(R.string.interstitial_full_screen), adRequest!!,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    AppUtils.logI(adError.message)
                    mInterstitialAd = null
                    mAdIsLoading = false
                    val error = "domain: ${adError.domain}, code: ${adError.code}, " +
                            "message: ${adError.message}"
                    AppUtils.logI("onAdFailedToLoad() with error $error")
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    AppUtils.logI("Ad was loaded.")
                    mInterstitialAd = interstitialAd
                    mAdIsLoading = false
                    AppUtils.logI("onAdLoaded()")
                    showInterstitial()
                }
            }
        )
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        }
    }

    private fun showInterstitial() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    AppUtils.logI("Ad was dismissed.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mInterstitialAd = null
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    AppUtils.logI("Ad failed to show.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mInterstitialAd = null
                }

                override fun onAdShowedFullScreenContent() {
                    AppUtils.logI("Ad showed fullscreen content.")
                    // Called when ad is dismissed.
                }
            }
            mInterstitialAd?.show(this)
        }
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            sensorManager?.registerListener(sensorListener, it, SensorManager.SENSOR_DELAY_UI)
        }

        // Update Daily Check-In status on banner
        try {
            val sharedPrefs = getSharedPreferences("DailyBeautyCarePrefs", Context.MODE_PRIVATE)
            val streak = sharedPrefs.getInt("check_in_streak", 0)
            val checkInDates = sharedPrefs.getStringSet("check_in_dates", emptySet()) ?: emptySet()
            val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Calendar.getInstance().time)
            val isCheckedInToday = checkInDates.contains(todayStr)

            val txtCheckInSubtitle = findViewById<AppCompatTextView>(R.id.txtCheckInSubtitle)
            if (isCheckedInToday) {
                txtCheckInSubtitle.text = String.format(getString(R.string.check_in_streak_format), streak)
            } else {
                txtCheckInSubtitle.text = getString(R.string.check_in_tap_short)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Update Water Tracker status on banner
        try {
            val waterPrefs = getSharedPreferences("water_tracker_prefs", Context.MODE_PRIVATE)
            val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Calendar.getInstance().time)
            val lastDate = waterPrefs.getString("water_last_date", "") ?: ""
            var count = waterPrefs.getInt("water_today_count", 0)
            if (todayStr != lastDate) {
                count = 0
                waterPrefs.edit().putInt("water_today_count", 0).putString("water_last_date", todayStr).apply()
            }
            
            val goal = waterPrefs.getInt("water_water_goal", 8)
            val txtWaterSubtitle = findViewById<AppCompatTextView>(R.id.txtWaterSubtitle)
            txtWaterSubtitle.text = String.format(getString(R.string.glasses_format), count, goal)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        // Resume in-progress update if any
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                try {
                    appUpdateManager.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.IMMEDIATE,
                        this,
                        UPDATE_REQUEST_CODE
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        
        updateWellnessScore()
    }

    override fun onPause() {
        sensorManager?.unregisterListener(sensorListener)
        super.onPause()
    }

    private fun triggerShakeSuggestion() {
        try {
            // Haptic Feedback (Vibration)
            try {
                val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    vibrator.vibrate(300)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Audio Feedback (Short notification beep)
            val notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(applicationContext, notificationUri)
            ringtone?.play()

            // Fetch dynamic tips
            val allTips = TipRepository.getAllTips(this)
            if (allTips.isNotEmpty()) {
                val randomTip = allTips.shuffled().first()
                
                Intent(this, DetailsActivity::class.java).also { intent ->
                    intent.putExtra("tabName", getString(R.string.daily_suggestion))
                    intent.putExtra("title", randomTip.title)
                    intent.putExtra("image", randomTip.image)
                    intent.putExtra("details", randomTip.details)
                    intent.putExtra("descriptionName", randomTip.descriptionName)
                    startActivity(intent)
                    AppUtils.startFromRightToLeft(this)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun checkForAppUpdate() {
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                try {
                    appUpdateManager.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.IMMEDIATE,
                        this,
                        UPDATE_REQUEST_CODE
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UPDATE_REQUEST_CODE) {
            if (resultCode != RESULT_OK) {
                Toast.makeText(this, "An update is required to continue using the app.", Toast.LENGTH_LONG).show()
                finishAffinity()
            }
        }
    }

    private fun updateWellnessScore() {
        val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Calendar.getInstance().time)

        // 1. Water Points (Max 25)
        val waterPrefs = getSharedPreferences("water_tracker_prefs", Context.MODE_PRIVATE)
        val lastDate = waterPrefs.getString("water_last_date", "") ?: ""
        var waterCount = waterPrefs.getInt("water_today_count", 0)
        if (todayStr != lastDate) {
            waterCount = 0
        }
        val waterGoal = waterPrefs.getInt("water_water_goal", 8)
        val waterPoints = if (waterGoal > 0) {
            (waterCount.toFloat() / waterGoal.toFloat() * 25).toInt().coerceIn(0, 25)
        } else {
            0
        }

        // 2. Check-In Streak Points (Max 25)
        val checkInPrefs = getSharedPreferences("DailyBeautyCarePrefs", Context.MODE_PRIVATE)
        val streak = checkInPrefs.getInt("check_in_streak", 0)
        val checkInDates = checkInPrefs.getStringSet("check_in_dates", emptySet()) ?: emptySet()
        val isCheckedInToday = checkInDates.contains(todayStr)
        val streakPoints = if (isCheckedInToday) {
            (15 + streak * 2).coerceAtMost(25)
        } else {
            (streak * 2).coerceAtMost(25)
        }

        // 3. Challenge Points (Max 25)
        val beautyPrefs = getSharedPreferences("beautytips_prefs", Context.MODE_PRIVATE)
        var totalCompletedTasks = 0
        var totalPossibleTasks = 0
        for (challenge in com.kp.beautytips.data.ChallengeRepository.challenges) {
            totalPossibleTasks += challenge.daysCount
            for (i in 0 until challenge.daysCount) {
                if (beautyPrefs.getBoolean("challenge_completed_${challenge.id}_$i", false)) {
                    totalCompletedTasks++
                }
            }
        }
        val challengePoints = if (totalPossibleTasks > 0) {
            (totalCompletedTasks.toFloat() / totalPossibleTasks.toFloat() * 25).toInt().coerceIn(0, 25)
        } else {
            0
        }

        // 4. Tips Read Points (Max 25)
        val readTipsSet = beautyPrefs.getStringSet("read_tips_$todayStr", emptySet()) ?: emptySet()
        val tipsReadToday = readTipsSet.size
        val tipsPoints = (tipsReadToday * 8).coerceAtMost(25)

        // Total Score (0-100)
        val totalScore = (waterPoints + streakPoints + challengePoints + tipsPoints).coerceIn(0, 100)

        // Update Toolbar Views
        try {
            val toolbarScoreProgress = findViewById<ProgressBar>(R.id.toolbarScoreProgress)
            val txtToolbarScore = findViewById<AppCompatTextView>(R.id.txtToolbarScore)
            toolbarScoreProgress?.progress = totalScore
            txtToolbarScore?.text = totalScore.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Set Click Listener to open Bottom Sheet Dialog with details
        val layoutToolbarScore = findViewById<View>(R.id.layoutToolbarScore)
        layoutToolbarScore?.setOnClickListener {
            showWellnessDetailsBottomSheet(
                totalScore,
                waterCount, waterGoal, waterPoints,
                streak, isCheckedInToday, streakPoints,
                totalCompletedTasks, totalPossibleTasks, challengePoints,
                tipsReadToday, tipsPoints
            )
        }
    }

    private fun showWellnessDetailsBottomSheet(
        totalScore: Int,
        waterCount: Int, waterGoal: Int, waterPoints: Int,
        streak: Int, isCheckedInToday: Boolean, streakPoints: Int,
        completedChallenges: Int, totalChallenges: Int, challengePoints: Int,
        tipsReadToday: Int, tipsPoints: Int
    ) {
        try {
            val bottomSheetDialog = com.google.android.material.bottomsheet.BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.dialog_wellness_score_detail, null)

            // Find views
            val detailScoreProgress = view.findViewById<ProgressBar>(R.id.detailScoreProgress)
            val txtDetailScore = view.findViewById<AppCompatTextView>(R.id.txtDetailScore)
            val txtDetailScoreStatus = view.findViewById<AppCompatTextView>(R.id.txtDetailScoreStatus)

            val txtWaterProgress = view.findViewById<AppCompatTextView>(R.id.txtWaterProgress)
            val imgCheckWater = view.findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.imgCheckWater)

            val txtStreakProgress = view.findViewById<AppCompatTextView>(R.id.txtStreakProgress)
            val imgCheckStreak = view.findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.imgCheckStreak)

            val txtChallengeProgress = view.findViewById<AppCompatTextView>(R.id.txtChallengeProgress)
            val imgCheckChallenge = view.findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.imgCheckChallenge)

            val txtReadsProgress = view.findViewById<AppCompatTextView>(R.id.txtReadsProgress)
            val txtReadsAdvice = view.findViewById<AppCompatTextView>(R.id.txtReadsAdvice)
            val imgCheckReads = view.findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.imgCheckReads)

            val btnCloseDetail = view.findViewById<View>(R.id.btnCloseDetail)

            // Set main score info
            detailScoreProgress.progress = totalScore
            txtDetailScore.text = totalScore.toString()
            val statusRes = when {
                totalScore >= 75 -> R.string.wellness_status_high
                totalScore >= 40 -> R.string.wellness_status_medium
                else -> R.string.wellness_status_low
            }
            txtDetailScoreStatus.setText(statusRes)

            // Bind Water component
            txtWaterProgress.text = String.format(getString(R.string.wellness_score_water_label), waterCount, waterGoal)
            if (waterPoints >= 25) {
                imgCheckWater.setImageResource(R.drawable.ic_check_circle_green)
                imgCheckWater.imageTintList = null
            } else {
                imgCheckWater.setImageResource(R.drawable.ic_check_circle)
                imgCheckWater.setColorFilter(android.graphics.Color.parseColor("#CCCCCC"))
            }

            // Bind Streak component
            txtStreakProgress.text = String.format(getString(R.string.wellness_score_streak_label), streak)
            if (streakPoints >= 25) {
                imgCheckStreak.setImageResource(R.drawable.ic_check_circle_green)
                imgCheckStreak.imageTintList = null
            } else {
                imgCheckStreak.setImageResource(R.drawable.ic_check_circle)
                imgCheckStreak.setColorFilter(android.graphics.Color.parseColor("#CCCCCC"))
            }

            // Bind Challenge component
            txtChallengeProgress.text = String.format(getString(R.string.wellness_score_challenges_label), completedChallenges, totalChallenges)
            if (challengePoints >= 25) {
                imgCheckChallenge.setImageResource(R.drawable.ic_check_circle_green)
                imgCheckChallenge.imageTintList = null
            } else {
                imgCheckChallenge.setImageResource(R.drawable.ic_check_circle)
                imgCheckChallenge.setColorFilter(android.graphics.Color.parseColor("#CCCCCC"))
            }

            // Bind Reads component
            txtReadsProgress.text = String.format(getString(R.string.wellness_score_reads_label), tipsReadToday, 3)
            txtReadsAdvice.text = String.format(getString(R.string.wellness_score_detail_reads_advice), 3)
            if (tipsPoints >= 25) {
                imgCheckReads.setImageResource(R.drawable.ic_check_circle_green)
                imgCheckReads.imageTintList = null
            } else {
                imgCheckReads.setImageResource(R.drawable.ic_check_circle)
                imgCheckReads.setColorFilter(android.graphics.Color.parseColor("#CCCCCC"))
            }

            btnCloseDetail.setOnClickListener {
                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}