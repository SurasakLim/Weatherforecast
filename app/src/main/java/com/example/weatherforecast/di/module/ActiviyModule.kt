package com.example.weatherforecast.di.module

import com.example.weatherforecast.di.module.main.FragmentActivityModule
import com.example.weatherforecast.di.module.main.MainActivityModule
import com.example.weatherforecast.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.example.weatherforecast.di.scope.ActivityScope

@Module
interface ActiviyModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class,
            FragmentActivityModule::class
        ]
    )
    fun mainActivity(): MainActivity
}
