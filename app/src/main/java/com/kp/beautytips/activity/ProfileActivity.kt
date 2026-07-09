package com.kp.beautytips.activity
import android.view.ViewGroup

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.kp.beautytips.R
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class ProfileActivity : BaseActivity() {

    private lateinit var spinnerSkinType: Spinner
    private lateinit var spinnerHairType: Spinner
    private lateinit var spinnerAgeGroup: Spinner
    private lateinit var spinnerPrimaryConcern: Spinner
    private lateinit var cardSkinTestLink: MaterialCardView
    private lateinit var btnSaveProfile: MaterialButton

    // Option Definitions
    private data class SpinnerOption(val key: String, val displayResId: Int)

    private val skinTypeOptions = listOf(
        SpinnerOption("Normal", R.string.skin_type_normal),
        SpinnerOption("Dry", R.string.skin_type_dry),
        SpinnerOption("Oily", R.string.skin_type_oily),
        SpinnerOption("Sensitive", R.string.skin_type_sensitive),
        SpinnerOption("Combination", R.string.skin_type_combination)
    )

    private val hairTypeOptions = listOf(
        SpinnerOption("Normal", R.string.profile_hair_normal),
        SpinnerOption("Dry", R.string.profile_hair_dry),
        SpinnerOption("Oily", R.string.profile_hair_oily),
        SpinnerOption("Dandruff", R.string.profile_hair_dandruff)
    )

    private val ageGroupOptions = listOf(
        SpinnerOption("Teens", R.string.profile_age_teens),
        SpinnerOption("20s", R.string.profile_age_20s),
        SpinnerOption("30s", R.string.profile_age_30s),
        SpinnerOption("40s", R.string.profile_age_40s)
    )

    private val concernOptions = listOf(
        SpinnerOption("acne_title", R.string.acne_title),
        SpinnerOption("glowingskin_title", R.string.glowingskin_title),
        SpinnerOption("hairfall_title", R.string.hairfall_title),
        SpinnerOption("dandruff_title", R.string.dandruff_title),
        SpinnerOption("wrinkles_title", R.string.wrinkles_title),
        SpinnerOption("darkcircle_title", R.string.darkcircle_title),
        SpinnerOption("detan_title", R.string.detan_title),
        SpinnerOption("teethwhitening_title", R.string.teethwhitening_title)
    )

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        init()
        loadProfile()
    }

    override fun onResume() {
        super.onResume()
        // If they took the skin test, reload skin type choice from shared prefs
        val prefs = getSharedPreferences("BeautyProfile", Context.MODE_PRIVATE)
        val savedSkinType = prefs.getString("profile_skin_type", "Normal")
        val skinIndex = skinTypeOptions.indexOfFirst { it.key == savedSkinType }
        if (skinIndex != -1) {
            spinnerSkinType.setSelection(skinIndex)
        }
    }

    private fun init() {
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.navigationIcon?.setTint(Color.WHITE)

        val txtTabTitle = findViewById<AppCompatTextView>(R.id.txtTabTitle)
        txtTabTitle.text = getString(R.string.settings_profile)

        spinnerSkinType = findViewById(R.id.spinnerSkinType)
        spinnerHairType = findViewById(R.id.spinnerHairType)
        spinnerAgeGroup = findViewById(R.id.spinnerAgeGroup)
        spinnerPrimaryConcern = findViewById(R.id.spinnerPrimaryConcern)
        cardSkinTestLink = findViewById(R.id.cardSkinTestLink)
        btnSaveProfile = findViewById(R.id.btnSaveProfile)

        // Set Spinners Adapters
        setupSpinner(spinnerSkinType, skinTypeOptions)
        setupSpinner(spinnerHairType, hairTypeOptions)
        setupSpinner(spinnerAgeGroup, ageGroupOptions)
        setupSpinner(spinnerPrimaryConcern, concernOptions)

        cardSkinTestLink.setOnClickListener {
            // Launch Skin Test quiz
            val intent = Intent(this, SkinTestActivity::class.java)
            startActivity(intent)
            AppUtils.startFromRightToLeft(this)
        }

        btnSaveProfile.setOnClickListener {
            saveProfile()
        }

        loadAdBanner()
    }

    private fun setupSpinner(spinner: Spinner, options: List<SpinnerOption>) {
        val displayNames = options.map { getString(it.displayResId) }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, displayNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun loadProfile() {
        val prefs = getSharedPreferences("BeautyProfile", Context.MODE_PRIVATE)
        
        val savedSkinType = prefs.getString("profile_skin_type", "Normal")
        val savedHairType = prefs.getString("profile_hair_type", "Normal")
        val savedAgeGroup = prefs.getString("profile_age_group", "20s")
        val savedConcern = prefs.getString("profile_primary_concern", "acne_title")

        val skinIndex = skinTypeOptions.indexOfFirst { it.key == savedSkinType }
        if (skinIndex != -1) spinnerSkinType.setSelection(skinIndex)

        val hairIndex = hairTypeOptions.indexOfFirst { it.key == savedHairType }
        if (hairIndex != -1) spinnerHairType.setSelection(hairIndex)

        val ageIndex = ageGroupOptions.indexOfFirst { it.key == savedAgeGroup }
        if (ageIndex != -1) spinnerAgeGroup.setSelection(ageIndex)

        val concernIndex = concernOptions.indexOfFirst { it.key == savedConcern }
        if (concernIndex != -1) spinnerPrimaryConcern.setSelection(concernIndex)
    }

    private fun saveProfile() {
        val selectedSkin = skinTypeOptions[spinnerSkinType.selectedItemPosition].key
        val selectedHair = hairTypeOptions[spinnerHairType.selectedItemPosition].key
        val selectedAge = ageGroupOptions[spinnerAgeGroup.selectedItemPosition].key
        val selectedConcern = concernOptions[spinnerPrimaryConcern.selectedItemPosition].key

        val prefs = getSharedPreferences("BeautyProfile", Context.MODE_PRIVATE)
        prefs.edit().apply {
            putString("profile_skin_type", selectedSkin)
            putString("profile_hair_type", selectedHair)
            putString("profile_age_group", selectedAge)
            putString("profile_primary_concern", selectedConcern)
            putBoolean("profile_is_configured", true)
            apply()
        }

        Toast.makeText(this, getString(R.string.profile_saved_toast), Toast.LENGTH_SHORT).show()
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
