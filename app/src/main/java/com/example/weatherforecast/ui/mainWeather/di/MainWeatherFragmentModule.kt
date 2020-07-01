package com.example.weatherforecast.ui.mainWeather.di

import androidx.lifecycle.ViewModelProviders
import com.example.weatherforecast.ui.mainWeather.*
import dagger.Module
import dagger.Provides


@Module
class MainWeatherFragmentModule {

    @Provides
    fun provideWeatherView(mainWeatherFragment: MainWeatherFragment): WeatherPresenterContract.ViewWeather {
        return mainWeatherFragment
    }

    @Provides
    fun provideWeatherModel(fragment: MainWeatherFragment): MainWeatherViewModel {
        return ViewModelProviders.of(fragment.requireActivity())[MainWeatherViewModel::class.java]
    }

}