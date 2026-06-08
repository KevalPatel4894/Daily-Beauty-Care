package com.kp.beautytips.activity

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.kp.beautytips.R
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import com.kp.beautytips.utils.Constants
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.util.Locale

class DetailsActivity : BaseActivity() {
    private var tabName: String = ""
    private var title: String = ""
    private var details: String = ""
    private var image: Int = 0
    private var adRequest: AdRequest? = null
    private var mInterstitialAd: InterstitialAd? = null
    private var mAdIsLoading: Boolean = false

    // Timer variables
    private var durationText: String = ""
    private var totalTimeInMillis: Long = 0
    private var timeRemainingInMillis: Long = 0
    private var countDownTimer: CountDownTimer? = null
    private var isTimerRunning = false
    private var ringtone: Ringtone? = null

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        init()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun init() {
        val txtTabTitle = findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtTabTitle)
        val txtTitle = findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtTitle)
        val txtDetails = findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtDetails)
        val img = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.img)
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        val lrCopy = findViewById<View>(R.id.lrCopy)
        val lrShare = findViewById<View>(R.id.lrShare)
        val lrWhatShare = findViewById<View>(R.id.lrWhatShare)

        // Timer views
        val cardTimer = findViewById<CardView>(R.id.cardTimer)
        val txtTimerDuration = findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtTimerDuration)
        val progressTimer = findViewById<ProgressBar>(R.id.progressTimer)
        val btnTimerStart = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnTimerStart)
        val btnTimerReset = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnTimerReset)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            tabName = bundle.getString("tabName").toString()
            title = bundle.getString("title").toString()
            image = bundle.getInt("image")
            details = bundle.getString("details").toString()
            durationText = bundle.getString("descriptionName").toString()
            
            txtTabTitle.text = tabName
            txtTitle.text = title
            txtDetails.text = details

            Glide.with(activity).load(
                activity.getDrawable(
                    image
                )
            ).into(img)

            // Setup timer if a valid duration exists
            val parsedMinutes = extractMinutes(durationText)
            if (parsedMinutes != null && parsedMinutes > 0) {
                totalTimeInMillis = parsedMinutes * 60 * 1000L
                timeRemainingInMillis = totalTimeInMillis
                cardTimer.visibility = View.VISIBLE
                updateTimerText(timeRemainingInMillis, txtTimerDuration)

                btnTimerStart.setOnClickListener {
                    if (isTimerRunning) {
                        pauseTimer(btnTimerStart)
                    } else {
                        startTimer(txtTimerDuration, progressTimer, btnTimerStart)
                    }
                }

                btnTimerReset.setOnClickListener {
                    resetTimer(txtTimerDuration, progressTimer, btnTimerStart)
                }
            }
        }
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        adRequest = AdRequest.Builder()
            .build()

        val adContainer = findViewById<View>(R.id.ad_view)
        val mAdView = AdView(this)
        mAdView.setAdSize(AdSize.BANNER)
        mAdView.adUnitId = getString(R.string.banner_home_footer)
        (adContainer as RelativeLayout).addView(mAdView)
        mAdView.loadAd(adRequest!!)

        loadFullScreenAds()

        lrCopy.setOnClickListener {
            copyToClipboard(details)
        }

        lrShare.setOnClickListener { shareContent() }
        lrWhatShare.setOnClickListener { shareContentOnlyWhatsapp() }
    }

    private fun extractMinutes(durationText: String): Int? {
        val clean = durationText.lowercase(Locale.getDefault())
        
        // Handle ranges like "05-15 m" or "10-15 Min"
        val rangePattern = Regex("(\\d+)\\s*-\\s*(\\d+)\\s*(min|m|minute|minutes|मिनट)")
        val rangeMatch = rangePattern.find(clean)
        if (rangeMatch != null) {
            return rangeMatch.groupValues[2].toInt()
        }

        // Handle single numeric values
        val pattern = Regex("(\\d+)\\s*(min|m|minute|minutes|मिनट|minuto|minutos)")
        val match = pattern.find(clean)
        if (match != null) {
            return match.groupValues[1].toInt()
        }
        return null
    }

    private fun updateTimerText(millisUntilFinished: Long, textView: androidx.appcompat.widget.AppCompatTextView) {
        val seconds = (millisUntilFinished / 1000) % 60
        val minutes = (millisUntilFinished / 1000) / 60
        val timeStr = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        textView.text = timeStr
    }

    private fun startTimer(
        txtDuration: androidx.appcompat.widget.AppCompatTextView,
        progressBar: ProgressBar,
        btnStart: androidx.appcompat.widget.AppCompatButton
    ) {
        stopAlarm()

        countDownTimer = object : CountDownTimer(timeRemainingInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemainingInMillis = millisUntilFinished
                updateTimerText(timeRemainingInMillis, txtDuration)
                val progress = ((timeRemainingInMillis.toFloat() / totalTimeInMillis.toFloat()) * 100).toInt()
                progressBar.progress = progress
            }

            override fun onFinish() {
                timeRemainingInMillis = 0
                updateTimerText(0, txtDuration)
                progressBar.progress = 0
                btnStart.text = "Start"
                isTimerRunning = false
                triggerAlarm()
            }
        }.start()

        btnStart.text = "Pause"
        isTimerRunning = true
    }

    private fun pauseTimer(btnStart: androidx.appcompat.widget.AppCompatButton) {
        countDownTimer?.cancel()
        btnStart.text = "Start"
        isTimerRunning = false
    }

    private fun resetTimer(
        txtDuration: androidx.appcompat.widget.AppCompatTextView,
        progressBar: ProgressBar,
        btnStart: androidx.appcompat.widget.AppCompatButton
    ) {
        countDownTimer?.cancel()
        stopAlarm()
        timeRemainingInMillis = totalTimeInMillis
        updateTimerText(timeRemainingInMillis, txtDuration)
        progressBar.progress = 100
        btnStart.text = "Start"
        isTimerRunning = false
    }

    private fun triggerAlarm() {
        try {
            val alarmUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            ringtone = RingtoneManager.getRingtone(applicationContext, alarmUri)
            ringtone?.play()

            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                vibrator.vibrate(1000)
            }

            Toast.makeText(this, "Timer finished! Time to wash off.", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun stopAlarm() {
        try {
            if (ringtone?.isPlaying == true) {
                ringtone?.stop()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        countDownTimer?.cancel()
        stopAlarm()
        super.onDestroy()
    }

    private fun copyToClipboard(copyText: String?) {
        val clipboard =
            getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = ClipData.newPlainText(getString(R.string.copy_content), copyText)
        clipboard.setPrimaryClip(clip)
        val toast = Toast.makeText(
            applicationContext,
            getString(R.string.copy_clipboard), Toast.LENGTH_SHORT
        )
        toast.show()
    }

    private fun shareContent() {
        val text =
            title + " :\n\n" + details + "\n\n" + getString(R.string.app_share) + ": http://play.google.com/store/apps/details?id=" + packageName
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share)))
    }

    private fun shareContentOnlyWhatsapp() {
        val text =
            title + " :\n\n" + details + "\n\n" + getString(R.string.app_share) + ": http://play.google.com/store/apps/details?id=" + packageName

        val whatsappIntent = Intent(Intent.ACTION_SEND)
        whatsappIntent.type = "text/plain"
        whatsappIntent.setPackage("com.whatsapp")
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, text)
        try {
            activity.startActivity(whatsappIntent)
        } catch (ex: ActivityNotFoundException) {
            val toast = Toast.makeText(
                applicationContext,
                getString(R.string.whatsapp_not_installed), Toast.LENGTH_SHORT
            )
            toast.show()
        }
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        val favoriteItem = menu?.findItem(R.id.action_favorite)
        val sharedPreferences = getSharedPreferences("beautytips_prefs", Context.MODE_PRIVATE)
        val isFav = sharedPreferences.getBoolean("fav_" + title, false)
        if (isFav) {
            favoriteItem?.setIcon(R.drawable.ic_favorite)
        } else {
            favoriteItem?.setIcon(R.drawable.ic_favorite_border)
        }
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            val sharedPreferences = getSharedPreferences("beautytips_prefs", Context.MODE_PRIVATE)
            val isFav = sharedPreferences.getBoolean("fav_" + title, false)
            val editor = sharedPreferences.edit()
            if (isFav) {
                editor.putBoolean("fav_" + title, false).apply()
                item.setIcon(R.drawable.ic_favorite_border)
                Toast.makeText(this, "Removed from Favorites", Toast.LENGTH_SHORT).show()
            } else {
                editor.putBoolean("fav_" + title, true).apply()
                item.setIcon(R.drawable.ic_favorite)
                Toast.makeText(this, "Saved to Favorites", Toast.LENGTH_SHORT).show()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(activity)
        return true
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
                    mInterstitialAd = null
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    AppUtils.logI("Ad failed to show.")
                    mInterstitialAd = null
                }

                override fun onAdShowedFullScreenContent() {
                    AppUtils.logI("Ad showed fullscreen content.")
                }
            }
            mInterstitialAd?.show(this)
        }
    }
}