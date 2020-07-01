package com.example.weatherforecast.ui.mainWeather.model

data class WeathResponse<T>(
    var success:Boolean = false,
    var errorMessage:String = "",
    var data: T
)