package com.example.weatherforecast.ui.domain

import com.example.weatherforecast.ui.mainWeather.model.WeathResponse
import com.example.weatherforecast.ui.mainWeather.model.Weather
import io.reactivex.Observable
import retrofit2.Response

interface WeathInterface {
    fun getServiceWeath(cityID:String,apiKey:String, callBack: WeathDataSourceRemoteInterface.RequestServiceCallback)
}