package com.kp.beautytips.activity

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
import java.util.Calendar

data class BeautyCalendarTask(
    val id: String,
    val dayOfWeek: Int, // 0 = Mon, 1 = Tue, ..., 6 = Sun
    var title: String,
    var isDone: Boolean = false
)

class BeautyCalendarActivity : BaseActivity() {

    private var selectedDayIndex: Int = 0 // 0..6
    private val allTasks = mutableListOf<BeautyCalendarTask>()

    private lateinit var dayMon: TextView
    private lateinit var dayTue: TextView
    private lateinit var dayWed: TextView
    private lateinit var dayThu: TextView
    private lateinit var dayFri: TextView
    private lateinit var daySat: TextView
    private lateinit var daySun: TextView
    private lateinit var dayTextViews: List<TextView>

    private lateinit var rvTasks: RecyclerView
    private lateinit var txtEmptyCalendar: TextView
    private lateinit var taskAdapter: CalendarTaskAdapter

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beauty_calendar)

        // Default selected day to current day of week
        val calDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        // Calendar.MONDAY = 2, Calendar.SUNDAY = 1
        selectedDayIndex = when (calDay) {
            Calendar.MONDAY -> 0
            Calendar.TUESDAY -> 1
            Calendar.WEDNESDAY -> 2
            Calendar.THURSDAY -> 3
            Calendar.FRIDAY -> 4
            Calendar.SATURDAY -> 5
            Calendar.SUNDAY -> 6
            else -> 0
        }

        initViews()
        loadTasks()
        selectDay(selectedDayIndex)
    }

    private fun initViews() {
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.setNavigationOnClickListener { onBackPressed() }

        dayMon = findViewById(R.id.dayMon)
        dayTue = findViewById(R.id.dayTue)
        dayWed = findViewById(R.id.dayWed)
        dayThu = findViewById(R.id.dayThu)
        dayFri = findViewById(R.id.dayFri)
        daySat = findViewById(R.id.daySat)
        daySun = findViewById(R.id.daySun)
        dayTextViews = listOf(dayMon, dayTue, dayWed, dayThu, dayFri, daySat, daySun)

        dayTextViews.forEachIndexed { index, tv ->
            tv.setOnClickListener {
                selectDay(index)
            }
        }

        rvTasks = findViewById(R.id.rvScheduledTasks)
        txtEmptyCalendar = findViewById(R.id.txtEmptyCalendar)
        rvTasks.layoutManager = LinearLayoutManager(this)

        taskAdapter = CalendarTaskAdapter(
            tasks = mutableListOf(),
            onToggleDone = { task, isChecked ->
                task.isDone = isChecked
                saveTasks()
                updateListForDay()
            },
            onDelete = { task ->
                allTasks.remove(task)
                saveTasks()
                updateListForDay()
            }
        )
        rvTasks.adapter = taskAdapter

        val btnAdd = findViewById<MaterialButton>(R.id.btnAddScheduledTask)
        btnAdd.setOnClickListener {
            showAddTaskDialog()
        }

        val adContainer = findViewById<ViewGroup>(R.id.ad_view)
        AdManager.loadBanner(this, adContainer, getString(R.string.banner_home_footer))
    }

    private fun selectDay(dayIndex: Int) {
        selectedDayIndex = dayIndex
        dayTextViews.forEachIndexed { idx, tv ->
            if (idx == dayIndex) {
                tv.setBackgroundResource(R.drawable.bg_white_rounded_10)
                tv.setTextColor(resources.getColor(R.color.toolBarColor))
            } else {
                tv.background = null
                tv.setTextColor(resources.getColor(R.color.black))
            }
        }
        updateListForDay()
    }

    private fun updateListForDay() {
        val filtered = allTasks.filter { it.dayOfWeek == selectedDayIndex }
        if (filtered.isEmpty()) {
            rvTasks.visibility = View.GONE
            txtEmptyCalendar.visibility = View.VISIBLE
        } else {
            rvTasks.visibility = View.VISIBLE
            txtEmptyCalendar.visibility = View.GONE
            taskAdapter.updateItems(filtered)
        }
    }

    private fun showAddTaskDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.dialog_add_task_title)

        val input = EditText(this)
        input.hint = getString(R.string.hint_task_name)
        input.setPadding(32, 24, 32, 24)
        builder.setView(input)

        builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
            val taskText = input.text.toString().trim()
            if (taskText.isNotEmpty()) {
                val newTask = BeautyCalendarTask(
                    id = System.currentTimeMillis().toString(),
                    dayOfWeek = selectedDayIndex,
                    title = taskText,
                    isDone = false
                )
                allTasks.add(newTask)
                saveTasks()
                updateListForDay()
            }
            dialog.dismiss()
        }
        builder.setNegativeButton(android.R.string.cancel) { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun loadTasks() {
        allTasks.clear()
        val prefs = getSharedPreferences("beautytips_prefs", Context.MODE_PRIVATE)
        val jsonStr = prefs.getString("beauty_calendar_tasks_json", null)

        if (jsonStr != null) {
            try {
                val array = JSONArray(jsonStr)
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    allTasks.add(
                        BeautyCalendarTask(
                            id = obj.getString("id"),
                            dayOfWeek = obj.getInt("dayOfWeek"),
                            title = obj.getString("title"),
                            isDone = obj.optBoolean("isDone", false)
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            // Seed defaults for Sun, Wed, Fri
            allTasks.add(BeautyCalendarTask("def_sun", 6, getString(R.string.default_task_sun), false))
            allTasks.add(BeautyCalendarTask("def_wed", 2, getString(R.string.default_task_wed), false))
            allTasks.add(BeautyCalendarTask("def_fri", 4, getString(R.string.default_task_fri), false))
            saveTasks()
        }
    }

    private fun saveTasks() {
        val array = JSONArray()
        allTasks.forEach { task ->
            val obj = JSONObject()
            obj.put("id", task.id)
            obj.put("dayOfWeek", task.dayOfWeek)
            obj.put("title", task.title)
            obj.put("isDone", task.isDone)
            array.put(obj)
        }
        getSharedPreferences("beautytips_prefs", Context.MODE_PRIVATE)
            .edit()
            .putString("beauty_calendar_tasks_json", array.toString())
            .apply()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(this)
        return true
    }
}

class CalendarTaskAdapter(
    private val tasks: MutableList<BeautyCalendarTask>,
    private val onToggleDone: (BeautyCalendarTask, Boolean) -> Unit,
    private val onDelete: (BeautyCalendarTask) -> Unit
) : RecyclerView.Adapter<CalendarTaskAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cbDone: CheckBox = itemView.findViewById(R.id.cbTaskDone)
        val txtTitle: TextView = itemView.findViewById(R.id.txtTaskTitle)
        val imgDelete: ImageView = itemView.findViewById(R.id.imgDeleteTask)
    }

    fun updateItems(newItems: List<BeautyCalendarTask>) {
        tasks.clear()
        tasks.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scheduled_task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        holder.txtTitle.text = task.title

        // Avoid triggering listener during bind
        holder.cbDone.setOnCheckedChangeListener(null)
        holder.cbDone.isChecked = task.isDone

        if (task.isDone) {
            holder.txtTitle.paintFlags = holder.txtTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            holder.txtTitle.setTextColor(0xFF888888.toInt())
        } else {
            holder.txtTitle.paintFlags = holder.txtTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            holder.txtTitle.setTextColor(0xFF333333.toInt())
        }

        holder.cbDone.setOnCheckedChangeListener { _, isChecked ->
            onToggleDone(task, isChecked)
        }

        holder.imgDelete.setOnClickListener {
            onDelete(task)
        }
    }

    override fun getItemCount(): Int = tasks.size
}
