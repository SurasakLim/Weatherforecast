package com.example.weatherforecast.di.serviceModule.module

import com.example.weatherforecast.ui.mainWeather.module.WeathContributor
import dagger.Module

@Module(includes = [
    WeathContributor::class
])
class WeatherModuleContributor