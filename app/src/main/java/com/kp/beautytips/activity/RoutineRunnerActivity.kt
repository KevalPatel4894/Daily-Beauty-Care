package com.kp.beautytips.activity

import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton
import com.kp.beautytips.R
import com.kp.beautytips.model.RoutineModel
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AdManager
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.util.Locale

class RoutineRunnerActivity : BaseActivity() {

    private var routine: RoutineModel? = null
    private var currentStepIndex = 0

    private lateinit var txtStepProgress: TextView
    private lateinit var progressBarRoutine: ProgressBar
    private lateinit var txtStepName: TextView
    private lateinit var txtStepDescription: TextView
    private lateinit var layoutTimerContainer: LinearLayout
    private lateinit var txtTimerDisplay: TextView
    private lateinit var btnToggleTimer: MaterialButton
    private lateinit var btnPrevStep: MaterialButton
    private lateinit var btnNextStep: MaterialButton

    private var countDownTimer: CountDownTimer? = null
    private var isTimerRunning = false
    private var timeLeftInMillis: Long = 0

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine_runner)

        routine = intent.getSerializableExtra("routine") as? RoutineModel
        if (routine == null || routine!!.steps.isEmpty()) {
            Toast.makeText(this, R.string.no_routines_found, Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        initViews()
        displayStep(0)
    }

    private fun initViews() {
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolBar.title = routine?.title ?: getString(R.string.routine_runner_title)
        toolBar.setNavigationOnClickListener { onBackPressed() }

        txtStepProgress = findViewById(R.id.txtStepProgress)
        progressBarRoutine = findViewById(R.id.progressBarRoutine)
        txtStepName = findViewById(R.id.txtStepName)
        txtStepDescription = findViewById(R.id.txtStepDescription)
        layoutTimerContainer = findViewById(R.id.layoutTimerContainer)
        txtTimerDisplay = findViewById(R.id.txtTimerDisplay)
        btnToggleTimer = findViewById(R.id.btnToggleTimer)
        btnPrevStep = findViewById(R.id.btnPrevStep)
        btnNextStep = findViewById(R.id.btnNextStep)

        btnPrevStep.setOnClickListener {
            if (currentStepIndex > 0) {
                stopTimer()
                displayStep(currentStepIndex - 1)
            }
        }

        btnNextStep.setOnClickListener {
            val totalSteps = routine?.steps?.size ?: 0
            if (currentStepIndex < totalSteps - 1) {
                stopTimer()
                displayStep(currentStepIndex + 1)
            } else {
                // Routine Complete!
                playCompletionFeedback()
                Toast.makeText(this, R.string.routine_completed_congrats, Toast.LENGTH_LONG).show()
                finish()
            }
        }

        btnToggleTimer.setOnClickListener {
            if (isTimerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }

        val adContainer = findViewById<RelativeLayout>(R.id.ad_view)
        AdManager.showBannerAd(this, adContainer)
    }

    private fun displayStep(index: Int) {
        currentStepIndex = index
        val steps = routine?.steps ?: return
        val currentStep = steps[index]

        val totalSteps = steps.size
        txtStepProgress.text = getString(R.string.step_progress_format, index + 1, totalSteps)
        progressBarRoutine.progress = (((index + 1).toDouble() / totalSteps) * 100).toInt()

        txtStepName.text = currentStep.stepName
        if (currentStep.description.isNotEmpty()) {
            txtStepDescription.visibility = View.VISIBLE
            txtStepDescription.text = currentStep.description
        } else {
            txtStepDescription.visibility = View.GONE
        }

        if (currentStep.timerSeconds > 0) {
            layoutTimerContainer.visibility = View.VISIBLE
            timeLeftInMillis = currentStep.timerSeconds * 1000L
            updateTimerText()
            btnToggleTimer.setText(R.string.btn_start_timer)
        } else {
            layoutTimerContainer.visibility = View.GONE
        }

        btnPrevStep.visibility = if (index > 0) View.VISIBLE else View.INVISIBLE
        if (index == totalSteps - 1) {
            btnNextStep.setText(R.string.btn_finish_routine)
        } else {
            btnNextStep.setText(R.string.btn_next_step)
        }
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                isTimerRunning = false
                btnToggleTimer.setText(R.string.btn_start_timer)
                playCompletionFeedback()
                Toast.makeText(this@RoutineRunnerActivity, R.string.step_timer_finished, Toast.LENGTH_SHORT).show()
            }
        }.start()

        isTimerRunning = true
        btnToggleTimer.setText(R.string.btn_pause_timer)
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
        isTimerRunning = false
        btnToggleTimer.setText(R.string.btn_start_timer)
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
        isTimerRunning = false
    }

    private fun updateTimerText() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        txtTimerDisplay.text = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
    }

    private fun playCompletionFeedback() {
        try {
            val notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(applicationContext, notificationUri)
            ringtone.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vibratorManager.defaultVibrator
            } else {
                @Suppress("DEPRECATION")
                getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(500)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }
}
