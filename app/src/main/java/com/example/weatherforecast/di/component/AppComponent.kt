package com.example.weatherforecast.di.component

import android.app.Application
import com.example.weatherforecast.MainApplication
import com.example.weatherforecast.di.module.ActiviyModule
import com.example.weatherforecast.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import com.example.weatherforecast.di.scope.ApplicationScope

@ApplicationScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActiviyModule::class,
    ]
)
interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}
