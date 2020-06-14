package com.example.weatherforecast.di.serviceModule.module

import android.content.Context
import com.example.weatherforecast.di.serviceModule.ServiceApi
import com.example.weatherforecast.di.serviceModule.WeatherApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Suppress("unused")
@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideWeathApi(context: Context) = ServiceApi.provideService(
        context,
        ServiceApi.url,
        WeatherApi::class.java
    )
}