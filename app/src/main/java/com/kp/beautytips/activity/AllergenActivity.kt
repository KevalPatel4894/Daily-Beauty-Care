package com.kp.beautytips.activity

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton
import com.kp.beautytips.R
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class AllergenActivity : BaseActivity() {

    private lateinit var cbLemon: CheckBox
    private lateinit var cbHoney: CheckBox
    private lateinit var cbCoconut: CheckBox
    private lateinit var cbEgg: CheckBox
    private lateinit var cbMilk: CheckBox
    private lateinit var cbNuts: CheckBox
    private lateinit var cbTeaTree: CheckBox
    private lateinit var cbOnion: CheckBox

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_allergen)
        initViews()
        loadSavedAllergens()
    }

    private fun initViews() {
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.setNavigationOnClickListener { onBackPressed() }

        cbLemon = findViewById(R.id.cbLemon)
        cbHoney = findViewById(R.id.cbHoney)
        cbCoconut = findViewById(R.id.cbCoconut)
        cbEgg = findViewById(R.id.cbEgg)
        cbMilk = findViewById(R.id.cbMilk)
        cbNuts = findViewById(R.id.cbNuts)
        cbTeaTree = findViewById(R.id.cbTeaTree)
        cbOnion = findViewById(R.id.cbOnion)

        val btnSave = findViewById<MaterialButton>(R.id.btnSaveAllergens)
        btnSave.setOnClickListener {
            saveAllergens()
        }

        val adContainer = findViewById<ViewGroup>(R.id.ad_view)
        AdManager.loadBanner(this, adContainer, getString(R.string.banner_home_footer))
    }

    private fun loadSavedAllergens() {
        val prefs = getSharedPreferences("beautytips_prefs", Context.MODE_PRIVATE)
        val savedSet = prefs.getStringSet("avoided_ingredients_set", emptySet()) ?: emptySet()

        cbLemon.isChecked = savedSet.contains("lemon")
        cbHoney.isChecked = savedSet.contains("honey")
        cbCoconut.isChecked = savedSet.contains("coconut")
        cbEgg.isChecked = savedSet.contains("egg")
        cbMilk.isChecked = savedSet.contains("milk") || savedSet.contains("dairy")
        cbNuts.isChecked = savedSet.contains("almond") || savedSet.contains("nut")
        cbTeaTree.isChecked = savedSet.contains("tea tree")
        cbOnion.isChecked = savedSet.contains("onion") || savedSet.contains("garlic")
    }

    private fun saveAllergens() {
        val newSet = HashSet<String>()
        if (cbLemon.isChecked) newSet.add("lemon")
        if (cbHoney.isChecked) newSet.add("honey")
        if (cbCoconut.isChecked) newSet.add("coconut")
        if (cbEgg.isChecked) newSet.add("egg")
        if (cbMilk.isChecked) {
            newSet.add("milk")
            newSet.add("dairy")
        }
        if (cbNuts.isChecked) {
            newSet.add("almond")
            newSet.add("nut")
        }
        if (cbTeaTree.isChecked) newSet.add("tea tree")
        if (cbOnion.isChecked) {
            newSet.add("onion")
            newSet.add("garlic")
        }

        val prefs = getSharedPreferences("beautytips_prefs", Context.MODE_PRIVATE)
        prefs.edit().putStringSet("avoided_ingredients_set", newSet).apply()

        Toast.makeText(this, R.string.msg_allergens_saved, Toast.LENGTH_SHORT).show()
        finish()
        AppUtils.finishFromLeftToRight(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(this)
        return true
    }
}
