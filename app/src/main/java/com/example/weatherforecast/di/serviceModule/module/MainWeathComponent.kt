package com.example.weatherforecast.di.serviceModule.module

import com.example.weatherforecast.MainActivity
import com.example.weatherforecast.ui.mainWeather.MainWeatherFragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Subcomponent
interface MainWeathComponent : AndroidInjector<MainActivity>{

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainActivity>{
    }

}

@Module(subcomponents = [MainWeathComponent::class])
internal abstract class InjectorModule {
    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    abstract fun bind(factory: MainWeathComponent.Factory?): AndroidInjector.Factory<*>?
}