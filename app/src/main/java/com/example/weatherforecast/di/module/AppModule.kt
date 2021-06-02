package com.example.weatherforecast.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import th.co.the1.the1app.common.lib.schedulers.RxSchedulerProvider
import th.co.the1.the1app.common.lib.schedulers.RxSchedulerProviderImpl
import com.example.weatherforecast.di.scope.ApplicationScope

@Module
abstract class AppModule {

    @Binds
    @ApplicationScope
    internal abstract fun applicationContext(application: Application): Context

    companion object {

        @Provides
        @ApplicationScope
        fun rxSchedulerProvider(): RxSchedulerProvider = RxSchedulerProviderImpl()
    }
}
