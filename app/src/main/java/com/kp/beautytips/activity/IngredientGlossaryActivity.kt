package com.kp.beautytips.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.kp.beautytips.R
import com.kp.beautytips.adapter.IngredientAdapter
import com.kp.beautytips.model.IngredientModel
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class IngredientGlossaryActivity : BaseActivity() {

    private lateinit var rvIngredients: RecyclerView
    private lateinit var layoutEmptyIngredients: View
    private lateinit var etIngredientSearch: AppCompatEditText
    private lateinit var imgClearSearch: AppCompatImageView
    private lateinit var adapter: IngredientAdapter

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient_glossary)
        init()
    }

    private fun init() {
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.navigationIcon?.setTint(Color.WHITE)

        val txtTabTitle = findViewById<AppCompatTextView>(R.id.txtTabTitle)
        txtTabTitle.text = getString(R.string.ingredient_glossary_title)

        rvIngredients = findViewById(R.id.rvIngredients)
        layoutEmptyIngredients = findViewById(R.id.layoutEmptyIngredients)
        etIngredientSearch = findViewById(R.id.etIngredientSearch)
        imgClearSearch = findViewById(R.id.imgClearSearch)

        loadAdBanner()

        val ingredientsList = getIngredients()
        adapter = IngredientAdapter(this, ingredientsList) { count ->
            if (count == 0) {
                layoutEmptyIngredients.visibility = View.VISIBLE
                rvIngredients.visibility = View.GONE
            } else {
                layoutEmptyIngredients.visibility = View.GONE
                rvIngredients.visibility = View.VISIBLE
            }
        }

        rvIngredients.layoutManager = LinearLayoutManager(this)
        rvIngredients.adapter = adapter

        etIngredientSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = s?.toString() ?: ""
                adapter.filter.filter(text)
                if (text.isNotEmpty()) {
                    imgClearSearch.visibility = View.VISIBLE
                } else {
                    imgClearSearch.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        imgClearSearch.setOnClickListener {
            etIngredientSearch.setText("")
        }
    }

    private fun getIngredients(): List<IngredientModel> {
        return listOf(
            IngredientModel("honey", R.string.ingredient_honey_name, R.string.ingredient_honey_benefits, R.drawable.ic_book_white, R.string.ingredient_honey_keywords),
            IngredientModel("lemon", R.string.ingredient_lemon_name, R.string.ingredient_lemon_benefits, R.drawable.ic_book_white, R.string.ingredient_lemon_keywords),
            IngredientModel("aloe_vera", R.string.ingredient_aloe_vera_name, R.string.ingredient_aloe_vera_benefits, R.drawable.ic_book_white, R.string.ingredient_aloe_vera_keywords),
            IngredientModel("coconut_oil", R.string.ingredient_coconut_oil_name, R.string.ingredient_coconut_oil_benefits, R.drawable.ic_book_white, R.string.ingredient_coconut_oil_keywords),
            IngredientModel("cucumber", R.string.ingredient_cucumber_name, R.string.ingredient_cucumber_benefits, R.drawable.ic_book_white, R.string.ingredient_cucumber_keywords),
            IngredientModel("yogurt", R.string.ingredient_yogurt_name, R.string.ingredient_yogurt_benefits, R.drawable.ic_book_white, R.string.ingredient_yogurt_keywords),
            IngredientModel("turmeric", R.string.ingredient_turmeric_name, R.string.ingredient_turmeric_benefits, R.drawable.ic_book_white, R.string.ingredient_turmeric_keywords),
            IngredientModel("rose_water", R.string.ingredient_rose_water_name, R.string.ingredient_rose_water_benefits, R.drawable.ic_book_white, R.string.ingredient_rose_water_keywords),
            IngredientModel("milk", R.string.ingredient_milk_name, R.string.ingredient_milk_benefits, R.drawable.ic_book_white, R.string.ingredient_milk_keywords),
            IngredientModel("oatmeal", R.string.ingredient_oatmeal_name, R.string.ingredient_oatmeal_benefits, R.drawable.ic_book_white, R.string.ingredient_oatmeal_keywords),
            IngredientModel("green_tea", R.string.ingredient_green_tea_name, R.string.ingredient_green_tea_benefits, R.drawable.ic_book_white, R.string.ingredient_green_tea_keywords),
            IngredientModel("egg", R.string.ingredient_egg_name, R.string.ingredient_egg_benefits, R.drawable.ic_book_white, R.string.ingredient_egg_keywords)
        )
    }

    private fun loadAdBanner() {
        val adContainer = findViewById<View>(R.id.ad_view) ?: return
        val adRequest = AdRequest.Builder().build()
        val mAdView = AdView(this)
        mAdView.setAdSize(AdSize.BANNER)
        mAdView.adUnitId = getString(R.string.banner_home_footer)
        (adContainer as RelativeLayout).addView(mAdView)
        mAdView.loadAd(adRequest)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(this)
        return true
    }
}
