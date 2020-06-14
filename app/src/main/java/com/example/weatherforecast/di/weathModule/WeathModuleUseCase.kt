package com.example.weatherforecast.di.weathModule

import com.example.weatherforecast.di.serviceModule.WeatherApi
import com.example.weatherforecast.ui.domain.WeathDataSourceRemote
import com.example.weatherforecast.ui.domain.WeathUseCase

object WeathModuleUseCase {

    fun provideWeathUseCase(dataSourceRemote: WeathDataSourceRemote): WeathUseCase {
        return WeathUseCase(dataSourceRemote)
    }
}