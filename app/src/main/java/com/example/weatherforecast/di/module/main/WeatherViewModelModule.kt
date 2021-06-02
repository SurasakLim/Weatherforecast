package com.example.weatherforecast.di.module.main

import androidx.lifecycle.ViewModel
import com.example.weatherforecast.di.serviceModule.factory.ViewModelKey
import com.example.weatherforecast.ui.screen.mainWeather.MainWeatherViewModel
import com.example.weatherforecast.ui.screen.mainWeather.MainWeatherViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.example.weatherforecast.di.scope.FragmentScope

@Module
interface WeatherViewModelModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(MainWeatherViewModel::class)
    fun mainWeatherViewModel(viewModel: MainWeatherViewModelImpl): ViewModel
}
