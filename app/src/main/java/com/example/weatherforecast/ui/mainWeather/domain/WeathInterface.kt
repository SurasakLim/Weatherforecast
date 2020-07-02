package com.example.weatherforecast.ui.mainWeather.domain

import com.example.weatherforecast.ui.mainWeather.model.WeathResponse
import com.example.weatherforecast.ui.weathOneCall.module.WeatherOneCall

interface WeathInterface {
    fun getServiceWeath(
        cityID: String,
        apiKey: String,
        callBack: WeathDataSourceRemoteInterface.RequestServiceCallback
    )

    fun getServiceWeathOneCall(
        lat: String,
        lon: String,
        apiKey: String,
        callBack: WeathDataSourceRemoteInterface.RequestWeatherOneCall
    )
}