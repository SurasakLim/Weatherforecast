package com.example.weatherforecast.ui.mainWeather.module

import com.example.weatherforecast.ui.mainWeather.WearterDetailFragment
import com.example.weatherforecast.ui.mainWeather.di.WeatherDataSourceModule
import com.example.weatherforecast.ui.mainWeather.di.WeatherFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WeathContributor {

    @ContributesAndroidInjector(modules = [WeatherFragmentModule::class,WeatherDataSourceModule::class])
    abstract fun wearterDetailFragmentContribute(): WearterDetailFragment
}