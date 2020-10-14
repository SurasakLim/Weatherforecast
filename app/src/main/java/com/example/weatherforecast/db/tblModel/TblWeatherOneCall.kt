package com.example.weatherforecast.db.tblModel

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*


@Entity(tableName = "tblweatheronecall")
data class TblWeatherOneCall(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),

    @SerializedName("current")
    @TypeConverters(ConverterTypeObject::class)
    var current: Current,

    @SerializedName("daily")
    @TypeConverters(ConvertresDalyList::class)
    var daily: List<Daily> = listOf(),

    @SerializedName("hourly")
    @TypeConverters(ConvertresHourlyList::class)
    var hourly: List<Hourly> = listOf(),

    @SerializedName("minutely")
    @TypeConverters(ConvertreMinutelyList::class)
    val minutely: List<Minutely> = listOf(),

    @SerializedName("lat")
    var lat: Double = 0.0,

    @SerializedName("lon")
    var lon: Double = 0.0,

    @SerializedName("timezone")
    var timezone: String = "",

    @SerializedName("timezone_offset")
    var timezone_offset: Int = 0
)

@Entity(tableName = "current")
data class Current(
    var id_tblonecall: String,

    @PrimaryKey
    var currentId: String = UUID.randomUUID().toString(),

    @SerializedName("clouds")
    var clouds: Int = 0,

    @SerializedName("dew_point")
    var dew_point: Double = 0.0,

    @SerializedName("dt")
    var dt: Int = 0,

    @SerializedName("feels_like")
    var feels_like: Double = 0.0,

    @SerializedName("humidity")
    var humidity: Int = 0,

    @SerializedName("pressure")
    var pressure: Int = 0,

    @SerializedName("sunrise")
    var sunrise: Int = 0,

    @SerializedName("sunset")
    var sunset: Int = 0,

    @SerializedName("temp")
    var temp: Double = 0.0,

    @SerializedName("uvi")
    var uvi: Double = 0.0,

    @SerializedName("visibility")
    var visibility: Int = 0,

    @SerializedName("weather")
    @TypeConverters(ConvertresWeatherList::class)
    var weather: List<Weather> = listOf(),

    @SerializedName("wind_deg")
    var wind_deg: Int = 0,

    @SerializedName("wind_speed")
    var wind_speed: Double = 0.0
)

@Entity(tableName = "weather")
data class Weather(
    var id_current: String,

    @PrimaryKey
    var WeatherId: String = UUID.randomUUID().toString(),

    @SerializedName("description")
    var description: String = "",

    @SerializedName("icon")
    var icon: String = "",

    @SerializedName("id")
    var idWeather: Int = 0,

    @SerializedName("main")
    var main: String = ""
)









