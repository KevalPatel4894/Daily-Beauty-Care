package com.kp.beautytips.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.kp.beautytips.R
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import org.json.JSONArray
import org.json.JSONObject

data class RecentlyViewedItem(
    val title: String,
    val tabName: String,
    val details: String
)

class RecentlyViewedActivity : BaseActivity() {

    private val recentList = mutableListOf<RecentlyViewedItem>()
    private lateinit var rvRecentlyViewed: RecyclerView
    private lateinit var txtEmpty: TextView
    private lateinit var adapter: RecentAdapter

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recently_viewed)
        initViews()
        loadRecentlyViewed()
    }

    private fun initViews() {
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.setNavigationOnClickListener { onBackPressed() }

        rvRecentlyViewed = findViewById(R.id.rvRecentlyViewed)
        txtEmpty = findViewById(R.id.txtEmptyRecentlyViewed)
        rvRecentlyViewed.layoutManager = LinearLayoutManager(this)

        adapter = RecentAdapter(recentList) { item ->
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra("TabName", item.tabName)
                putExtra("Title", item.title)
                putExtra("Details", item.details)
            }
            startActivity(intent)
            AppUtils.startFromRightToLeft(this)
        }
        rvRecentlyViewed.adapter = adapter

        val btnClear = findViewById<MaterialButton>(R.id.btnClearHistory)
        btnClear.setOnClickListener {
            clearHistory()
        }

        val adContainer = findViewById<ViewGroup>(R.id.ad_view)
        AdManager.loadBanner(this, adContainer, getString(R.string.banner_home_footer))
    }

    private fun loadRecentlyViewed() {
        recentList.clear()
        val prefs = getSharedPreferences("beautytips_prefs", Context.MODE_PRIVATE)
        val jsonStr = prefs.getString("recently_viewed_tips_json", null)

        if (jsonStr != null) {
            try {
                val array = JSONArray(jsonStr)
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    recentList.add(
                        RecentlyViewedItem(
                            title = obj.optString("title", ""),
                            tabName = obj.optString("tabName", ""),
                            details = obj.optString("details", "")
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        if (recentList.isEmpty()) {
            rvRecentlyViewed.visibility = View.GONE
            txtEmpty.visibility = View.VISIBLE
        } else {
            rvRecentlyViewed.visibility = View.VISIBLE
            txtEmpty.visibility = View.GONE
            adapter.notifyDataSetChanged()
        }
    }

    private fun clearHistory() {
        getSharedPreferences("beautytips_prefs", Context.MODE_PRIVATE)
            .edit()
            .remove("recently_viewed_tips_json")
            .apply()

        recentList.clear()
        adapter.notifyDataSetChanged()
        rvRecentlyViewed.visibility = View.GONE
        txtEmpty.visibility = View.VISIBLE
        Toast.makeText(this, R.string.msg_history_cleared, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(this)
        return true
    }
}

class RecentAdapter(
    private val items: List<RecentlyViewedItem>,
    private val onItemClick: (RecentlyViewedItem) -> Unit
) : RecyclerView.Adapter<RecentAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtCategory: TextView = itemView.findViewById(R.id.txtRecentCategory)
        val txtTitle: TextView = itemView.findViewById(R.id.txtRecentTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recently_viewed, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.txtCategory.text = item.tabName
        holder.txtTitle.text = item.title

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}
