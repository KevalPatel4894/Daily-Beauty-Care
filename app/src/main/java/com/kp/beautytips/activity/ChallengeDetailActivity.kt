package com.kp.beautytips.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.kp.beautytips.R
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.adapter.ChallengeDaysAdapter
import com.kp.beautytips.data.ChallengeRepository
import com.kp.beautytips.model.Challenge
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class ChallengeDetailActivity : BaseActivity() {

    private lateinit var rvChallengeDays: RecyclerView
    private lateinit var txtToolbarTitle: AppCompatTextView
    private lateinit var txtHeaderTitle: AppCompatTextView
    private lateinit var txtHeaderDesc: AppCompatTextView
    private lateinit var txtHeaderProgressText: AppCompatTextView
    private lateinit var headerProgressBar: ProgressBar
    private var adRequest: AdRequest? = null

    private var challengeId: String = ""
    private var challenge: Challenge? = null

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_detail)
        
        challengeId = intent.getStringExtra("challenge_id") ?: ""
        challenge = ChallengeRepository.getChallengeById(challengeId)
        
        if (challenge == null) {
            finish()
            return
        }

        init()
    }

    private fun init() {
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        txtToolbarTitle = findViewById(R.id.txtToolbarTitle)
        txtHeaderTitle = findViewById(R.id.txtHeaderTitle)
        txtHeaderDesc = findViewById(R.id.txtHeaderDesc)
        txtHeaderProgressText = findViewById(R.id.txtHeaderProgressText)
        headerProgressBar = findViewById(R.id.headerProgressBar)

        txtToolbarTitle.text = getString(challenge!!.titleResId)
        txtHeaderTitle.text = getString(challenge!!.titleResId)
        txtHeaderDesc.text = getString(challenge!!.descriptionResId)

        rvChallengeDays = findViewById(R.id.rvChallengeDays)
        rvChallengeDays.layoutManager = LinearLayoutManager(this)

        // Ad configuration
        adRequest = AdRequest.Builder().build()
                val adContainer = findViewById<ViewGroup>(R.id.ad_view)
        AdManager.loadBanner(this, adContainer, getString(R.string.banner_home_footer))
    }

    override fun onResume() {
        super.onResume()
        updateProgress()
        
        // Refresh adapter on resume to show newly unlocked days
        rvChallengeDays.adapter = ChallengeDaysAdapter(this, challengeId, challenge!!.tasks)
    }

    private fun updateProgress() {
        val prefs = getSharedPreferences("beautytips_prefs", Context.MODE_PRIVATE)
        var completedCount = 0
        for (i in 0 until challenge!!.daysCount) {
            val isCompleted = prefs.getBoolean("challenge_completed_${challengeId}_$i", false)
            if (isCompleted) {
                completedCount++
            }
        }

        val progressPercent = (completedCount * 100) / challenge!!.daysCount
        headerProgressBar.progress = progressPercent
        txtHeaderProgressText.text = getString(R.string.challenge_progress, completedCount, challenge!!.daysCount)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(activity)
        return true
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        AppUtils.finishFromLeftToRight(activity)
    }
}
