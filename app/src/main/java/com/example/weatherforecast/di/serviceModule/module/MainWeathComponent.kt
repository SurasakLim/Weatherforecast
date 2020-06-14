package com.example.weatherforecast.di.serviceModule.module

import com.example.weatherforecast.MainActivity
import com.example.weatherforecast.ui.mainWeather.MainWeatherFragment
import dagger.Component
import dagger.Subcomponent

@Subcomponent
interface MainWeathComponent{

    @Subcomponent.Factory
    interface Factory{
        fun create(): MainWeathComponent
    }

    fun inject(activiyWeather:MainActivity)
    fun inject(fragementWeath:MainWeatherFragment)
}