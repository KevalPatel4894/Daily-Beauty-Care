package com.kp.beautytips.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.kp.beautytips.R
import com.kp.beautytips.adapter.ChallengesAdapter
import com.kp.beautytips.data.ChallengeRepository
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class ChallengesActivity : BaseActivity() {

    private lateinit var rvChallenges: RecyclerView
    private var adRequest: AdRequest? = null

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenges)
        init()
    }

    private fun init() {
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        rvChallenges = findViewById(R.id.rvChallenges)
        rvChallenges.layoutManager = LinearLayoutManager(this)

        // Ad configuration
        adRequest = AdRequest.Builder().build()
        val adContainer = findViewById<View>(R.id.ad_view)
        val mAdView = AdView(this).apply {
            setAdSize(AdSize.BANNER)
            adUnitId = getString(R.string.banner_home_footer)
        }
        (adContainer as RelativeLayout).addView(mAdView)
        mAdView.loadAd(adRequest!!)
    }

    override fun onResume() {
        super.onResume()
        // Refresh challenges list to update progress bars
        rvChallenges.adapter = ChallengesAdapter(this, ChallengeRepository.challenges)
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
