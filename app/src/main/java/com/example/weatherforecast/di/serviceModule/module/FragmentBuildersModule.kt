package com.example.weatherforecast.di.serviceModule.module

import com.example.weatherforecast.ui.mainWeather.MainWeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainWeatherFragment(): MainWeatherFragment

}