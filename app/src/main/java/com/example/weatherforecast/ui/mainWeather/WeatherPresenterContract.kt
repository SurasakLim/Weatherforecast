package com.example.weatherforecast.ui.mainWeather

import androidx.lifecycle.LifecycleObserver
import com.example.weatherforecast.ui.mainWeather.model.Weather
import com.example.weatherforecast.ui.mainWeather.model.WeatherDetial
import com.example.weatherforecast.ui.mainWeather.model.WeatherList

interface WeatherPresenterContract {

    interface ViewWeather:WeatherPresenterContract{
        fun onSetDataAdapter(dataExan: ArrayList<WeatherList>)
    }

    interface Presenter: LifecycleObserver {
        fun onGroupSetData(weath:Weather)
    }
}