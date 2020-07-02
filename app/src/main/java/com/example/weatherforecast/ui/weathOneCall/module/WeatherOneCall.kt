package com.example.weatherforecast.ui.weathOneCall.module

data class WeatherOneCall(
    val current: Current = Current(),
    val daily: List<Daily> = listOf(),
    val hourly: List<Hourly> = listOf(),
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val minutely: List<Minutely> = listOf(),
    val timezone: String = "",
    val timezone_offset: Int = 0
)

data class Current(
    val clouds: Int = 0,
    val dew_point: Double = 0.0,
    val dt: Int = 0,
    val feels_like: Double = 0.0,
    val humidity: Int = 0,
    val pressure: Int = 0,
    val sunrise: Int = 0,
    val sunset: Int = 0,
    val temp: Double = 0.0,
    val uvi: Double = 0.0,
    val visibility: Int = 0,
    val weather: List<Weather> = listOf(),
    val wind_deg: Int = 0,
    val wind_speed: Double = 0.0
)

data class Daily(
    val clouds: Int = 0,
    val dew_point: Double = 0.0,
    val dt: Int = 0,
    val feels_like: FeelsLike,
    val humidity: Int = 0,
    val pressure: Int = 0,
    val rain: Double = 0.0,
    val sunrise: Int = 0,
    val sunset: Int = 0,
    val temp: Temp,
    val uvi: Double = 0.0,
    val weather: List<WeatherDetailDaily>,
    val wind_deg: Int = 0,
    val wind_speed: Int = 0
)

data class Hourly(
    val clouds: Int = 0,
    val dew_point: Double = 0.0,
    val dt: Int = 0,
    val feels_like: Double = 0.0,
    val humidity: Int = 0,
    val pressure: Int = 0,
    val rain: Rain,
    val temp: Double = 0.0,
    val weather: List<WeatherDetail> = listOf(),
    val wind_deg: Int = 0,
    val wind_speed: Double = 0.0
)

data class Minutely(
    val dt: Int = 0,
    val precipitation: Int = 0
)

data class Weather(
    val description: String = "",
    val icon: String = "",
    val id: Int = 0,
    val main: String = ""
)

data class FeelsLike(
    val day: Double = 0.0,
    val eve: Double = 0.0,
    val morn: Double = 0.0,
    val night: Double = 0.0
)

data class Temp(
    val day: Double = 0.0,
    val eve: Double = 0.0,
    val max: Double = 0.0,
    val min: Double = 0.0,
    val morn: Double = 0.0,
    val night: Double = 0.0
)

data class WeatherDetailDaily(
    val description: String = "",
    val icon: String = "",
    val id: Int = 0,
    val main: String = ""
)

data class Rain(
    val `1h`: Double = 0.0
)

data class WeatherDetail(
    val description: String = "",
    val icon: String = "",
    val id: Int = 0,
    val main: String = ""
)