package com.example.weatherforecast.ui.screen.mainWeather.model

import android.os.Parcelable
import com.example.weatherapi.api.weatherapi.response.WeatherDetialResponse
import com.example.weatherapi.api.weatherapi.response.WeatherResponse
import com.example.weatherforecast.uitl.StringExtenion.celToFah
import com.example.weatherforecast.uitl.StringExtenion.dateToDay
import com.example.weatherforecast.uitl.StringExtenion.fahToCal
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureCelsius
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureFahrenheit
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    var tempType: String = "C",
    val cityName: String? = null,
    var tempMain: Double? = 0.0,
    var tempFormate: String? = "",
    var list: List<WeatherDetial> = listOf()
) : Parcelable

@Parcelize
data class WeatherDetial(
    var cloudsAll: Int,
    var day: String = "",
    var dtTxt: String = "",
    var dtFromat: String = "",
    val grndLevel: Double = 0.0,
    val feelsLike: Double = 0.0,
    val humidity: Int = 0,
    var pressure: Double = 0.0,
    var seaLevel: Double = 0.0,
    var temp: Double = 0.0,
    var tempKf: Double = 0.0,
    var tempMax: Double = 0.0,
    var tempMin: Double = 0.0,
    var tempMaxFormate: String = "",
    var tempMinFormate: String = "",
    var weatherX: List<WeatherX> = listOf(),
    val rain: Double = 0.0,
    val cloud: Int = 0,
    val snow: Double = 0.0,
    val windDeg: Double = 0.0,
    val windSpeed: Double = 0.0,
) : Parcelable

@Parcelize
data class WeatherX(
    var description: String = "",
    var icon: String = "",
    var id: Int = 0,
    var main: String = ""
) : Parcelable

fun WeatherResponse.toWeather(): Weather {
    return Weather(
        cityName = city.name,
        tempMain = list.firstOrNull()?.main?.temp,
        tempFormate = list.firstOrNull()?.main?.temp.toString().fromatTemperatureCelsius(),
        list = this.list.toWeatherDetailList()
    )
}

fun List<WeatherDetialResponse>.toWeatherDetailList(): List<WeatherDetial> {
    return map { it.toWeatherDetail() }
}

fun List<WeatherDetial>.toWeatherDetailTempF() {
    map {
        it.tempMax = it.tempMax.celToFah()
        it.tempMin = it.tempMin.celToFah()
        it.tempMaxFormate = it.tempMax.toString().fromatTemperatureFahrenheit()
        it.tempMinFormate = it.tempMin.toString().fromatTemperatureFahrenheit()
    }
}

fun List<WeatherDetial>.toWeatherDetailTempC() {
    map {
        it.tempMax = it.tempMax.fahToCal()
        it.tempMin = it.tempMin.fahToCal()
        it.tempMaxFormate = it.tempMax.toString().fromatTemperatureCelsius()
        it.tempMinFormate = it.tempMin.toString().fromatTemperatureCelsius()
    }
}

fun WeatherDetialResponse.toWeatherDetail(): WeatherDetial {
    return WeatherDetial(
        cloudsAll = clouds.all,
        day = day,
        dtFromat = dt_txt.dateToDay() ?: "",
        dtTxt = dt_txt,
        grndLevel = main.grnd_level,
        feelsLike = main.feels_like,
        humidity = main.humidity,
        pressure = main.pressure,
        seaLevel = main.sea_level,
        temp = main.temp,
        tempKf = main.temp_kf,
        tempMax = main.temp_max,
        tempMin = main.temp_min,
        weatherX = weatherX.map {
            WeatherX(
                description = it.description,
                icon = it.icon,
                main = it.main
            )
        },
        rain = rain.value,
        cloud = clouds.all,
        snow = snow.value,
        windDeg = wind.deg,
        windSpeed = wind.speed
    )
}

fun List<WeatherDetial>.mapGroupWeatherDay(currentDateTime: String): List<WeatherDetial> {
    return filter {
        it.dtFromat == currentDateTime
    }
}
