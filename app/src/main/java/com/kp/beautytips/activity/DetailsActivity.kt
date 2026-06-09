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
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.LayoutInflater
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

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

    // TTS variables
    private var textToSpeech: TextToSpeech? = null
    private var isTtsSpeaking = false

    // Challenge variables
    private var challengeId: String = ""
    private var dayIndex: Int = -1
    private var isChallengeTaskCompleted: Boolean = false

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
        val lrMainShare = findViewById<View>(R.id.lrMainShare)

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
            challengeId = bundle.getString("challenge_id", "")
            dayIndex = bundle.getInt("day_index", -1)
            isChallengeTaskCompleted = bundle.getBoolean("challenge_task_completed", false)
            
            txtTabTitle.text = tabName
            txtTitle.text = title
            txtDetails.text = details

            Glide.with(activity).load(image).into(img)

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

        val lrAudio = findViewById<View>(R.id.lrAudio)
        val imgAudio = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.imgAudio)

        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val currentLocale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    resources.configuration.locales.get(0)
                } else {
                    @Suppress("DEPRECATION")
                    resources.configuration.locale
                } ?: Locale.getDefault()
                
                var result = textToSpeech?.setLanguage(currentLocale)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    AppUtils.logI("TTS language $currentLocale is not supported or missing data, falling back to English")
                    result = textToSpeech?.setLanguage(Locale.ENGLISH)
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        AppUtils.logI("Fallback English language is also not supported")
                    }
                }
                
                textToSpeech?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onStart(utteranceId: String?) {
                        runOnUiThread {
                            isTtsSpeaking = true
                            imgAudio.setImageResource(R.drawable.ic_pause)
                        }
                    }

                    override fun onDone(utteranceId: String?) {
                        runOnUiThread {
                            isTtsSpeaking = false
                            imgAudio.setImageResource(R.drawable.ic_volume_up)
                        }
                    }

                    @Deprecated("Deprecated in Java")
                    override fun onError(utteranceId: String?) {
                        runOnUiThread {
                            isTtsSpeaking = false
                            imgAudio.setImageResource(R.drawable.ic_volume_up)
                        }
                    }

                    override fun onError(utteranceId: String?, errorCode: Int) {
                        runOnUiThread {
                            isTtsSpeaking = false
                            imgAudio.setImageResource(R.drawable.ic_volume_up)
                        }
                    }
                })
            } else {
                AppUtils.logI("TTS Initialization failed")
            }
        }

        lrAudio.setOnClickListener {
            if (isTtsSpeaking) {
                stopSpeech(imgAudio)
            } else {
                startSpeech(imgAudio)
            }
        }

        lrCopy.setOnClickListener {
            copyToClipboard(details)
        }

        lrShare.setOnClickListener { showShareChooserDialog(null) }
        lrWhatShare.setOnClickListener { showShareChooserDialog("com.whatsapp") }

        val btnCompleteChallenge = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnCompleteChallenge)
        if (challengeId.isNotEmpty() && !isChallengeTaskCompleted && dayIndex != -1) {
            lrMainShare.visibility = View.GONE
            btnCompleteChallenge.visibility = View.VISIBLE
            btnCompleteChallenge.setOnClickListener {
                markChallengeTaskCompleted()
            }
        } else {
            lrMainShare.visibility = View.VISIBLE
            btnCompleteChallenge.visibility = View.GONE
        }
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
                btnStart.text = getString(R.string.start)
                isTimerRunning = false
                triggerAlarm()
            }
        }.start()

        btnStart.text = getString(R.string.pause)
        isTimerRunning = true
    }

    private fun pauseTimer(btnStart: androidx.appcompat.widget.AppCompatButton) {
        countDownTimer?.cancel()
        btnStart.text = getString(R.string.start)
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
        btnStart.text = getString(R.string.start)
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

            Toast.makeText(this, getString(R.string.timer_finished_toast), Toast.LENGTH_LONG).show()
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

    private fun startSpeech(imgAudio: androidx.appcompat.widget.AppCompatImageView) {
        if (textToSpeech == null) {
            Toast.makeText(this, getString(R.string.audio_reader_not_ready_toast), Toast.LENGTH_SHORT).show()
            return
        }
        val speechText = "$title. $details"
        val params = Bundle()
        params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "remedyTextId")
        val speakResult = textToSpeech?.speak(speechText, TextToSpeech.QUEUE_FLUSH, params, "remedyTextId")
        if (speakResult == TextToSpeech.ERROR) {
            Toast.makeText(this, getString(R.string.audio_reader_error_toast), Toast.LENGTH_SHORT).show()
        } else {
            isTtsSpeaking = true
            imgAudio.setImageResource(R.drawable.ic_pause)
        }
    }

    private fun stopSpeech(imgAudio: androidx.appcompat.widget.AppCompatImageView) {
        textToSpeech?.stop()
        isTtsSpeaking = false
        imgAudio.setImageResource(R.drawable.ic_volume_up)
    }

    override fun onPause() {
        if (isTtsSpeaking) {
            val imgAudio = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.imgAudio)
            stopSpeech(imgAudio)
        }
        super.onPause()
    }

    override fun onDestroy() {
        countDownTimer?.cancel()
        stopAlarm()
        if (textToSpeech != null) {
            textToSpeech?.stop()
            textToSpeech?.shutdown()
        }
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
                Toast.makeText(this, getString(R.string.favorites_removed_toast), Toast.LENGTH_SHORT).show()
            } else {
                editor.putBoolean("fav_" + title, true).apply()
                item.setIcon(R.drawable.ic_favorite)
                Toast.makeText(this, getString(R.string.favorites_saved_toast), Toast.LENGTH_SHORT).show()
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

    private fun showShareChooserDialog(targetPackage: String?) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_share_chooser, null)
        val dialog = android.app.Dialog(this)
        dialog.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogView)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val cardShareImageOption = dialogView.findViewById<com.google.android.material.card.MaterialCardView>(R.id.cardShareImageOption)
        val cardShareTextOption = dialogView.findViewById<com.google.android.material.card.MaterialCardView>(R.id.cardShareTextOption)
        val btnCancelShare = dialogView.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnCancelShare)

        cardShareImageOption.setOnClickListener {
            dialog.dismiss()
            shareAsImageCard(targetPackage)
        }

        cardShareTextOption.setOnClickListener {
            dialog.dismiss()
            if (targetPackage == "com.whatsapp") {
                shareContentOnlyWhatsapp()
            } else {
                shareContent()
            }
        }

        btnCancelShare.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.setLayout(
            android.view.ViewGroup.LayoutParams.MATCH_PARENT,
            android.view.ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun shareAsImageCard(targetPackage: String?) {
        try {
            val bitmap = generateShareCardBitmap()
            val uri = saveBitmapToCache(bitmap)
            if (uri != null) {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "image/png"
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
                val caption = title + "\n\n" + getString(R.string.app_share) + ": http://play.google.com/store/apps/details?id=" + packageName
                shareIntent.putExtra(Intent.EXTRA_TEXT, caption)
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                
                if (targetPackage != null) {
                    shareIntent.setPackage(targetPackage)
                    try {
                        startActivity(shareIntent)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(this, getString(R.string.whatsapp_not_installed), Toast.LENGTH_SHORT).show()
                    }
                } else {
                    startActivity(Intent.createChooser(shareIntent, getString(R.string.share)))
                }
            } else {
                Toast.makeText(this, getString(R.string.share_card_failed_toast), Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            AppUtils.logI("Error sharing image card: " + e.message)
            Toast.makeText(this, getString(R.string.share_image_error_toast, e.message ?: ""), Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateShareCardBitmap(): Bitmap {
        val inflater = LayoutInflater.from(this)
        val cardView = inflater.inflate(R.layout.layout_share_card, null)

        val txtShareCategory = cardView.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtShareCategory)
        val txtShareTitle = cardView.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtShareTitle)
        val txtShareDetails = cardView.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtShareDetails)
        val imgShare = cardView.findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.imgShare)
        val cardShareImage = cardView.findViewById<androidx.cardview.widget.CardView>(R.id.cardShareImage)

        txtShareCategory.text = tabName.uppercase(Locale.getDefault())
        txtShareTitle.text = title
        txtShareDetails.text = details

        if (image != 0) {
            imgShare.setImageResource(image)
            cardShareImage.visibility = View.VISIBLE
        } else {
            cardShareImage.visibility = View.GONE
        }

        val density = resources.displayMetrics.density
        val widthPx = (600 * density).toInt()
        val widthSpec = View.MeasureSpec.makeMeasureSpec(widthPx, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)

        cardView.measure(widthSpec, heightSpec)
        cardView.layout(0, 0, cardView.measuredWidth, cardView.measuredHeight)

        val bitmap = Bitmap.createBitmap(cardView.measuredWidth, cardView.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        cardView.draw(canvas)

        return bitmap
    }

    private fun saveBitmapToCache(bitmap: Bitmap): Uri? {
        try {
            val cachePath = File(cacheDir, "shared_images")
            if (!cachePath.exists()) {
                cachePath.mkdirs()
            }
            // Clean up older cache files
            val files = cachePath.listFiles()
            if (files != null) {
                for (f in files) {
                    f.delete()
                }
            }
            val file = File(cachePath, "share_remedy_${System.currentTimeMillis()}.png")
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.flush()
            stream.close()
            return FileProvider.getUriForFile(this, "$packageName.fileprovider", file)
        } catch (e: IOException) {
            AppUtils.logI("Failed to save bitmap: " + e.message)
            return null
        }
    }

    private fun markChallengeTaskCompleted() {
        val prefs = getSharedPreferences("beautytips_prefs", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("challenge_completed_${challengeId}_$dayIndex", true).apply()
        
        try {
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(150)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        Toast.makeText(this, getString(R.string.challenge_day_completed_toast, dayIndex + 1), Toast.LENGTH_SHORT).show()
        
        finish()
        AppUtils.finishFromLeftToRight(activity)
    }
}