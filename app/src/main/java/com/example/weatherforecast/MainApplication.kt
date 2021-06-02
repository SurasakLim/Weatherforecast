package com.example.weatherforecast

import com.example.weatherforecast.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import java.io.IOException
import java.net.SocketException

class MainApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        registerRxGlobalErrorHandler()

        return DaggerAppComponent
            .factory()
            .create(this)
    }

    private fun registerRxGlobalErrorHandler() {
        RxJavaPlugins.setErrorHandler {
            val e = if (it is UndeliverableException) it.cause else it

            when (e) {
                is IOException, is SocketException -> {
                    // fine, irrelevant network problem or API that throws on cancellation
                    return@setErrorHandler
                }
                is InterruptedException -> {
                    // fine, some blocking code was interrupted by a dispose call
                    return@setErrorHandler
                }
                is NullPointerException, is IllegalArgumentException -> {
                    // that's likely a bug in the application
                    Thread.currentThread()
                        .uncaughtExceptionHandler?.uncaughtException(Thread.currentThread(), e)
                    return@setErrorHandler
                }
                is IllegalStateException -> {
                    // that's a bug in RxJava or in a custom operator
                    Thread.currentThread()
                        .uncaughtExceptionHandler?.uncaughtException(Thread.currentThread(), e)
                    return@setErrorHandler
                }
            }
        }
    }
}
