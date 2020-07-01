package com.example.weatherforecast.ui.mainWeather.di

import androidx.lifecycle.ViewModelProviders
import com.example.weatherforecast.ui.mainWeather.domain.WeathDataSourceRemote
import com.example.weatherforecast.ui.mainWeather.domain.WeathUseCase
import com.example.weatherforecast.ui.mainWeather.*
import dagger.Module
import dagger.Provides

@Module
class WeatherFragmentModule {

    @Provides
    fun provideWeatherView(weatherFragment: WearterDetailFragment): MainWeatherContract.View {
        return weatherFragment
    }

    @Provides
    fun provideWeatherPresenter(weatherPresenter: MainWeatherPresenter): MainWeatherContract.Presenter {
        return weatherPresenter
    }

    @Provides
    fun provideWeatherUseCase(dataSourceRemote: WeathDataSourceRemote):WeathUseCase {
        return WeathUseCase(dataSourceRemote)
    }

    @Provides
    fun provideWeatherModel(fragment: WearterDetailFragment): MainWeatherViewModel {
        return ViewModelProviders.of(fragment.requireActivity())[MainWeatherViewModel::class.java]
    }
}