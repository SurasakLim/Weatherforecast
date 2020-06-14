package com.example.weatherforecast.ui.mainWeather.model

import androidx.lifecycle.LiveData

data class WeathResponse<T>(
    var success:Boolean = false,
    var errorMessage:String = "",
    var data: T
)