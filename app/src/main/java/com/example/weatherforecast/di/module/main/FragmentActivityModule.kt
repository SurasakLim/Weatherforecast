package com.example.weatherforecast.di.module.main

import com.example.weatherforecast.di.scope.FragmentScope
import com.example.weatherforecast.ui.screen.mainWeather.WearterMainFragment
import com.example.weatherforecast.ui.screen.weathercurrent.WeatherCurrentFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [WeatherViewModelModule::class])
    fun mainWeatherFragment(): WearterMainFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [WeatherCurrentViewModelModule::class])
    fun wearterDetailFragment(): WeatherCurrentFragment
}
