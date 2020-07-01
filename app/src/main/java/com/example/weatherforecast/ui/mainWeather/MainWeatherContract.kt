package com.example.weatherforecast.ui.mainWeather

import androidx.lifecycle.LifecycleObserver
import com.example.weatherforecast.ui.mainWeather.model.Weather
import com.example.weatherforecast.ui.mainWeather.model.WeatherDetial

interface MainWeatherContract {

    interface View:MainWeatherContract {
        fun onShowWeather(data: Weather)
        fun onErrorWeather(message:String)
        fun onLoading()
        fun onLoaded()
    }

    interface Presenter: LifecycleObserver {
        fun onGetWeatherData(cityId:String)
    }

}