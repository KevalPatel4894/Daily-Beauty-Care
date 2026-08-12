package com.kp.beautytips.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.kp.beautytips.R
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper

data class BeginnerStep(
    val dayNumber: Int,
    val titleResId: Int,
    val descResId: Int
)

class BeginnersGuideActivity : BaseActivity() {

    private lateinit var rvBeginnerSteps: RecyclerView
    private lateinit var progressGuide: ProgressBar
    private lateinit var txtProgressStatus: TextView
    private lateinit var prefs: SharedPreferences
    private lateinit var adapter: BeginnerStepAdapter

    private val steps = listOf(
        BeginnerStep(1, R.string.beginner_day_1_title, R.string.beginner_day_1_desc),
        BeginnerStep(2, R.string.beginner_day_2_title, R.string.beginner_day_2_desc),
        BeginnerStep(3, R.string.beginner_day_3_title, R.string.beginner_day_3_desc),
        BeginnerStep(4, R.string.beginner_day_4_title, R.string.beginner_day_4_desc),
        BeginnerStep(5, R.string.beginner_day_5_title, R.string.beginner_day_5_desc),
        BeginnerStep(6, R.string.beginner_day_6_title, R.string.beginner_day_6_desc),
        BeginnerStep(7, R.string.beginner_day_7_title, R.string.beginner_day_7_desc)
    )

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beginners_guide)
        prefs = getSharedPreferences("beginners_guide_prefs", Context.MODE_PRIVATE)

        initViews()
    }

    private fun initViews() {
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.setNavigationOnClickListener { onBackPressed() }

        val txtTabTitle = findViewById<TextView>(R.id.txtTabTitle)
        txtTabTitle.text = getString(R.string.beginners_guide_title)

        progressGuide = findViewById(R.id.progressGuide)
        txtProgressStatus = findViewById(R.id.txtProgressStatus)
        rvBeginnerSteps = findViewById(R.id.rvBeginnerSteps)

        rvBeginnerSteps.layoutManager = LinearLayoutManager(this)
        adapter = BeginnerStepAdapter(steps, prefs) {
            updateProgress()
        }
        rvBeginnerSteps.adapter = adapter

        updateProgress()

        val adContainer = findViewById<ViewGroup>(R.id.ad_view)
        AdManager.loadBanner(this, adContainer, getString(R.string.banner_home_footer))
    }

    private fun updateProgress() {
        var completedCount = 0
        for (step in steps) {
            if (prefs.getBoolean("day_${step.dayNumber}_completed", false)) {
                completedCount++
            }
        }
        progressGuide.progress = completedCount
        val percent = (completedCount * 100) / steps.size
        txtProgressStatus.text = getString(R.string.beginner_progress_format, completedCount, steps.size, percent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(this)
        return true
    }
}

class BeginnerStepAdapter(
    private val steps: List<BeginnerStep>,
    private val prefs: SharedPreferences,
    private val onStepChanged: () -> Unit
) : RecyclerView.Adapter<BeginnerStepAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardStep: MaterialCardView = view.findViewById(R.id.cardStep)
        val txtDayBadge: TextView = view.findViewById(R.id.txtDayBadge)
        val cbCompleted: CheckBox = view.findViewById(R.id.cbCompleted)
        val txtStepTitle: TextView = view.findViewById(R.id.txtStepTitle)
        val txtStepDesc: TextView = view.findViewById(R.id.txtStepDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_beginner_step, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = steps[position]
        val context = holder.itemView.context
        val isCompleted = prefs.getBoolean("day_${item.dayNumber}_completed", false)

        holder.txtDayBadge.text = "DAY ${item.dayNumber}"
        holder.txtStepTitle.text = context.getString(item.titleResId)
        holder.txtStepDesc.text = context.getString(item.descResId)

        holder.cbCompleted.setOnCheckedChangeListener(null)
        holder.cbCompleted.isChecked = isCompleted

        if (isCompleted) {
            holder.cardStep.setCardBackgroundColor(0xFFF0FDF4.toInt())
            holder.cardStep.strokeColor = 0xFFBBF7D0.toInt()
        } else {
            holder.cardStep.setCardBackgroundColor(0xFFFFFFFF.toInt())
            holder.cardStep.strokeColor = 0xFFEAEAEA.toInt()
        }

        holder.cbCompleted.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("day_${item.dayNumber}_completed", isChecked).apply()
            notifyItemChanged(position)
            onStepChanged()
        }
    }

    override fun getItemCount() = steps.size
}
