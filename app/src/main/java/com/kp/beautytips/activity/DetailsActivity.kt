package com.kp.beautytips.activity

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import com.kp.beautytips.R
import com.kp.beautytips.utils.ActivityUtils
import com.kp.beautytips.utils.AppUtils
import com.kp.beautytips.utils.Constants
import com.bumptech.glide.Glide
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import io.github.inflationx.viewpump.ViewPumpContextWrapper



class DetailsActivity : BaseActivity() {
    private var tabName: String = ""
    private var title: String = ""
    private var details: String = ""
    private var image: Int = 0
    private var adRequest: AdRequest? = null
    private var mInterstitialAd: InterstitialAd? = null
    private var mAdIsLoading: Boolean = false

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        init()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun init() {
        val txtTabTitle = findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtTabTitle)
        val txtTitle = findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtTitle)
        val txtDetails = findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtDetails)
        val img = findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.img)
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        val lrCopy = findViewById<View>(R.id.lrCopy)
        val lrShare = findViewById<View>(R.id.lrShare)
        val lrWhatShare = findViewById<View>(R.id.lrWhatShare)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            tabName = bundle.getString("tabName").toString()
            title = bundle.getString("title").toString()
            image = bundle.getInt("image")
            details = bundle.getString("details").toString()
            txtTabTitle.text = tabName
            txtTitle.text = title
            txtDetails.text = details

            Glide.with(activity).load(
                activity.getDrawable(
                    image
                )
            ).into(img)
        }
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        adRequest = AdRequest.Builder()
            .build()

        val adContainer = findViewById<View>(R.id.ad_view)
        val mAdView = AdView(this)
        mAdView.setAdSize(AdSize.BANNER)
        mAdView.adUnitId = getString(R.string.banner_home_footer)
        (adContainer as RelativeLayout).addView(mAdView)
        mAdView.loadAd(adRequest!!)

        loadFullScreenAds()

        lrCopy.setOnClickListener {
            copyToClipboard(details)
        }

        lrShare.setOnClickListener { shareContent() }
        lrWhatShare.setOnClickListener { shareContentOnlyWhatsapp() }
    }

    private fun copyToClipboard(copyText: String?) {
        val clipboard =
            getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = ClipData.newPlainText(getString(R.string.copy_content), copyText)
        clipboard.setPrimaryClip(clip)
        val toast = Toast.makeText(
            applicationContext,
            getString(R.string.copy_clipboard), Toast.LENGTH_SHORT
        )
        toast.show()
    }

    private fun shareContent() {
        val text =
            title + " :\n\n" + details + "\n\n" + getString(R.string.app_share) + ": http://play.google.com/store/apps/details?id=" + packageName
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share)))
    }

    private fun shareContentOnlyWhatsapp() {
        val text =
            title + " :\n\n" + details + "\n\n" + getString(R.string.app_share) + ": http://play.google.com/store/apps/details?id=" + packageName

        val whatsappIntent = Intent(Intent.ACTION_SEND)
        whatsappIntent.type = "text/plain"
        whatsappIntent.setPackage("com.whatsapp")
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, text)
        try {
            activity.startActivity(whatsappIntent)
        } catch (ex: ActivityNotFoundException) {
            val toast = Toast.makeText(
                applicationContext,
                getString(R.string.whatsapp_not_installed), Toast.LENGTH_SHORT
            )
            toast.show()
        }
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        val favoriteItem = menu?.findItem(R.id.action_favorite)
        val sharedPreferences = getSharedPreferences("beautytips_prefs", Context.MODE_PRIVATE)
        val isFav = sharedPreferences.getBoolean("fav_" + title, false)
        if (isFav) {
            favoriteItem?.setIcon(R.drawable.ic_favorite)
        } else {
            favoriteItem?.setIcon(R.drawable.ic_favorite_border)
        }
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            val sharedPreferences = getSharedPreferences("beautytips_prefs", Context.MODE_PRIVATE)
            val isFav = sharedPreferences.getBoolean("fav_" + title, false)
            val editor = sharedPreferences.edit()
            if (isFav) {
                editor.putBoolean("fav_" + title, false).apply()
                item.setIcon(R.drawable.ic_favorite_border)
                Toast.makeText(this, "Removed from Favorites", Toast.LENGTH_SHORT).show()
            } else {
                editor.putBoolean("fav_" + title, true).apply()
                item.setIcon(R.drawable.ic_favorite)
                Toast.makeText(this, "Saved to Favorites", Toast.LENGTH_SHORT).show()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        AppUtils.finishFromLeftToRight(activity)
        return true
    }

    private fun loadFullScreenAds() {
        val sharedPreferences =
            getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if (sharedPreferences.getInt(Constants.FULL_ADS_COUNT, 0) == Constants.ADS_FULL_COUNTER) {
            editor.putInt(Constants.FULL_ADS_COUNT, 0).apply()
            if (!mAdIsLoading && mInterstitialAd == null) {
                mAdIsLoading = true
                loadAd()
            }
        } else {
            var getCount = sharedPreferences.getInt(Constants.FULL_ADS_COUNT, 0)
            getCount++
            editor.putInt(Constants.FULL_ADS_COUNT, getCount).apply()
        }
    }

    private fun loadAd() {
        InterstitialAd.load(
            this, getString(R.string.interstitial_full_screen), adRequest!!,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    AppUtils.logI(adError.message)
                    mInterstitialAd = null
                    mAdIsLoading = false
                    val error = "domain: ${adError.domain}, code: ${adError.code}, " +
                            "message: ${adError.message}"
                    AppUtils.logI("onAdFailedToLoad() with error $error")
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    AppUtils.logI("Ad was loaded.")
                    mInterstitialAd = interstitialAd
                    mAdIsLoading = false
                    AppUtils.logI("onAdLoaded()")
                    showInterstitial()
                }
            }
        )
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        }
    }

    private fun showInterstitial() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    AppUtils.logI("Ad was dismissed.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mInterstitialAd = null
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    AppUtils.logI("Ad failed to show.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mInterstitialAd = null
                }

                override fun onAdShowedFullScreenContent() {
                    AppUtils.logI("Ad showed fullscreen content.")
                    // Called when ad is dismissed.
                }
            }
            mInterstitialAd?.show(this)
        }
    }
}