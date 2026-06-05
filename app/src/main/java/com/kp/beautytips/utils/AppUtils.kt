package com.kp.beautytips.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import com.kp.beautytips.R

object AppUtils {
    private const val APP_TAG = "BeautyTips"

    @JvmStatic
    fun startFromRightToLeft(context: Context) {
        (context as Activity).overridePendingTransition(R.anim.trans_left_in, R.anim.no_animation)
    }

    @JvmStatic
    fun finishFromLeftToRight(context: Context) {
        (context as Activity).overridePendingTransition(
            R.anim.trans_right_in,
            R.anim.trans_right_out
        )
    }

    fun logI(message: String) {
        Log.i(APP_TAG, message)
    }

    fun setLanguageCode(context: Context, strLanguageCode: String) {
        val sharedPrefs = context.getSharedPreferences(
            Constants.PREF_NAME, Context.MODE_PRIVATE
        )
        val editor = sharedPrefs.edit()
        editor.putString(Constants.PREF_LANGUAGE_CODE, strLanguageCode)
        editor.commit()
    }

    fun getLanguageCode(context: Context): String {
        val sharedPrefs = context.getSharedPreferences(
            Constants.PREF_NAME, Context.MODE_PRIVATE
        )
        return sharedPrefs.getString(Constants.PREF_LANGUAGE_CODE, "en")!!
    }

}