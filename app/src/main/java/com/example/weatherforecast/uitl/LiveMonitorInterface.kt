package com.example.weatherforecast.uitl

interface LiveMonitorInterface{
    fun isConnected(): Boolean
    fun isWifiConnected(): Boolean
    fun isMobileConnected(): Boolean
    fun getNetworkType(): String
}