package com.jeffrey.plashdom.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.ColorRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat

object Utils {
    @JvmStatic
    fun openCustomTab(context: Context?, url: String?, @ColorRes colorResId: Int) {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ContextCompat.getColor(context!!, colorResId))
        builder.setShowTitle(true)
        builder.addDefaultShareMenuItem()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

    fun startActivity(context: Context, cls: Class<*>?) {
        val i = Intent(context, cls)
        context.startActivity(i)
    }
}