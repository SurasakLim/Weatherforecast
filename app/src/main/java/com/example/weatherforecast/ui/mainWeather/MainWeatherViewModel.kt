package com.example.weatherforecast.ui.mainWeather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforecast.ui.mainWeather.model.WeathResponse
import com.example.weatherforecast.ui.mainWeather.model.Weather
import com.example.weatherforecast.ui.mainWeather.model.WeatherDetial
import com.example.weatherforecast.ui.mainWeather.model.WeatherX
import com.example.weatherforecast.uitl.StringExtenion.dateToDay

class MainWeatherViewModel : ViewModel() {
    var weather = MutableLiveData<WeathResponse<Weather>>()

    init {
        weather.value = WeathResponse(false,"",Weather())
    }

    fun getCurrentWeather() = weather.value?.data?.list?.get(0)

    fun getWeatherDetail() = weather.value?.data?.list?.get(0)?.weatherX

    fun getGroupWeatherDay(): List<WeatherDetial>? {
        val data = weather.value?.data?.list
        data?.forEach {
            it.dt_txt = it.dt_txt.dateToDay()!!
        }
        data?.distinctBy{it.dt_txt}
        return data?.distinctBy {
            it.dt_txt
        }
    }
}
