package com.example.weatherforecast.di.serviceModule.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherforecast.di.serviceModule.annotation.ViewModelKey
import com.example.weatherforecast.di.serviceModule.factory.ViewModelFactory
import com.example.weatherforecast.ui.mainWeather.MainWeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindAppViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainWeatherViewModel::class)
    abstract fun bindWeatherViewModel(viewModel: MainWeatherViewModel): ViewModel
}