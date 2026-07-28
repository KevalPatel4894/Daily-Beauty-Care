package com.kp.beautytips.activity

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.kp.beautytips.R
import com.kp.beautytips.adapter.SeasonalAdapter
import com.kp.beautytips.model.SeasonCategory
import com.kp.beautytips.model.SeasonalTipModel
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.util.Calendar

class SeasonalAdvisorActivity : BaseActivity() {

    private lateinit var tabLayoutSeasons: TabLayout
    private lateinit var txtSeasonTitle: TextView
    private lateinit var txtSeasonDesc: TextView
    private lateinit var rvSeasonalTips: RecyclerView
    private lateinit var adapter: SeasonalAdapter

    private val seasonCategories = ArrayList<SeasonCategory>()

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seasonal_advisor)
        initData()
        initViews()
    }

    private fun initData() {
        // Summer
        seasonCategories.add(
            SeasonCategory(
                seasonKey = "summer",
                seasonNameRes = R.string.season_summer,
                headerTitleRes = R.string.season_summer_title,
                headerDescRes = R.string.season_summer_desc,
                tips = listOf(
                    SeasonalTipModel(
                        title = getString(R.string.season_summer_tip1_title),
                        description = getString(R.string.season_summer_tip1_desc)
                    ),
                    SeasonalTipModel(
                        title = getString(R.string.season_summer_tip2_title),
                        description = getString(R.string.season_summer_tip2_desc)
                    )
                )
            )
        )

        // Monsoon
        seasonCategories.add(
            SeasonCategory(
                seasonKey = "monsoon",
                seasonNameRes = R.string.season_monsoon,
                headerTitleRes = R.string.season_monsoon_title,
                headerDescRes = R.string.season_monsoon_desc,
                tips = listOf(
                    SeasonalTipModel(
                        title = getString(R.string.season_monsoon_tip1_title),
                        description = getString(R.string.season_monsoon_tip1_desc)
                    ),
                    SeasonalTipModel(
                        title = getString(R.string.season_monsoon_tip2_title),
                        description = getString(R.string.season_monsoon_tip2_desc)
                    )
                )
            )
        )

        // Autumn
        seasonCategories.add(
            SeasonCategory(
                seasonKey = "autumn",
                seasonNameRes = R.string.season_autumn,
                headerTitleRes = R.string.season_autumn_title,
                headerDescRes = R.string.season_autumn_desc,
                tips = listOf(
                    SeasonalTipModel(
                        title = getString(R.string.season_autumn_tip1_title),
                        description = getString(R.string.season_autumn_tip1_desc)
                    ),
                    SeasonalTipModel(
                        title = getString(R.string.season_autumn_tip2_title),
                        description = getString(R.string.season_autumn_tip2_desc)
                    )
                )
            )
        )

        // Winter
        seasonCategories.add(
            SeasonCategory(
                seasonKey = "winter",
                seasonNameRes = R.string.season_winter,
                headerTitleRes = R.string.season_winter_title,
                headerDescRes = R.string.season_winter_desc,
                tips = listOf(
                    SeasonalTipModel(
                        title = getString(R.string.season_winter_tip1_title),
                        description = getString(R.string.season_winter_tip1_desc)
                    ),
                    SeasonalTipModel(
                        title = getString(R.string.season_winter_tip2_title),
                        description = getString(R.string.season_winter_tip2_desc)
                    )
                )
            )
        )
    }

    private fun initViews() {
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.setNavigationOnClickListener { onBackPressed() }

        tabLayoutSeasons = findViewById(R.id.tabLayoutSeasons)
        txtSeasonTitle = findViewById(R.id.txtSeasonTitle)
        txtSeasonDesc = findViewById(R.id.txtSeasonDesc)
        rvSeasonalTips = findViewById(R.id.rvSeasonalTips)

        adapter = SeasonalAdapter(emptyList())
        rvSeasonalTips.layoutManager = LinearLayoutManager(this)
        rvSeasonalTips.adapter = adapter

        // Setup Tabs
        seasonCategories.forEach { category ->
            tabLayoutSeasons.addTab(tabLayoutSeasons.newTab().setText(getString(category.seasonNameRes)))
        }

        tabLayoutSeasons.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let { selectSeason(it) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // Auto-detect current season by month
        val currentSeasonIndex = getCurrentSeasonIndex()
        tabLayoutSeasons.getTabAt(currentSeasonIndex)?.select()
        selectSeason(currentSeasonIndex)

        val adContainer = findViewById<ViewGroup>(R.id.ad_view)
        AdManager.loadBanner(this, adContainer, getString(R.string.banner_home_footer))
    }

    private fun selectSeason(index: Int) {
        if (index in seasonCategories.indices) {
            val category = seasonCategories[index]
            txtSeasonTitle.text = getString(category.headerTitleRes)
            txtSeasonDesc.text = getString(category.headerDescRes)
            adapter.updateData(category.tips)
        }
    }

    private fun getCurrentSeasonIndex(): Int {
        val month = Calendar.getInstance().get(Calendar.MONTH) // 0-based: Jan=0, Feb=1, ... Dec=11
        return when (month) {
            Calendar.MARCH, Calendar.APRIL, Calendar.MAY, Calendar.JUNE -> 0 // Summer
            Calendar.JULY, Calendar.AUGUST, Calendar.SEPTEMBER -> 1 // Monsoon
            Calendar.OCTOBER, Calendar.NOVEMBER -> 2 // Autumn
            else -> 3 // Winter (Dec, Jan, Feb)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(this)
        return true
    }
}
