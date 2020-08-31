package com.jeffrey.plashdom.service

import android.content.Context
import android.net.ConnectivityManager

object InternetConnectionService {
    fun checkConnection(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connMgr.activeNetworkInfo
        if (activeNetworkInfo != null) {
            return if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                true
            } else activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
        }
        return false
    }
}