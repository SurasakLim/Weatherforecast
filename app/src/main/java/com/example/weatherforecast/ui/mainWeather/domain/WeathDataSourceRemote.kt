package com.example.weatherforecast.ui.mainWeather.domain

import com.example.weatherforecast.di.serviceModule.WeatherApi
import com.example.weatherforecast.ui.weathOneCall.module.WeatherOneCall
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class WeathDataSourceRemote @Inject constructor(private val apiWeather: WeatherApi) :
    WeathDataSourceRemoteInterface.mainWeath {
    override fun onRequestWeathService(cityId: String, apiKey: String) =
        apiWeather.getWeatherForCityId(cityId, apiKey, "metric")

    override fun onRequestWeatherOneCall(
        lat: String,
        lon: String,
        apiKey: String
    ) = apiWeather.getWeatherOneCall(lat, lon, apiKey, "metric")

}
