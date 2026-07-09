package com.kp.beautytips.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.kp.beautytips.R
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.adapter.ListAdapter
import com.kp.beautytips.data.TipRepository
import com.kp.beautytips.model.ListModel
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.util.Locale

class MoodTipsActivity : BaseActivity() {

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_tips)
        init()
    }

    private fun init() {
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.navigationIcon?.setTint(Color.WHITE)

        val moodKey = intent.getStringExtra("mood_key") ?: ""
        val moodName = intent.getStringExtra("mood_name") ?: ""
        val moodEmoji = intent.getStringExtra("mood_emoji") ?: ""

        val formattedTitle = getString(R.string.skin_mood_title_formatted, moodName)
        val txtTabTitle = findViewById<AppCompatTextView>(R.id.txtTabTitle)
        txtTabTitle.text = formattedTitle

        val txtMoodEmoji = findViewById<AppCompatTextView>(R.id.txtMoodEmoji)
        txtMoodEmoji.text = moodEmoji

        val txtMoodSubtitle = findViewById<AppCompatTextView>(R.id.txtMoodSubtitle)
        txtMoodSubtitle.text = formattedTitle

        loadAdBanner()

        val keywordsResId = when (moodKey) {
            "dull" -> R.string.skin_mood_dull_keywords
            "oily" -> R.string.skin_mood_oily_keywords
            "dry" -> R.string.skin_mood_dry_keywords
            "tired" -> R.string.skin_mood_tired_keywords
            "sensitive" -> R.string.skin_mood_sensitive_keywords
            "glowing" -> R.string.skin_mood_glowing_keywords
            else -> 0
        }

        val rvMoodTips = findViewById<RecyclerView>(R.id.rvMoodTips)
        val layoutEmptyTips = findViewById<View>(R.id.layoutEmptyTips)

        val matchingTips = getMatchingRemedies(keywordsResId)

        if (matchingTips.isEmpty()) {
            layoutEmptyTips.visibility = View.VISIBLE
            rvMoodTips.visibility = View.GONE
        } else {
            layoutEmptyTips.visibility = View.GONE
            rvMoodTips.visibility = View.VISIBLE

            val adapter = ListAdapter(ArrayList(matchingTips), moodName)
            rvMoodTips.layoutManager = LinearLayoutManager(this)
            rvMoodTips.adapter = adapter
        }
    }

    private fun getMatchingRemedies(keywordsResId: Int): List<ListModel> {
        if (keywordsResId == 0) return emptyList()

        val keywordsString = getString(keywordsResId)
        val keywordsList = keywordsString.split(",")
            .map { it.trim().lowercase(Locale.getDefault()) }
            .filter { it.isNotEmpty() }

        if (keywordsList.isEmpty()) return emptyList()

        val allTips = TipRepository.getAllTips(this)
        val matched = allTips.filter { tip ->
            val titleLower = tip.title.lowercase(Locale.getDefault())
            val detailsLower = tip.details.lowercase(Locale.getDefault())
            val descLower = tip.descriptionName.lowercase(Locale.getDefault())
            keywordsList.any { keyword ->
                titleLower.contains(keyword) || detailsLower.contains(keyword) || descLower.contains(keyword)
            }
        }
        return matched.distinctBy { it.title }
    }

    private fun loadAdBanner() {
        val adContainer = findViewById<ViewGroup>(R.id.ad_view) ?: return
        AdManager.loadBanner(this, adContainer, getString(R.string.banner_home_footer))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(this)
        return true
    }
}
