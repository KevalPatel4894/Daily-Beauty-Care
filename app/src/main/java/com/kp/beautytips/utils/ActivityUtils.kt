package com.kp.beautytips.utils

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import java.util.*

object ActivityUtils {
    fun updateBaseContextLocale(context: Context): Context? {
        var language: String? = AppUtils.getLanguageCode(context)
        if (language == null || language.isEmpty()) {
            language = Locale.getDefault().language
        }
        val locale = Locale(language)
        Locale.setDefault(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResourcesLocale(context, locale)
            return updateResourcesLocaleLegacy(context, locale)
        }
        AppUtils.logI("test")
        return updateResourcesLocaleLegacy(context, locale)
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResourcesLocale(context: Context, locale: Locale): Context? {
        val configuration: Configuration = context.resources.configuration
        configuration.setLocale(locale)
        AppUtils.logI("test n")
        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLocaleLegacy(context: Context, locale: Locale): Context? {
        val resources: Resources = context.resources
        val configuration: Configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }
}