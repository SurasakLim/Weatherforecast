package com.example.weatherforecast.di.weathModule

import com.example.weatherforecast.di.serviceModule.WeatherApi
import com.example.weatherforecast.ui.domain.WeathDataSourceRemote

object WeathModuleDataSource {

    fun provideWeathDataSource(api: WeatherApi): WeathDataSourceRemote {
        return WeathDataSourceRemote(api)
    }
}