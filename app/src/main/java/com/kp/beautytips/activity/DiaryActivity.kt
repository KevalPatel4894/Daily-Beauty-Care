package com.kp.beautytips.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
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
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.adapter.DiaryAdapter
import com.kp.beautytips.data.DiaryDbHelper
import com.kp.beautytips.model.DiaryEntry
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class DiaryActivity : BaseActivity() {

    private lateinit var dbHelper: DiaryDbHelper
    private lateinit var rvDiary: RecyclerView
    private lateinit var layoutEmptyDiary: LinearLayout
    private lateinit var fabAddEntry: FloatingActionButton
    private lateinit var adapter: DiaryAdapter
    private val diaryList = ArrayList<DiaryEntry>()

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)
        
        dbHelper = DiaryDbHelper(this)
        
        init()
    }

    override fun onResume() {
        super.onResume()
        loadEntries()
    }

    private fun init() {
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.navigationIcon?.setTint(Color.WHITE)

        val txtTabTitle = findViewById<AppCompatTextView>(R.id.txtTabTitle)
        txtTabTitle.text = getString(R.string.settings_diary)

        rvDiary = findViewById(R.id.rvDiary)
        layoutEmptyDiary = findViewById(R.id.layoutEmptyDiary)
        fabAddEntry = findViewById(R.id.fabAddEntry)

        rvDiary.layoutManager = LinearLayoutManager(this)
        adapter = DiaryAdapter(this, diaryList) { entry ->
            // Click to edit
            val intent = Intent(this, AddDiaryEntryActivity::class.java).apply {
                putExtra("entry_id", entry.id)
                putExtra("entry_date", entry.date)
                putExtra("entry_title", entry.title)
                putExtra("entry_content", entry.content)
                putExtra("entry_image", entry.imagePath)
            }
            startActivity(intent)
            AppUtils.startFromRightToLeft(this)
        }
        rvDiary.adapter = adapter

        fabAddEntry.setOnClickListener {
            // Click to add
            val intent = Intent(this, AddDiaryEntryActivity::class.java)
            startActivity(intent)
            AppUtils.startFromRightToLeft(this)
        }

        loadAdBanner()
    }

    private fun loadEntries() {
        try {
            diaryList.clear()
            val entries = dbHelper.getAllEntries()
            diaryList.addAll(entries)
            adapter.notifyDataSetChanged()

            if (diaryList.isEmpty()) {
                layoutEmptyDiary.visibility = View.VISIBLE
                rvDiary.visibility = View.GONE
            } else {
                layoutEmptyDiary.visibility = View.GONE
                rvDiary.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
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
