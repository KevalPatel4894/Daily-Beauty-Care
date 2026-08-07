package com.kp.beautytips.activity

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.kp.beautytips.R
import com.kp.beautytips.adapter.SeasonalAdapter
import com.kp.beautytips.model.SeasonalTipModel
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class CycleAdvisorActivity : BaseActivity() {

    private lateinit var txtLastPeriod: TextView
    private lateinit var txtCycleLengthLabel: TextView
    private lateinit var txtCurrentDay: TextView
    private lateinit var txtPhaseName: TextView
    private lateinit var txtPhaseDesc: TextView
    private lateinit var rvPhaseTips: RecyclerView
    private lateinit var adapter: SeasonalAdapter

    private lateinit var prefs: SharedPreferences
    private var lastPeriodTimeMillis: Long = 0
    private var cycleLengthDays: Int = 28

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cycle_advisor)

        prefs = getSharedPreferences("cycle_advisor_prefs", Context.MODE_PRIVATE)
        lastPeriodTimeMillis = prefs.getLong("last_period_millis", System.currentTimeMillis() - TimeUnit.DAYS.toMillis(14))
        cycleLengthDays = prefs.getInt("cycle_length", 28)

        initViews()
        updateCycleStatus()
    }

    private fun initViews() {
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.setNavigationOnClickListener { onBackPressed() }

        txtLastPeriod = findViewById(R.id.txtLastPeriod)
        txtCycleLengthLabel = findViewById(R.id.txtCycleLengthLabel)
        txtCurrentDay = findViewById(R.id.txtCurrentDay)
        txtPhaseName = findViewById(R.id.txtPhaseName)
        txtPhaseDesc = findViewById(R.id.txtPhaseDesc)
        rvPhaseTips = findViewById(R.id.rvPhaseTips)

        val btnSelectPeriodDate = findViewById<MaterialButton>(R.id.btnSelectPeriodDate)
        val btnMinusCycle = findViewById<MaterialButton>(R.id.btnMinusCycle)
        val btnPlusCycle = findViewById<MaterialButton>(R.id.btnPlusCycle)

        adapter = SeasonalAdapter(emptyList())
        rvPhaseTips.layoutManager = LinearLayoutManager(this)
        rvPhaseTips.adapter = adapter

        btnSelectPeriodDate.setOnClickListener {
            showDatePicker()
        }

        btnMinusCycle.setOnClickListener {
            if (cycleLengthDays > 21) {
                cycleLengthDays--
                saveCycleSettings()
                updateCycleStatus()
            }
        }

        btnPlusCycle.setOnClickListener {
            if (cycleLengthDays < 35) {
                cycleLengthDays++
                saveCycleSettings()
                updateCycleStatus()
            }
        }

        val adContainer = findViewById<ViewGroup>(R.id.ad_view)
        AdManager.loadBanner(this, adContainer, getString(R.string.banner_home_footer))
    }

    private fun showDatePicker() {
        val cal = Calendar.getInstance().apply { timeInMillis = lastPeriodTimeMillis }
        val dialog = DatePickerDialog(
            this,
            R.style.DialogTheme,
            { _, year, month, dayOfMonth ->
                val selectedCal = Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                }
                lastPeriodTimeMillis = selectedCal.timeInMillis
                saveCycleSettings()
                updateCycleStatus()
                Toast.makeText(this, R.string.msg_cycle_saved, Toast.LENGTH_SHORT).show()
            },
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
        dialog.datePicker.maxDate = System.currentTimeMillis()
        dialog.setOnShowListener {
            dialog.getButton(DatePickerDialog.BUTTON_POSITIVE)?.setTextColor(resources.getColor(R.color.toolBarColor))
            dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)?.setTextColor(resources.getColor(R.color.toolBarColor))
        }
        dialog.show()
    }

    private fun saveCycleSettings() {
        prefs.edit()
            .putLong("last_period_millis", lastPeriodTimeMillis)
            .putInt("cycle_length", cycleLengthDays)
            .apply()
        com.kp.beautytips.utils.CycleReminderScheduler.scheduleCycleReminder(this)
    }

    private fun updateCycleStatus() {
        val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        txtLastPeriod.text = getString(R.string.lbl_last_period, dateFormat.format(Date(lastPeriodTimeMillis)))
        txtCycleLengthLabel.text = getString(R.string.lbl_cycle_length, cycleLengthDays)

        val diffMillis = System.currentTimeMillis() - lastPeriodTimeMillis
        val diffDays = (TimeUnit.MILLISECONDS.toDays(diffMillis) % cycleLengthDays) + 1
        val currentDay = diffDays.toInt()

        txtCurrentDay.text = getString(R.string.lbl_current_day, currentDay, cycleLengthDays)

        val tipsList = ArrayList<SeasonalTipModel>()

        when {
            // Days 1-5: Menstrual Phase
            currentDay in 1..5 -> {
                txtPhaseName.text = getString(R.string.phase_menstrual_name)
                txtPhaseDesc.text = getString(R.string.phase_menstrual_desc)
                tipsList.add(
                    SeasonalTipModel(
                        title = getString(R.string.phase_menstrual_tip_title),
                        description = getString(R.string.phase_menstrual_tip_desc)
                    )
                )
            }
            // Days 6-13: Follicular Phase
            currentDay in 6..13 -> {
                txtPhaseName.text = getString(R.string.phase_follicular_name)
                txtPhaseDesc.text = getString(R.string.phase_follicular_desc)
                tipsList.add(
                    SeasonalTipModel(
                        title = getString(R.string.phase_follicular_tip_title),
                        description = getString(R.string.phase_follicular_tip_desc)
                    )
                )
            }
            // Days 14-16: Ovulation Phase
            currentDay in 14..16 -> {
                txtPhaseName.text = getString(R.string.phase_ovulation_name)
                txtPhaseDesc.text = getString(R.string.phase_ovulation_desc)
                tipsList.add(
                    SeasonalTipModel(
                        title = getString(R.string.phase_ovulation_tip_title),
                        description = getString(R.string.phase_ovulation_tip_desc)
                    )
                )
            }
            // Days 17-cycleLength: Luteal Phase (Hormonal Acne Risk)
            else -> {
                txtPhaseName.text = getString(R.string.phase_luteal_name)
                txtPhaseDesc.text = getString(R.string.phase_luteal_desc)
                tipsList.add(
                    SeasonalTipModel(
                        title = getString(R.string.phase_luteal_tip1_title),
                        description = getString(R.string.phase_luteal_tip1_desc)
                    )
                )
                tipsList.add(
                    SeasonalTipModel(
                        title = getString(R.string.phase_luteal_tip2_title),
                        description = getString(R.string.phase_luteal_tip2_desc)
                    )
                )
            }
        }

        adapter.updateData(tipsList)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(this)
        return true
    }
}
