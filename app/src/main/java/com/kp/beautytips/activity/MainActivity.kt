package com.kp.beautytips.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.kp.beautytips.R
import com.kp.beautytips.adapter.CategoryAdapter
import com.kp.beautytips.model.CategoryModel
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import com.kp.beautytips.utils.Constants
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
        
        init()
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
}