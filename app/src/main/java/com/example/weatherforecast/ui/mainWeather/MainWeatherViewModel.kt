package com.example.weatherforecast.ui.mainWeather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforecast.ui.mainWeather.model.WeathResponse
import com.example.weatherforecast.ui.mainWeather.model.Weather

class MainWeatherViewModel : ViewModel() {
    var weather = MutableLiveData<WeathResponse<Weather>>()

    init {
        weather.value = WeathResponse(false,"",Weather())
    }
}
