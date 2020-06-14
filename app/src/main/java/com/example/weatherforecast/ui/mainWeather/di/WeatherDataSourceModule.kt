package com.example.weatherforecast.ui.mainWeather.di

import com.example.weatherforecast.ui.domain.WeathDataSourceRemoteInterface
import com.example.weatherforecast.ui.domain.WeathRepository
import dagger.Module
import dagger.Provides

@Module
class WeatherDataSourceModule {

    @Provides
    fun provideWeathDataSouce(dataSource: WeathDataSourceRemoteInterface) : WeathDataSourceRemoteInterface {
        return WeathRepository(dataSource)
    }
}