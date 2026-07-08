package com.kp.beautytips.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kp.beautytips.R
import com.kp.beautytips.adapter.CustomTipsAdapter
import com.kp.beautytips.data.CustomTipDbHelper
import com.kp.beautytips.model.CustomTip
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class CustomTipsActivity : BaseActivity() {

    private lateinit var dbHelper: CustomTipDbHelper
    private lateinit var rvCustomTips: RecyclerView
    private lateinit var layoutEmptyTips: LinearLayout
    private lateinit var fabAddCustomTip: FloatingActionButton
    private lateinit var adapter: CustomTipsAdapter
    private val tipsList = ArrayList<CustomTip>()

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_tips)

        dbHelper = CustomTipDbHelper(this)
        init()
    }

    override fun onResume() {
        super.onResume()
        loadTips()
    }

    private fun init() {
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.navigationIcon?.setTint(Color.WHITE)

        val txtTabTitle = findViewById<AppCompatTextView>(R.id.txtTabTitle)
        txtTabTitle.text = getString(R.string.my_custom_tips)

        rvCustomTips = findViewById(R.id.rvCustomTips)
        layoutEmptyTips = findViewById(R.id.layoutEmptyTips)
        fabAddCustomTip = findViewById(R.id.fabAddCustomTip)

        rvCustomTips.layoutManager = LinearLayoutManager(this)
        adapter = CustomTipsAdapter(tipsList) { tip ->
            // Click to open details
            val intent = Intent(this, CustomTipDetailsActivity::class.java).apply {
                putExtra("custom_tip", tip)
            }
            startActivity(intent)
            AppUtils.startFromRightToLeft(this)
        }
        rvCustomTips.adapter = adapter

        fabAddCustomTip.setOnClickListener {
            // Click to add new custom tip
            val intent = Intent(this, AddEditCustomTipActivity::class.java)
            startActivity(intent)
            AppUtils.startFromRightToLeft(this)
        }

        loadAdBanner()
    }

    private fun loadTips() {
        try {
            tipsList.clear()
            val tips = dbHelper.getAllTips()
            tipsList.addAll(tips)
            adapter.notifyDataSetChanged()

            if (tipsList.isEmpty()) {
                layoutEmptyTips.visibility = View.VISIBLE
                rvCustomTips.visibility = View.GONE
            } else {
                layoutEmptyTips.visibility = View.GONE
                rvCustomTips.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(this)
        return true
    }
}
