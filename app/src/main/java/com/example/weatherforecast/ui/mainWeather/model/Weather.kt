package com.example.weatherforecast.ui.mainWeather.model

import com.google.gson.annotations.SerializedName

data class Weather(
    @field:SerializedName("city")
    val city: City = City(),

    @field:SerializedName("cnt")
    val cnt: Int = 0,

    @field:SerializedName("cod")
    val cod: String = "",

    @field:SerializedName("list")
    val list: List<WeatherDetial> = listOf(),

    @field:SerializedName("message")
    val message: Double = 0.0
)

data class City(
    @field:SerializedName("coord")
    val coord: Coord = Coord(),

    @field:SerializedName("country")
    val country: String = "",

    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("name")
    val name: String = ""
)

data class WeatherDetial(

    @field:SerializedName("clouds")
    val clouds: Clouds = Clouds(),

    @field:SerializedName("dt")
    val dt: Int = 0,

    @field:SerializedName("dt_txt")
    val dt_txt: String = "",

    @field:SerializedName("main")
    val main: Main,

    @field:SerializedName("rain")
    val rain: Rain,

    @field:SerializedName("snow")
    val snow: Snow,

    @field:SerializedName("sys")
    val sys: Sys,

    @field:SerializedName("weather")
    val weatherX: List<WeatherX>,

    @field:SerializedName("wind")
    val wind: Wind
)

data class Coord(
    @field:SerializedName("lat")
    val lat: Double = 0.0,

    @field:SerializedName("lon")
    val lon: Double = 0.0
)

data class Clouds(
    @field:SerializedName("all")
    val all: Int = 0
)

data class Main(
    @field:SerializedName("grnd_level")
    val grnd_level: Double = 0.0,

    @field:SerializedName("humidity")
    val humidity: Int = 0,

    @field:SerializedName("pressure")
    val pressure: Double = 0.0,

    @field:SerializedName("sea_level")
    val sea_level: Double = 0.0,

    @field:SerializedName("temp")
    val temp: Double = 0.0,

    @field:SerializedName("temp_kf")
    val temp_kf: Double = 0.0,

    @field:SerializedName("temp_max")
    val temp_max: Double = 0.0,

    @field:SerializedName("temp_min")
    val temp_min: Double = 0.0
)

data class Rain(
    @field:SerializedName("3h")
    val `3h`: Double = 0.0
)

data class Snow(
    @field:SerializedName("3h")
    val `3h`: Double = 0.0
)

data class Sys(
    @field:SerializedName("pod")
    val pod: String = ""
)

data class WeatherX(
    @field:SerializedName("description")
    val description: String = "",

    @field:SerializedName("icon")
    val icon: String = "",

    @field:SerializedName("id")
    val id: Int = 0,

    @field:SerializedName("main")
    val main: String = ""
)

data class Wind(
    @field:SerializedName("deg")
    val deg: Double = 0.0,

    @field:SerializedName("speed")
    val speed: Double = 0.0
)