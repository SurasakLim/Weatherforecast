package com.example.weatherforecast.ui.mainWeather.di

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import com.example.weatherforecast.ui.domain.WeathDataSourceRemote
import com.example.weatherforecast.ui.domain.WeathUseCase
import com.example.weatherforecast.ui.mainWeather.MainWeatherContract
import com.example.weatherforecast.ui.mainWeather.MainWeatherFragment
import com.example.weatherforecast.ui.mainWeather.MainWeatherPresenter
import com.example.weatherforecast.ui.mainWeather.MainWeatherViewModel
import dagger.Module
import dagger.Provides

@Module
class WeatherFragmentModule {

    @Provides
    fun provideWeatherView(weatherFragment: MainWeatherFragment): MainWeatherContract.View {
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
    fun provideWeatherModel(fragment: MainWeatherFragment): MainWeatherViewModel {
        return ViewModelProviders.of(fragment.requireActivity())[MainWeatherViewModel::class.java]
    }
}