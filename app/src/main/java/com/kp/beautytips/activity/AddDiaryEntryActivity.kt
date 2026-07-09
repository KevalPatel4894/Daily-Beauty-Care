package com.kp.beautytips.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.material.button.MaterialButton
import com.kp.beautytips.R
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.data.DiaryDbHelper
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class AddDiaryEntryActivity : BaseActivity() {

    private lateinit var dbHelper: DiaryDbHelper
    private lateinit var txtEntryDate: AppCompatTextView
    private lateinit var btnChangeDate: AppCompatImageButton
    private lateinit var etEntryTitle: EditText
    private lateinit var etEntryContent: EditText
    private lateinit var btnAddPhoto: MaterialButton
    private lateinit var layoutPhotoPreview: FrameLayout
    private lateinit var imgEntryPhoto: ImageView
    private lateinit var btnRemovePhoto: AppCompatImageButton
    private lateinit var btnSaveEntry: MaterialButton
    private lateinit var btnDeleteEntry: MaterialButton

    private var entryId: Int = -1
    private var existingImagePath: String? = null
    private var selectedImageUri: Uri? = null
    private val calendar = Calendar.getInstance()
    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    // Activity Result Launcher for Photo Picking
    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            selectedImageUri = uri
            layoutPhotoPreview.visibility = View.VISIBLE
            Glide.with(this)
                .load(uri)
                .into(imgEntryPhoto)
        }
    }

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_diary_entry)

        dbHelper = DiaryDbHelper(this)

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

        val txtTabTitle = findViewById<AppCompatTextView>(R.id.txtTabTitle)
        
        txtEntryDate = findViewById(R.id.txtEntryDate)
        btnChangeDate = findViewById(R.id.btnChangeDate)
        etEntryTitle = findViewById(R.id.etEntryTitle)
        etEntryContent = findViewById(R.id.etEntryContent)
        btnAddPhoto = findViewById(R.id.btnAddPhoto)
        layoutPhotoPreview = findViewById(R.id.layoutPhotoPreview)
        imgEntryPhoto = findViewById(R.id.imgEntryPhoto)
        btnRemovePhoto = findViewById(R.id.btnRemovePhoto)
        btnSaveEntry = findViewById(R.id.btnSaveEntry)
        btnDeleteEntry = findViewById(R.id.btnDeleteEntry)

        // Default Date
        txtEntryDate.text = dateFormatter.format(calendar.time)

        // Date Picker
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            txtEntryDate.text = dateFormatter.format(calendar.time)
        }

        val datePickerClick = View.OnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        btnChangeDate.setOnClickListener(datePickerClick)
        txtEntryDate.setOnClickListener(datePickerClick)

        // Photo selection
        btnAddPhoto.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        // Photo removal
        btnRemovePhoto.setOnClickListener {
            selectedImageUri = null
            existingImagePath = null
            layoutPhotoPreview.visibility = View.GONE
        }

        // Save entry
        btnSaveEntry.setOnClickListener {
            saveEntry()
        }

        // Delete entry
        btnDeleteEntry.setOnClickListener {
            confirmDelete()
        }

        loadAdBanner()
    }

    private fun setupData() {
        val bundle = intent.extras
        if (bundle != null && bundle.containsKey("entry_id")) {
            entryId = bundle.getInt("entry_id", -1)
            val dateStr = bundle.getString("entry_date", "")
            val titleStr = bundle.getString("entry_title", "")
            val contentStr = bundle.getString("entry_content", "")
            existingImagePath = bundle.getString("entry_image")

            val txtTabTitle = findViewById<AppCompatTextView>(R.id.txtTabTitle)
            txtTabTitle.text = getString(R.string.diary_edit_entry)

            etEntryTitle.setText(titleStr)
            etEntryContent.setText(contentStr)
            if (!dateStr.isNullOrEmpty()) {
                txtEntryDate.text = dateStr
                try {
                    val date = dateFormatter.parse(dateStr)
                    if (date != null) {
                        calendar.time = date
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            if (!existingImagePath.isNullOrEmpty()) {
                val file = File(existingImagePath!!)
                if (file.exists()) {
                    layoutPhotoPreview.visibility = View.VISIBLE
                    Glide.with(this)
                        .load(file)
                        .into(imgEntryPhoto)
                }
            }

            btnDeleteEntry.visibility = View.VISIBLE
        }
    }

    private fun saveEntry() {
        val titleStr = etEntryTitle.text.toString().trim()
        val contentStr = etEntryContent.text.toString().trim()
        val dateStr = txtEntryDate.text.toString()

        if (titleStr.isEmpty()) {
            etEntryTitle.error = "Title required"
            etEntryTitle.requestFocus()
            return
        }

        if (contentStr.isEmpty()) {
            etEntryContent.error = "Description required"
            etEntryContent.requestFocus()
            return
        }

        var finalImagePath: String? = existingImagePath

        // If a new photo is selected, save it locally
        if (selectedImageUri != null) {
            try {
                // Delete old file if existed
                if (!existingImagePath.isNullOrEmpty()) {
                    val oldFile = File(existingImagePath!!)
                    if (oldFile.exists()) {
                        oldFile.delete()
                    }
                }

                // Copy new photo to internal storage
                val directory = File(filesDir, "diary_photos")
                if (!directory.exists()) {
                    directory.mkdirs()
                }
                val fileName = "diary_${System.currentTimeMillis()}.jpg"
                val destFile = File(directory, fileName)

                contentResolver.openInputStream(selectedImageUri!!)?.use { input ->
                    FileOutputStream(destFile).use { output ->
                        input.copyTo(output)
                    }
                }
                finalImagePath = destFile.absolutePath
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Failed to save photo log", Toast.LENGTH_SHORT).show()
            }
        } else if (existingImagePath == null) {
            // User explicitly removed the image
            finalImagePath = null
        }

        if (entryId == -1) {
            dbHelper.insertEntry(dateStr, titleStr, contentStr, finalImagePath)
        } else {
            dbHelper.updateEntry(entryId, dateStr, titleStr, contentStr, finalImagePath)
        }

        Toast.makeText(this, getString(R.string.diary_saved_toast), Toast.LENGTH_SHORT).show()
        finish()
        AppUtils.finishFromLeftToRight(this)
    }

    private fun confirmDelete() {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.diary_delete_confirm))
            .setPositiveButton(android.R.string.yes) { _, _ ->
                deleteEntry()
            }
            .setNegativeButton(android.R.string.no, null)
            .show()
    }

    private fun deleteEntry() {
        if (entryId != -1) {
            // Delete local photo
            if (!existingImagePath.isNullOrEmpty()) {
                val file = File(existingImagePath!!)
                if (file.exists()) {
                    file.delete()
                }
            }
            dbHelper.deleteEntry(entryId)
            Toast.makeText(this, getString(R.string.diary_deleted_toast), Toast.LENGTH_SHORT).show()
        }
        finish()
        AppUtils.finishFromLeftToRight(this)
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
