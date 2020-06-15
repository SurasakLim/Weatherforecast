package com.example.weatherforecast.uitl

import java.io.IOException

class NoNetworkException : IOException() {

    override val message: String? = "No connectivity exception"
}
