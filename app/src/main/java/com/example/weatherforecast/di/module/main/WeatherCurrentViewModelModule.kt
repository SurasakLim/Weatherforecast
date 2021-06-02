package com.example.weatherforecast.di.module.main

import androidx.lifecycle.ViewModel
import com.example.weatherforecast.di.scope.FragmentScope
import com.example.weatherforecast.di.serviceModule.factory.ViewModelKey
import com.example.weatherforecast.ui.screen.weathercurrent.WeatherCurrentViewModel
import com.example.weatherforecast.ui.screen.weathercurrent.WeatherCurrentViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WeatherCurrentViewModelModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKey(WeatherCurrentViewModel::class)
    fun weatherCurrentViewModel(viewModel: WeatherCurrentViewModelImpl): ViewModel
}
