package com.example.weatherforecast.ui.mainWeather

import androidx.lifecycle.LifecycleObserver
import com.example.weatherforecast.ui.mainWeather.model.Weather

interface MainWeatherContract {

    interface View:MainWeatherContract {
        fun onShowWeather(data: Weather)
        fun onErrorWeather(message:String)
    }

    interface Presenter: LifecycleObserver {
        fun onGetWeatherData(cityId:String)
    }
}