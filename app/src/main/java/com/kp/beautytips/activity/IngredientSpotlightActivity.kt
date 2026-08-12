package com.kp.beautytips.activity

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.kp.beautytips.R
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import java.util.Calendar

data class SpotlightIngredient(
    val nameResId: Int,
    val taglineResId: Int,
    val descResId: Int,
    val benefitsResId: Int,
    val recipesResId: Int,
    val imageResId: Int
)

class IngredientSpotlightActivity : BaseActivity() {

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient_spotlight)

        initViews()
    }

    private fun initViews() {
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.setNavigationOnClickListener { onBackPressed() }

        val spotlightList = getSpotlightData()
        val dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        val currentSpotlight = spotlightList[dayOfYear % spotlightList.size]

        val imgHero = findViewById<ImageView>(R.id.imgIngredientHero)
        val txtName = findViewById<TextView>(R.id.txtIngredientName)
        val txtTagline = findViewById<TextView>(R.id.txtIngredientTagline)
        val txtDesc = findViewById<TextView>(R.id.txtIngredientDesc)
        val txtBenefits = findViewById<TextView>(R.id.txtIngredientBenefits)
        val txtRecipes = findViewById<TextView>(R.id.txtIngredientRecipes)

        imgHero.setImageResource(currentSpotlight.imageResId)
        txtName.text = getString(currentSpotlight.nameResId)
        txtTagline.text = getString(currentSpotlight.taglineResId)
        txtDesc.text = getString(currentSpotlight.descResId)
        txtBenefits.text = getString(currentSpotlight.benefitsResId)
        txtRecipes.text = getString(currentSpotlight.recipesResId)

        val adContainer = findViewById<ViewGroup>(R.id.ad_view)
        AdManager.loadBanner(this, adContainer, getString(R.string.banner_home_footer))
    }

    companion object {
        fun getSpotlightData(): List<SpotlightIngredient> {
            return listOf(
                SpotlightIngredient(
                    R.string.spotlight_turmeric_name,
                    R.string.spotlight_turmeric_tagline,
                    R.string.spotlight_turmeric_desc,
                    R.string.spotlight_turmeric_benefits,
                    R.string.spotlight_turmeric_recipes,
                    R.drawable.darkcrcl_tuermeric
                ),
                SpotlightIngredient(
                    R.string.spotlight_honey_name,
                    R.string.spotlight_honey_tagline,
                    R.string.spotlight_honey_desc,
                    R.string.spotlight_honey_benefits,
                    R.string.spotlight_honey_recipes,
                    R.drawable.honey
                ),
                SpotlightIngredient(
                    R.string.spotlight_aloe_name,
                    R.string.spotlight_aloe_tagline,
                    R.string.spotlight_aloe_desc,
                    R.string.spotlight_aloe_benefits,
                    R.string.spotlight_aloe_recipes,
                    R.drawable.aloevera
                ),
                SpotlightIngredient(
                    R.string.spotlight_coconut_name,
                    R.string.spotlight_coconut_tagline,
                    R.string.spotlight_coconut_desc,
                    R.string.spotlight_coconut_benefits,
                    R.string.spotlight_coconut_recipes,
                    R.drawable.coconutoil_hairfall_remedy
                ),
                SpotlightIngredient(
                    R.string.spotlight_rosewater_name,
                    R.string.spotlight_rosewater_tagline,
                    R.string.spotlight_rosewater_desc,
                    R.string.spotlight_rosewater_benefits,
                    R.string.spotlight_rosewater_recipes,
                    R.drawable.rosewater
                )
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(this)
        return true
    }
}
