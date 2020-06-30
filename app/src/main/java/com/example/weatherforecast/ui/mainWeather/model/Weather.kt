package com.example.weatherforecast.ui.mainWeather.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    @field:SerializedName("city")
    var city: City = City(),

    @field:SerializedName("cnt")
    var cnt: Int = 0,

    @field:SerializedName("cod")
    var cod: String = "",

    @field:SerializedName("list")
    var list: List<WeatherDetial> = listOf(),

    @field:SerializedName("message")
    var message: Double = 0.0
) : Parcelable

@Parcelize
data class City(
    @field:SerializedName("coord")
    var coord: Coord = Coord(),

    @field:SerializedName("country")
    var country: String = "",

    @field:SerializedName("id")
    var id: Int = 0,

    @field:SerializedName("name")
    var name: String = ""
) : Parcelable

@Parcelize
data class WeatherDetial(

    @field:SerializedName("clouds")
    var clouds: Clouds = Clouds(),

    @field:SerializedName("dt")
    var dt: Int = 0,

    var day: String = "",

    @field:SerializedName("dt_txt")
    var dt_txt: String = "",

    @field:SerializedName("main")
    var main: Main = Main(),

    @field:SerializedName("rain")
    var rain: Rain = Rain(),

    @field:SerializedName("snow")
    var snow: Snow = Snow(),

    @field:SerializedName("sys")
    var sys: Sys = Sys(),

    @field:SerializedName("weather")
    var weatherX: List<WeatherX> = listOf(),

    @field:SerializedName("wind")
    var wind: Wind = Wind()
) : Parcelable

@Parcelize
data class Coord(
    @field:SerializedName("lat")
    var lat: Double = 0.0,

    @field:SerializedName("lon")
    var lon: Double = 0.0
) : Parcelable

@Parcelize
data class Clouds(
    @field:SerializedName("all")
    var all: Int = 0
) : Parcelable


@Parcelize
data class Main(
    @field:SerializedName("grnd_level")
    var grnd_level: Double = 0.0,

    @field:SerializedName("feels_like")
    var feels_like: Double = 0.0,

    @field:SerializedName("humidity")
    var humidity: Int = 0,

    @field:SerializedName("pressure")
    var pressure: Double = 0.0,

    @field:SerializedName("sea_level")
    var sea_level: Double = 0.0,

    @field:SerializedName("temp")
    var temp: Double = 0.0,

    @field:SerializedName("temp_kf")
    var temp_kf: Double = 0.0,

    @field:SerializedName("temp_max")
    var temp_max: Double = 0.0,

    @field:SerializedName("temp_min")
    var temp_min: Double = 0.0
) : Parcelable

@Parcelize
data class Rain(
    @field:SerializedName("3h")
    var `3h`: Double = 0.0
) : Parcelable

@Parcelize
data class Snow(
    @field:SerializedName("3h")
    var `3h`: Double = 0.0
) : Parcelable


@Parcelize
data class Sys(
    @field:SerializedName("pod")
    var pod: String = ""
) : Parcelable

@Parcelize
data class WeatherX(
    @field:SerializedName("description")
    var description: String = "",

    @field:SerializedName("icon")
    var icon: String = "",

    @field:SerializedName("id")
    var id: Int = 0,

    @field:SerializedName("main")
    var main: String = ""
) : Parcelable

@Parcelize
data class Wind(
    @field:SerializedName("deg")
    var deg: Double = 0.0,

    @field:SerializedName("speed")
    var speed: Double = 0.0
) : Parcelable