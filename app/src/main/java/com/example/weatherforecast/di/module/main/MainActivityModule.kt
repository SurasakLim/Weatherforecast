package com.example.weatherforecast.di.module.main

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.weatherapi.NetworkModule
import com.example.weatherapi.api.weatherapi.WeatherDateSourceModule
import com.example.weatherforecast.di.serviceModule.factory.ViewModelKey
import com.example.weatherforecast.ui.MainActivity
import com.example.weatherforecast.ui.MainActivityViewModel
import com.example.weatherforecast.ui.MainActivityViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.example.weatherforecast.di.scope.ActivityScope

@Module(
    includes = [
        NetworkModule::class,
        WeatherDateSourceModule::class
    ]
)
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ActivityScope
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun mainActivityViewModel(viewModel: MainActivityViewModelImpl): ViewModel

    @Binds
    @ActivityScope
    internal abstract fun activity(mainActivity: MainActivity): Activity
}
