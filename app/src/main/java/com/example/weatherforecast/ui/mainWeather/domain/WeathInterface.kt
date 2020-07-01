package com.example.weatherforecast.ui.mainWeather.domain

interface WeathInterface {
    fun getServiceWeath(cityID:String,apiKey:String, callBack: WeathDataSourceRemoteInterface.RequestServiceCallback)
}