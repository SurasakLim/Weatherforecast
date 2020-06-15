package com.example.weatherforecast.di.serviceModule.component

import android.app.Application
import com.example.weatherforecast.MainApplication
import com.example.weatherforecast.di.serviceModule.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApiModule::class,
    AppModule::class,
    ActivityModule::class,
    WeatherModuleContributor::class
    ])
interface AppComponent: AndroidInjector<MainApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }


    override fun inject(app: MainApplication)

}
