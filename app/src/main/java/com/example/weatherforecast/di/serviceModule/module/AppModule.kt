package com.example.weatherforecast.di.serviceModule.module

import android.app.Application
import com.example.weatherforecast.uitl.AppExecutors
import com.example.weatherforecast.uitl.LiveNetworkMonitor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: Application) = app.applicationContext

//    @Singleton
//    @Provides
//    fun provideDataBase(app: Application) = WeatherDataBase.getInstance(app)

    @Singleton
    @Provides
    fun provideNetworkMonitor(app: Application): LiveNetworkMonitor = LiveNetworkMonitor(app)

    @Singleton
    @Provides
    fun provideAppExecitors() = AppExecutors()
}