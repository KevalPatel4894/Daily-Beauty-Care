package com.kp.beautytips.activity

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.content.FileProvider
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.kp.beautytips.R
import com.kp.beautytips.data.CustomTipDbHelper
import com.kp.beautytips.model.CustomTip
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import com.kp.beautytips.utils.Constants
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Locale

class CustomTipDetailsActivity : BaseActivity() {

    private lateinit var tip: CustomTip
    private lateinit var dbHelper: CustomTipDbHelper

    private var adRequest: AdRequest? = null
    private var mInterstitialAd: InterstitialAd? = null
    private var mAdIsLoading: Boolean = false

    // Timer variables
    private var totalTimeInMillis: Long = 0
    private var timeRemainingInMillis: Long = 0
    private var countDownTimer: CountDownTimer? = null
    private var isTimerRunning = false
    private var ringtone: Ringtone? = null

    // TTS variables
    private var textToSpeech: TextToSpeech? = null
    private var isTtsSpeaking = false

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_tip_details)

        dbHelper = CustomTipDbHelper(this)
        
        val serializableTip = intent.getSerializableExtra("custom_tip")
        if (serializableTip is CustomTip) {
            tip = serializableTip
            init()
        } else {
            Toast.makeText(this, "Failed to load recipe details", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        val serializableTip = intent?.getSerializableExtra("custom_tip")
        if (serializableTip is CustomTip) {
            tip = serializableTip
            
            // Re-bind views with updated data
            findViewById<AppCompatTextView>(R.id.txtTitle).text = tip.title
            findViewById<AppCompatTextView>(R.id.txtDetails).text = tip.details
            
            val cardTimer = findViewById<CardView>(R.id.cardTimer)
            val txtTimerDuration = findViewById<AppCompatTextView>(R.id.txtTimerDuration)
            val progressTimer = findViewById<ProgressBar>(R.id.progressTimer)
            val btnTimerStart = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnTimerStart)

            // Reset timer UI with new duration
            countDownTimer?.cancel()
            stopAlarm()
            isTimerRunning = false
            btnTimerStart.text = getString(R.string.start)
            
            val parsedMinutes = extractMinutes(tip.duration)
            if (parsedMinutes != null && parsedMinutes > 0) {
                totalTimeInMillis = parsedMinutes * 60 * 1000L
                timeRemainingInMillis = totalTimeInMillis
                cardTimer.visibility = View.VISIBLE
                updateTimerText(timeRemainingInMillis, txtTimerDuration)
                progressTimer.progress = 100
            } else {
                cardTimer.visibility = View.GONE
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun init() {
        val txtTabTitle = findViewById<AppCompatTextView>(R.id.txtTabTitle)
        val txtTitle = findViewById<AppCompatTextView>(R.id.txtTitle)
        val txtDetails = findViewById<AppCompatTextView>(R.id.txtDetails)
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        val lrCopy = findViewById<View>(R.id.lrCopy)
        val lrShare = findViewById<View>(R.id.lrShare)
        val lrWhatShare = findViewById<View>(R.id.lrWhatShare)

        // Timer views
        val cardTimer = findViewById<CardView>(R.id.cardTimer)
        val txtTimerDuration = findViewById<AppCompatTextView>(R.id.txtTimerDuration)
        val progressTimer = findViewById<ProgressBar>(R.id.progressTimer)
        val btnTimerStart = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnTimerStart)
        val btnTimerReset = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnTimerReset)

        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.navigationIcon?.setTint(Color.WHITE)

        txtTabTitle.text = tip.category
        txtTitle.text = tip.title
        txtDetails.text = tip.details

        // Set Cover Image header by Category
        val imgHeader = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.img)
        val headerRes = when (tip.category) {
            "Face", "Gesicht", "Cara", "Visage", "चेहरा", "顔", "얼굴", "Rosto", "Лицо" -> R.drawable.facegelmassage_title
            "Hair", "Haare", "Cabello", "Cheveux", "बाल", "髪", "헤어", "Cabelo", "Волосы" -> R.drawable.hairtreatment2
            "Skin", "Haut", "Piel", "Peau", "त्वचा", "肌", "피부", "Pele", "Кожа" -> R.drawable.facebodymassage
            "Eyes", "Augen", "Ojos", "Yeux", "आंखें", "目", "눈", "Olhos", "Глаза" -> R.drawable.faceeyesmask
            else -> R.drawable.ic_remedy
        }
        imgHeader.setImageResource(headerRes)

        // Setup timer if a valid duration exists
        val parsedMinutes = extractMinutes(tip.duration)
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

        // Setup TTS
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
                    result = textToSpeech?.setLanguage(Locale.ENGLISH)
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
            copyToClipboard(tip.details)
        }

        lrShare.setOnClickListener { showShareChooserDialog(null) }
        lrWhatShare.setOnClickListener { showShareChooserDialog("com.whatsapp") }

        adRequest = AdRequest.Builder().build()

        val adContainer = findViewById<View>(R.id.ad_view)
        val mAdView = AdView(this)
        mAdView.setAdSize(AdSize.BANNER)
        mAdView.adUnitId = getString(R.string.banner_home_footer)
        (adContainer as RelativeLayout).addView(mAdView)
        mAdView.loadAd(adRequest!!)

        loadFullScreenAds()
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

    private fun updateTimerText(millisUntilFinished: Long, textView: AppCompatTextView) {
        val seconds = (millisUntilFinished / 1000) % 60
        val minutes = (millisUntilFinished / 1000) / 60
        val timeStr = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        textView.text = timeStr
    }

    private fun startTimer(
        txtDuration: AppCompatTextView,
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
        txtDuration: AppCompatTextView,
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
                @Suppress("DEPRECATION")
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
        val speechText = "${tip.title}. ${tip.details}"
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

    private fun copyToClipboard(copyText: String?) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = ClipData.newPlainText(getString(R.string.copy_content), copyText)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, getString(R.string.copy_clipboard), Toast.LENGTH_SHORT).show()
    }

    private fun shareContent() {
        val text = "${tip.title} :\n\n${tip.details}\n\n${getString(R.string.app_share)}: http://play.google.com/store/apps/details?id=$packageName"
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share)))
    }

    private fun shareContentOnlyWhatsapp() {
        val text = "${tip.title} :\n\n${tip.details}\n\n${getString(R.string.app_share)}: http://play.google.com/store/apps/details?id=$packageName"
        val whatsappIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            setPackage("com.whatsapp")
            putExtra(Intent.EXTRA_TEXT, text)
        }
        try {
            startActivity(whatsappIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, getString(R.string.whatsapp_not_installed), Toast.LENGTH_SHORT).show()
        }
    }

    private fun showShareChooserDialog(targetPackage: String?) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_share_chooser, null)
        val dialog = android.app.Dialog(this).apply {
            requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
            setContentView(dialogView)
        }
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
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "image/png"
                    putExtra(Intent.EXTRA_STREAM, uri)
                    val caption = "${tip.title}\n\n${getString(R.string.app_share)}: http://play.google.com/store/apps/details?id=$packageName"
                    putExtra(Intent.EXTRA_TEXT, caption)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                
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
            Toast.makeText(this, getString(R.string.share_image_error_toast, e.message ?: ""), Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateShareCardBitmap(): Bitmap {
        val cardView = LayoutInflater.from(this).inflate(R.layout.layout_share_card, null)

        val txtShareCategory = cardView.findViewById<AppCompatTextView>(R.id.txtShareCategory)
        val txtShareTitle = cardView.findViewById<AppCompatTextView>(R.id.txtShareTitle)
        val txtShareDetails = cardView.findViewById<AppCompatTextView>(R.id.txtShareDetails)
        val imgShare = cardView.findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.imgShare)
        val cardShareImage = cardView.findViewById<CardView>(R.id.cardShareImage)

        txtShareCategory.text = tip.category.uppercase(Locale.getDefault())
        txtShareTitle.text = tip.title
        txtShareDetails.text = tip.details

        // Set Cover Image for card
        val headerRes = when (tip.category) {
            "Face", "Gesicht", "Cara", "Visage", "चेहरा", "顔", "얼굴", "Rosto", "Лицо" -> R.drawable.facegelmassage_title
            "Hair", "Haare", "Cabello", "Cheveux", "बाल", "髪", "헤어", "Cabelo", "Волосы" -> R.drawable.hairtreatment2
            "Skin", "Haut", "Piel", "Peau", "त्वча", "肌", "피부", "Pele", "Кожа" -> R.drawable.facebodymassage
            "Eyes", "Augen", "Ojos", "Yeux", "आंखें", "目", "눈", "Olhos", "Глаза" -> R.drawable.faceeyesmask
            else -> R.drawable.ic_remedy
        }
        imgShare.setImageResource(headerRes)
        cardShareImage.visibility = View.VISIBLE

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
            val files = cachePath.listFiles()
            if (files != null) {
                for (f in files) {
                    f.delete()
                }
            }
            val file = File(cachePath, "share_custom_remedy_${System.currentTimeMillis()}.png")
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.flush()
            stream.close()
            return FileProvider.getUriForFile(this, "$packageName.fileprovider", file)
        } catch (e: IOException) {
            return null
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_custom_tip_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                // Open AddEditCustomTipActivity with current tip data
                val intent = Intent(this, AddEditCustomTipActivity::class.java).apply {
                    putExtra("tip_id", tip.id)
                    putExtra("tip_title", tip.title)
                    putExtra("tip_duration", tip.duration)
                    putExtra("tip_details", tip.details)
                    putExtra("tip_category", tip.category)
                }
                startActivity(intent)
                AppUtils.startFromRightToLeft(this)
                true
            }
            R.id.action_delete -> {
                confirmDelete()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun confirmDelete() {
        android.app.AlertDialog.Builder(this)
            .setMessage(getString(R.string.custom_tip_delete_confirm))
            .setPositiveButton(android.R.string.yes) { _, _ ->
                deleteTip()
            }
            .setNegativeButton(android.R.string.no, null)
            .show()
    }

    private fun deleteTip() {
        dbHelper.deleteTip(tip.id)
        Toast.makeText(this, getString(R.string.custom_tip_deleted_toast), Toast.LENGTH_SHORT).show()
        finish()
        AppUtils.finishFromLeftToRight(this)
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(this)
        return true
    }

    private fun loadFullScreenAds() {
        val sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
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
                override fun onAdFailedToLoad(adError: com.google.android.gms.ads.LoadAdError) {
                    mInterstitialAd = null
                    mAdIsLoading = false
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                    mAdIsLoading = false
                    showInterstitial()
                }
            }
        )
    }

    private fun showInterstitial() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : com.google.android.gms.ads.FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    mInterstitialAd = null
                }

                override fun onAdFailedToShowFullScreenContent(adError: com.google.android.gms.ads.AdError) {
                    mInterstitialAd = null
                }
            }
            mInterstitialAd?.show(this)
        }
    }
}
