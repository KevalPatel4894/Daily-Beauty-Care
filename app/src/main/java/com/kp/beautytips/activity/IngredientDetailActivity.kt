package com.kp.beautytips.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.kp.beautytips.R
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.adapter.ListAdapter
import com.kp.beautytips.data.TipRepository
import com.kp.beautytips.model.ListModel
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import com.bumptech.glide.Glide
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.util.Locale

class IngredientDetailActivity : BaseActivity() {

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient_detail)
        init()
    }

    private fun init() {
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.navigationIcon?.setTint(Color.WHITE)

        val nameResId = intent.getIntExtra("ingredient_name_res", 0)
        val benefitsResId = intent.getIntExtra("ingredient_benefits_res", 0)
        val imageResId = intent.getIntExtra("ingredient_image_res", 0)
        val keywordsResId = intent.getIntExtra("ingredient_keywords_res", 0)

        val ingredientName = if (nameResId != 0) getString(nameResId) else ""
        val txtTabTitle = findViewById<AppCompatTextView>(R.id.txtTabTitle)
        txtTabTitle.text = ingredientName

        val txtIngredientTitle = findViewById<AppCompatTextView>(R.id.txtIngredientTitle)
        txtIngredientTitle.text = ingredientName

        val txtBenefitsDetail = findViewById<AppCompatTextView>(R.id.txtBenefitsDetail)
        if (benefitsResId != 0) {
            txtBenefitsDetail.text = getString(benefitsResId)
        }

        val imgIngredient = findViewById<AppCompatImageView>(R.id.imgIngredient)
        if (imageResId != 0) {
            Glide.with(this)
                .load(imageResId)
                .placeholder(R.drawable.ic_book_white)
                .error(R.drawable.ic_book_white)
                .into(imgIngredient)
        }

        loadAdBanner()

        // Filter and display related remedies
        val rvRelatedRemedies = findViewById<RecyclerView>(R.id.rvRelatedRemedies)
        val tvNoRemedies = findViewById<AppCompatTextView>(R.id.tvNoRemedies)

        val matchingTips = getMatchingRemedies(keywordsResId)

        if (matchingTips.isEmpty()) {
            tvNoRemedies.visibility = View.VISIBLE
            rvRelatedRemedies.visibility = View.GONE
        } else {
            tvNoRemedies.visibility = View.GONE
            rvRelatedRemedies.visibility = View.VISIBLE
            
            val adapter = ListAdapter(ArrayList(matchingTips), ingredientName)
            rvRelatedRemedies.layoutManager = LinearLayoutManager(this)
            rvRelatedRemedies.adapter = adapter
        }
    }

    private fun getMatchingRemedies(keywordsResId: Int): List<ListModel> {
        if (keywordsResId == 0) return emptyList()

        val keywordsString = getString(keywordsResId)
        val keywordsList = keywordsString.split(",")
            .map { it.trim().lowercase(Locale.getDefault()) }
            .filter { it.isNotEmpty() }

        if (keywordsList.isEmpty()) return emptyList()

        val allTips = TipRepository.getAllTips(this)
        return allTips.filter { tip ->
            val titleLower = tip.title.lowercase(Locale.getDefault())
            val detailsLower = tip.details.lowercase(Locale.getDefault())
            keywordsList.any { keyword ->
                titleLower.contains(keyword) || detailsLower.contains(keyword)
            }
        }
    }

    private fun loadAdBanner() {
        val adContainer = findViewById<ViewGroup>(R.id.ad_view) ?: return
        AdManager.loadBanner(this, adContainer, getString(R.string.banner_home_footer))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(this)
        return true
    }
}
