package com.kp.beautytips.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.kp.beautytips.utils.AppUtils
import com.kp.beautytips.R
import com.kp.beautytips.utils.ActivityUtils
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()
    }

    override fun attachBaseContext(newBase: Context) {
        val wrappedBase = ViewPumpContextWrapper.wrap(newBase)
        super.attachBaseContext(ActivityUtils.updateBaseContextLocale(wrappedBase))
    }

    private fun init() {
        Handler(Looper.getMainLooper()).apply {
            postDelayed({
                Intent(activity, MainActivity::class.java).also {
                    startActivity(it)
                    AppUtils.startFromRightToLeft(activity)
                }
                finish()
            }, 800)
        }
    }
}