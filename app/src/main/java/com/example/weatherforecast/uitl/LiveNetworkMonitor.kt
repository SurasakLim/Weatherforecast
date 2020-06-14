package com.example.weatherforecast.uitl

import android.content.Context
import android.net.ConnectivityManager
import android.telephony.TelephonyManager

class LiveNetworkMonitor(private val context: Context?) : LiveMonitorInterface {

    override fun isMobileConnected(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return if (activeNetwork != null && activeNetwork.isConnectedOrConnecting) {
            // connected to the internet
            return when (activeNetwork.type) {
                ConnectivityManager.TYPE_MOBILE -> true
                else -> false
            }
        } else {
            false // not connected to the internet
        }
    }

    override fun isWifiConnected(): Boolean {

        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return if (activeNetwork != null && activeNetwork.isConnectedOrConnecting) {
            // connected to the internet
            return when (activeNetwork.type) {
                ConnectivityManager.TYPE_WIFI -> true
                else -> false
            }
        } else {
            false // not connected to the internet
        }
    }

    override fun isConnected(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    override fun getNetworkType(): String {
        if (isConnected()) {
            val teleMan = context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val connManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkType = teleMan.networkType
            val networkInfo = connManager.activeNetworkInfo
            when (networkType) {
                TelephonyManager.NETWORK_TYPE_GPRS -> return TYPE_GPRS
                TelephonyManager.NETWORK_TYPE_EDGE -> return TYPE_EDGE
                TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_1xRTT,
                TelephonyManager.NETWORK_TYPE_IDEN -> return TYPE_2G
                TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0,
                TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_HSDPA,
                TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA,
                TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD,
                TelephonyManager.NETWORK_TYPE_HSPAP -> return TYPE_3G
                TelephonyManager.NETWORK_TYPE_LTE -> return TYPE_4G
            }
            if (networkInfo.type == ConnectivityManager.TYPE_WIFI) return TYPE_WIFI
        }

        return TYPE_NETWORK_NOT_CONNECT
    }

    companion object {
        const val TYPE_GPRS = "GPRS"
        const val TYPE_EDGE = "EDGE"
        const val TYPE_2G = "2G"
        const val TYPE_3G = "3G"
        const val TYPE_4G = "4G"
        const val TYPE_WIFI = "WIFI"
        const val TYPE_NETWORK_NOT_CONNECT = "Network is not connect"
    }

}

