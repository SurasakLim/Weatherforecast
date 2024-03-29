package com.example.weatherforecast.ui.base

import dagger.android.AndroidInjector

interface Injectable {
    companion object {
        lateinit var realInjector: AndroidInjector<Any>
        var mockInjector: AndroidInjector<Any>? = null
    }
}
