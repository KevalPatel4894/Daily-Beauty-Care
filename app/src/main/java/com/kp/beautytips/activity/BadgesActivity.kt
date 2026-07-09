package com.kp.beautytips.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.kp.beautytips.R
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.adapter.BadgesAdapter
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class BadgesActivity : BaseActivity() {

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badges)
        init()
    }

    private fun init() {
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.navigationIcon?.setTint(Color.WHITE)

        val txtTabTitle = findViewById<AppCompatTextView>(R.id.txtTabTitle)
        txtTabTitle.text = getString(R.string.settings_achievements)

        // Load Ad Banner
        loadAdBanner()

        // Load Badges Data
        val badges = loadBadges()
        val unlockedCount = badges.count { it.isUnlocked }

        // Bind Summary
        val txtBadgesSummaryCount = findViewById<AppCompatTextView>(R.id.txtBadgesSummaryCount)
        txtBadgesSummaryCount.text = "$unlockedCount / ${badges.size}"

        val progressBadgesSummary = findViewById<ProgressBar>(R.id.progressBadgesSummary)
        progressBadgesSummary.max = badges.size
        progressBadgesSummary.progress = unlockedCount

        // Bind Grid RecyclerView
        val rvBadges = findViewById<RecyclerView>(R.id.rvBadges)
        rvBadges.layoutManager = GridLayoutManager(this, 2)
        rvBadges.adapter = BadgesAdapter(this, badges)
    }

    private fun loadBadges(): List<BadgeModel> {
        val list = ArrayList<BadgeModel>()
        val beautyPrefs = getSharedPreferences("beautytips_prefs", Context.MODE_PRIVATE)
        val waterPrefs = getSharedPreferences("water_tracker_prefs", Context.MODE_PRIVATE)
        val checkInPrefs = getSharedPreferences("DailyBeautyCarePrefs", Context.MODE_PRIVATE)

        // 1. Glow Master: Read 5 skin/face tips
        val skinSet = beautyPrefs.getStringSet("read_tips_skin", emptySet()) ?: emptySet()
        list.add(BadgeModel(
            id = "glow_master",
            titleRes = R.string.badge_glow_master_title,
            descRes = R.string.badge_glow_master_desc,
            maxProgress = 5,
            currentProgress = skinSet.size,
            tintColor = "#CD7F32" // Bronze
        ))

        // 2. Hair Guru: Read 5 hair tips
        val hairSet = beautyPrefs.getStringSet("read_tips_hair", emptySet()) ?: emptySet()
        list.add(BadgeModel(
            id = "hair_guru",
            titleRes = R.string.badge_hair_guru_title,
            descRes = R.string.badge_hair_guru_desc,
            maxProgress = 5,
            currentProgress = hairSet.size,
            tintColor = "#CD7F32" // Bronze
        ))

        // 3. Remedy Saver: Save 5 favorites
        val allKeys = beautyPrefs.all
        val favCount = allKeys.filterKeys { it.startsWith("fav_") }.values.filter { it is Boolean && it == true }.size
        list.add(BadgeModel(
            id = "remedy_saver",
            titleRes = R.string.badge_remedy_saver_title,
            descRes = R.string.badge_remedy_saver_desc,
            maxProgress = 5,
            currentProgress = favCount,
            tintColor = "#B0BEC5" // Silver
        ))

        // 4. Hydration Hero: Meet water goals on 3 days
        val completedDaysSet = waterPrefs.getStringSet("water_completed_dates", emptySet()) ?: emptySet()
        list.add(BadgeModel(
            id = "hydration_hero",
            titleRes = R.string.badge_hydration_hero_title,
            descRes = R.string.badge_hydration_hero_desc,
            maxProgress = 3,
            currentProgress = completedDaysSet.size,
            tintColor = "#B0BEC5" // Silver
        ))

        // 5. Consistency Champion: 7-day check-in streak
        val streak = checkInPrefs.getInt("check_in_streak", 0)
        list.add(BadgeModel(
            id = "consistency_champion",
            titleRes = R.string.badge_consistency_champion_title,
            descRes = R.string.badge_consistency_champion_desc,
            maxProgress = 7,
            currentProgress = streak,
            tintColor = "#FFD700" // Gold
        ))

        // 6. Challenge Champion: Complete 1 challenge
        var completedChallenges = 0
        for (challenge in com.kp.beautytips.data.ChallengeRepository.challenges) {
            var allCompleted = true
            for (i in 0 until challenge.daysCount) {
                if (!beautyPrefs.getBoolean("challenge_completed_${challenge.id}_$i", false)) {
                    allCompleted = false
                    break
                }
            }
            if (allCompleted) {
                completedChallenges++
            }
        }
        list.add(BadgeModel(
            id = "challenge_champion",
            titleRes = R.string.badge_challenge_champion_title,
            descRes = R.string.badge_challenge_champion_desc,
            maxProgress = 1,
            currentProgress = completedChallenges,
            tintColor = "#FFD700" // Gold
        ))

        // 7. Social Beautician: Share 3 beauty tips
        val shareCount = beautyPrefs.getInt("tips_shared_count", 0)
        list.add(BadgeModel(
            id = "social_beautician",
            titleRes = R.string.badge_social_beautician_title,
            descRes = R.string.badge_social_beautician_desc,
            maxProgress = 3,
            currentProgress = shareCount,
            tintColor = "#B0BEC5" // Silver
        ))

        return list
    }

    private fun loadAdBanner() {
        try {
            val adContainer = findViewById<ViewGroup>(R.id.ad_view) ?: return
            AdManager.loadBanner(this, adContainer, getString(R.string.banner_home_footer))
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

data class BadgeModel(
    val id: String,
    val titleRes: Int,
    val descRes: Int,
    val maxProgress: Int,
    val currentProgress: Int,
    val tintColor: String
) {
    val isUnlocked: Boolean
        get() = currentProgress >= maxProgress
}
