package com.kp.beautytips.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kp.beautytips.R
import com.kp.beautytips.adapter.RoutineAdapter
import com.kp.beautytips.data.RoutineDbHelper
import com.kp.beautytips.model.RoutineModel
import com.kp.beautytips.model.RoutineStep
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AdManager
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class RoutineBuilderActivity : BaseActivity() {

    private lateinit var dbHelper: RoutineDbHelper
    private lateinit var rvRoutines: RecyclerView
    private lateinit var layoutEmptyRoutines: LinearLayout
    private lateinit var fabAddRoutine: FloatingActionButton
    private lateinit var adapter: RoutineAdapter
    private val routinesList = ArrayList<RoutineModel>()

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine_builder)

        dbHelper = RoutineDbHelper(this)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        loadRoutines()
    }

    private fun initViews() {
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolBar.setNavigationOnClickListener { onBackPressed() }

        rvRoutines = findViewById(R.id.rvRoutines)
        layoutEmptyRoutines = findViewById(R.id.layoutEmptyRoutines)
        fabAddRoutine = findViewById(R.id.fabAddRoutine)

        rvRoutines.layoutManager = LinearLayoutManager(this)
        adapter = RoutineAdapter(
            routines = routinesList,
            onStartClick = { routine ->
                val intent = Intent(this, RoutineRunnerActivity::class.java)
                intent.putExtra("routine", routine)
                startActivity(intent)
            },
            onEditClick = { routine ->
                showAddEditDialog(routine)
            },
            onDeleteClick = { routine ->
                confirmDelete(routine)
            }
        )
        rvRoutines.adapter = adapter

        fabAddRoutine.setOnClickListener {
            showAddEditDialog(null)
        }

        val adContainer = findViewById<RelativeLayout>(R.id.ad_view)
        AdManager.showBannerAd(this, adContainer)
    }

    private fun loadRoutines() {
        routinesList.clear()
        routinesList.addAll(dbHelper.getAllRoutines())
        adapter.updateData(routinesList)

        if (routinesList.isEmpty()) {
            layoutEmptyRoutines.visibility = View.VISIBLE
            rvRoutines.visibility = View.GONE
        } else {
            layoutEmptyRoutines.visibility = View.GONE
            rvRoutines.visibility = View.VISIBLE
        }
    }

    private fun confirmDelete(routine: RoutineModel) {
        AlertDialog.Builder(this)
            .setTitle(R.string.routine_builder_title)
            .setMessage(R.string.msg_confirm_delete_routine)
            .setPositiveButton(R.string.save) { _, _ ->
                dbHelper.deleteRoutine(routine.id)
                loadRoutines()
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }

    private fun showAddEditDialog(routine: RoutineModel?) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_edit_routine, null)
        val txtDialogTitle = dialogView.findViewById<TextView>(R.id.txtDialogTitle)
        val etRoutineTitle = dialogView.findViewById<EditText>(R.id.etRoutineTitle)
        val rgRoutineType = dialogView.findViewById<RadioGroup>(R.id.rgRoutineType)
        val rbMorning = dialogView.findViewById<RadioButton>(R.id.rbMorning)
        val rbNight = dialogView.findViewById<RadioButton>(R.id.rbNight)
        val rbCustom = dialogView.findViewById<RadioButton>(R.id.rbCustom)
        val layoutStepsContainer = dialogView.findViewById<LinearLayout>(R.id.layoutStepsContainer)
        val btnAddStepItem = dialogView.findViewById<View>(R.id.btnAddStepItem)
        val btnCancelDialog = dialogView.findViewById<View>(R.id.btnCancelDialog)
        val btnSaveRoutine = dialogView.findViewById<View>(R.id.btnSaveRoutine)

        val isEdit = (routine != null)
        if (isEdit) {
            txtDialogTitle.setText(R.string.edit_routine)
            etRoutineTitle.setText(routine?.title)
            when (routine?.type) {
                "Night" -> rbNight.isChecked = true
                "Custom" -> rbCustom.isChecked = true
                else -> rbMorning.isChecked = true
            }
            routine?.steps?.forEach { addStepRow(layoutStepsContainer, it) }
        } else {
            txtDialogTitle.setText(R.string.add_routine)
            // Add 1 default empty step row
            addStepRow(layoutStepsContainer, RoutineStep("", "", 60))
        }

        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        btnAddStepItem.setOnClickListener {
            addStepRow(layoutStepsContainer, RoutineStep("", "", 60))
        }

        btnCancelDialog.setOnClickListener {
            alertDialog.dismiss()
        }

        btnSaveRoutine.setOnClickListener {
            val title = etRoutineTitle.text.toString().trim()
            if (title.isEmpty()) {
                etRoutineTitle.error = getString(R.string.enter_title)
                return@setOnClickListener
            }

            val type = when (rgRoutineType.checkedRadioButtonId) {
                R.id.rbNight -> "Night"
                R.id.rbCustom -> "Custom"
                else -> "Morning"
            }

            val stepsList = ArrayList<RoutineStep>()
            for (i in 0 until layoutStepsContainer.childCount) {
                val stepView = layoutStepsContainer.getChildAt(i)
                val etName = stepView.findViewById<EditText>(R.id.etStepName)
                val etDesc = stepView.findViewById<EditText>(R.id.etStepDesc)
                val etTimer = stepView.findViewById<EditText>(R.id.etStepTimer)

                val stepName = etName.text.toString().trim()
                val stepDesc = etDesc.text.toString().trim()
                val timerSec = etTimer.text.toString().toIntOrNull() ?: 0

                if (stepName.isNotEmpty()) {
                    stepsList.add(RoutineStep(stepName, stepDesc, timerSec))
                }
            }

            if (stepsList.isEmpty()) {
                Toast.makeText(this, R.string.add_at_least_one_step, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (isEdit && routine != null) {
                dbHelper.updateRoutine(routine.id, title, type, stepsList)
            } else {
                dbHelper.insertRoutine(title, type, stepsList)
            }

            alertDialog.dismiss()
            loadRoutines()
        }

        alertDialog.show()
    }

    private fun addStepRow(container: LinearLayout, step: RoutineStep) {
        val rowView = LayoutInflater.from(this).inflate(R.layout.item_step_input, container, false)
        val etName = rowView.findViewById<EditText>(R.id.etStepName)
        val etDesc = rowView.findViewById<EditText>(R.id.etStepDesc)
        val etTimer = rowView.findViewById<EditText>(R.id.etStepTimer)
        val btnRemove = rowView.findViewById<ImageView>(R.id.btnRemoveStep)

        etName.setText(step.stepName)
        etDesc.setText(step.description)
        if (step.timerSeconds > 0) {
            etTimer.setText(step.timerSeconds.toString())
        }

        btnRemove.setOnClickListener {
            container.removeView(rowView)
        }

        container.addView(rowView)
    }
}
