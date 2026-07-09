package com.kp.beautytips.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.material.button.MaterialButton
import com.kp.beautytips.R
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.data.CustomTipDbHelper
import com.kp.beautytips.model.CustomTip
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class AddEditCustomTipActivity : BaseActivity() {

    private lateinit var dbHelper: CustomTipDbHelper
    private lateinit var etCustomTipTitle: EditText
    private lateinit var spCustomTipCategory: Spinner
    private lateinit var etCustomTipDuration: EditText
    private lateinit var etCustomTipDetails: EditText
    private lateinit var btnSaveCustomTip: MaterialButton
    private lateinit var btnDeleteCustomTip: MaterialButton
    private lateinit var txtTabTitle: AppCompatTextView

    private var customTipId: Int = -1
    private var isEditMode = false

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_custom_tip)

        dbHelper = CustomTipDbHelper(this)
        init()
        setupData()
    }

    private fun init() {
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.navigationIcon?.setTint(Color.WHITE)

        txtTabTitle = findViewById(R.id.txtTabTitle)
        etCustomTipTitle = findViewById(R.id.etCustomTipTitle)
        spCustomTipCategory = findViewById(R.id.spCustomTipCategory)
        etCustomTipDuration = findViewById(R.id.etCustomTipDuration)
        etCustomTipDetails = findViewById(R.id.etCustomTipDetails)
        btnSaveCustomTip = findViewById(R.id.btnSaveCustomTip)
        btnDeleteCustomTip = findViewById(R.id.btnDeleteCustomTip)

        // Setup Spinner
        val categories = arrayOf(
            getString(R.string.custom_tip_category_face),
            getString(R.string.custom_tip_category_hair),
            getString(R.string.custom_tip_category_skin),
            getString(R.string.custom_tip_category_eyes),
            getString(R.string.custom_tip_category_handsfeet)
        )
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spCustomTipCategory.adapter = spinnerAdapter

        // Default Title
        txtTabTitle.text = getString(R.string.add_custom_tip)

        // Save
        btnSaveCustomTip.setOnClickListener {
            saveTip()
        }

        // Delete
        btnDeleteCustomTip.setOnClickListener {
            confirmDelete()
        }

        loadAdBanner()
    }

    private fun setupData() {
        val bundle = intent.extras
        if (bundle != null && bundle.containsKey("tip_id")) {
            customTipId = bundle.getInt("tip_id", -1)
            val title = bundle.getString("tip_title", "")
            val duration = bundle.getString("tip_duration", "")
            val details = bundle.getString("tip_details", "")
            val category = bundle.getString("tip_category", "")

            isEditMode = true
            txtTabTitle.text = getString(R.string.edit_custom_tip)
            etCustomTipTitle.setText(title)
            etCustomTipDuration.setText(duration)
            etCustomTipDetails.setText(details)

            // Select Spinner category
            val categories = arrayOf(
                getString(R.string.custom_tip_category_face),
                getString(R.string.custom_tip_category_hair),
                getString(R.string.custom_tip_category_skin),
                getString(R.string.custom_tip_category_eyes),
                getString(R.string.custom_tip_category_handsfeet)
            )
            val index = categories.indexOf(category)
            if (index != -1) {
                spCustomTipCategory.setSelection(index)
            }

            btnDeleteCustomTip.visibility = View.VISIBLE
        }
    }

    private fun saveTip() {
        val titleStr = etCustomTipTitle.text.toString().trim()
        val durationStr = etCustomTipDuration.text.toString().trim()
        val detailsStr = etCustomTipDetails.text.toString().trim()
        val categoryStr = spCustomTipCategory.selectedItem.toString()

        if (titleStr.isEmpty() || detailsStr.isEmpty()) {
            Toast.makeText(this, getString(R.string.custom_tip_empty_fields_warning), Toast.LENGTH_SHORT).show()
            return
        }

        if (customTipId == -1) {
            dbHelper.insertTip(titleStr, durationStr, detailsStr, categoryStr)
        } else {
            dbHelper.updateTip(customTipId, titleStr, durationStr, detailsStr, categoryStr)
        }

        Toast.makeText(this, getString(R.string.custom_tip_saved_toast), Toast.LENGTH_SHORT).show()
        
        // If editing, launch Details activity and clear top so we go back to custom tips activity correctly
        if (isEditMode) {
            val updatedTip = CustomTip(customTipId, titleStr, durationStr, detailsStr, categoryStr)
            val intent = Intent(this, CustomTipDetailsActivity::class.java).apply {
                putExtra("custom_tip", updatedTip)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
        }
        
        finish()
        AppUtils.finishFromLeftToRight(this)
    }

    private fun confirmDelete() {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.custom_tip_delete_confirm))
            .setPositiveButton(android.R.string.yes) { _, _ ->
                deleteTip()
            }
            .setNegativeButton(android.R.string.no, null)
            .show()
    }

    private fun deleteTip() {
        if (customTipId != -1) {
            dbHelper.deleteTip(customTipId)
            Toast.makeText(this, getString(R.string.custom_tip_deleted_toast), Toast.LENGTH_SHORT).show()
            
            // Go back to CustomTipsActivity, clearing intermediate activities
            val intent = Intent(this, CustomTipsActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(intent)
            finish()
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
