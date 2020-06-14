package com.example.weatherforecast.di.serviceModule

import java.io.IOException

class NoNetworkException : IOException() {

    override val message: String?
        get() = super.message
}
