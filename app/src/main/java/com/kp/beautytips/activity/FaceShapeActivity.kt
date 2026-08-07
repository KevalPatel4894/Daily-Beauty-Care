package com.kp.beautytips.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton
import com.kp.beautytips.R
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AdManager
import com.kp.beautytips.utils.AppUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class FaceShapeActivity : BaseActivity() {

    private lateinit var rgWidestPart: RadioGroup
    private lateinit var rgJawShape: RadioGroup
    private lateinit var rgFaceLength: RadioGroup
    private lateinit var cardFaceResult: View
    private lateinit var txtResultTitle: TextView
    private lateinit var txtResultDesc: TextView
    private lateinit var txtResultContour: TextView
    private lateinit var txtResultBrows: TextView
    private lateinit var txtResultHair: TextView

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_face_shape)
        initViews()
    }

    private fun initViews() {
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.setNavigationOnClickListener { onBackPressed() }

        rgWidestPart = findViewById(R.id.rgWidestPart)
        rgJawShape = findViewById(R.id.rgJawShape)
        rgFaceLength = findViewById(R.id.rgFaceLength)
        cardFaceResult = findViewById(R.id.cardFaceResult)
        txtResultTitle = findViewById(R.id.txtResultTitle)
        txtResultDesc = findViewById(R.id.txtResultDesc)
        txtResultContour = findViewById(R.id.txtResultContour)
        txtResultBrows = findViewById(R.id.txtResultBrows)
        txtResultHair = findViewById(R.id.txtResultHair)

        val btnCalculate = findViewById<MaterialButton>(R.id.btnCalculateFaceShape)
        btnCalculate.setOnClickListener {
            calculateFaceShape()
        }

        val adContainer = findViewById<ViewGroup>(R.id.ad_view)
        AdManager.loadBanner(this, adContainer, getString(R.string.banner_home_footer))
    }

    private fun calculateFaceShape() {
        val widestId = rgWidestPart.checkedRadioButtonId
        val jawId = rgJawShape.checkedRadioButtonId
        val lengthId = rgFaceLength.checkedRadioButtonId

        when {
            // Heart shape: Forehead widest + Pointed jaw
            widestId == R.id.rbForehead && jawId == R.id.rbJawPointed -> {
                displayResult(
                    titleRes = R.string.shape_heart_title,
                    descRes = R.string.shape_heart_desc,
                    contourRes = R.string.shape_heart_contour,
                    browsRes = R.string.shape_heart_brows,
                    hairRes = R.string.shape_heart_hair
                )
            }
            // Square shape: Equal width or Jaw widest + Square jaw
            (widestId == R.id.rbEqualWidth || widestId == R.id.rbJaw) && jawId == R.id.rbJawSquare -> {
                displayResult(
                    titleRes = R.string.shape_square_title,
                    descRes = R.string.shape_square_desc,
                    contourRes = R.string.shape_square_contour,
                    browsRes = R.string.shape_square_brows,
                    hairRes = R.string.shape_square_hair
                )
            }
            // Round shape: Equal length and width + Round jaw
            lengthId == R.id.rbLengthEqual && jawId == R.id.rbJawRound -> {
                displayResult(
                    titleRes = R.string.shape_round_title,
                    descRes = R.string.shape_round_desc,
                    contourRes = R.string.shape_round_contour,
                    browsRes = R.string.shape_round_brows,
                    hairRes = R.string.shape_round_hair
                )
            }
            // Oval shape: Default balanced
            else -> {
                displayResult(
                    titleRes = R.string.shape_oval_title,
                    descRes = R.string.shape_oval_desc,
                    contourRes = R.string.shape_oval_contour,
                    browsRes = R.string.shape_oval_brows,
                    hairRes = R.string.shape_oval_hair
                )
            }
        }
    }

    private fun displayResult(
        titleRes: Int,
        descRes: Int,
        contourRes: Int,
        browsRes: Int,
        hairRes: Int
    ) {
        cardFaceResult.visibility = View.VISIBLE
        txtResultTitle.text = getString(titleRes)
        txtResultDesc.text = getString(descRes)
        txtResultContour.text = getString(contourRes)
        txtResultBrows.text = getString(browsRes)
        txtResultHair.text = getString(hairRes)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(this)
        return true
    }
}
