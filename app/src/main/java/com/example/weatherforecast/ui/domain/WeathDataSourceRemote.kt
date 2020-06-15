package com.example.weatherforecast.ui.domain

import com.example.weatherforecast.di.serviceModule.WeatherApi
import javax.inject.Inject

class WeathDataSourceRemote @Inject constructor(private val apiWeather: WeatherApi):WeathDataSourceRemoteInterface.mainWeath {
    override fun onRequestWeathService(cityId: String, apiKey: String) = apiWeather.getWeatherForCityId(cityId,apiKey,"metric")

}
