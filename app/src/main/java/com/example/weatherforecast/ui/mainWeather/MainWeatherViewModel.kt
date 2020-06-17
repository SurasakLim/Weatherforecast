package com.example.weatherforecast.ui.mainWeather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforecast.ui.mainWeather.model.WeathResponse
import com.example.weatherforecast.ui.mainWeather.model.Weather
import com.example.weatherforecast.ui.mainWeather.model.WeatherDetial
import com.example.weatherforecast.ui.mainWeather.model.WeatherX
import com.example.weatherforecast.uitl.StringExtenion.dateToDay
import com.example.weatherforecast.uitl.StringExtenion.celToFah
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureFahrenheit
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureCelsius
import com.example.weatherforecast.uitl.StringExtenion.fahToCal

class MainWeatherViewModel : ViewModel() {
    var weather = MutableLiveData<WeathResponse<Weather>>()
    var mainTemp = MutableLiveData<Double>()
    var tempFeelLike = MutableLiveData<Double>()
    var untiTemporalChange = MutableLiveData<Boolean>()

    init {
        mainTemp.value = 0.0
        tempFeelLike.value = 0.0
        untiTemporalChange.value = false
        weather.value = WeathResponse(false,"",Weather())
    }


    fun getCelsiusToFahrenheit(data: Double?): String = data?.celToFah().toString().fromatTemperatureFahrenheit()
    fun getFahrenheitToCelsius(data: Double?): String =
        if(untiTemporalChange.value!!) data?.fahToCal().toString().fromatTemperatureCelsius()
    else data?.toString()?.fromatTemperatureCelsius()!!

//    fun getGroupWeatherDay(): List<WeatherDetial>? {
//        val data = weather.value?.data?.list
//        data?.forEach {
//            it.dt_txt = it.dt_txt.dateToDay()!!
//        }
//        data?.distinctBy{it.dt_txt}
//        return data?.distinctBy {
//            it.dt_txt
//        }
//    }
}
