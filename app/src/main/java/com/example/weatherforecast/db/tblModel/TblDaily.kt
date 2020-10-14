package com.example.weatherforecast.db.tblModel

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "daily")
data class Daily(
    @ColumnInfo(name = "id_tblonecall")
    var id_tblonecall: String,

    @PrimaryKey
    @ColumnInfo(name = "dailyId")
    var id: String = UUID.randomUUID().toString(),

    @SerializedName("clouds")
    @ColumnInfo(name = "clouds")
    var clouds: Int = 0,

    @SerializedName("dew_point")
    @ColumnInfo(name = "dewpoint")
    var dew_point: Double = 0.0,

    @SerializedName("dt")
    @ColumnInfo(name = "dt")
    var dt: Int = 0,

    @SerializedName("feels_like")
    @TypeConverters(ConverterFeelsLikeObject::class)
    var feels_like: FeelsLike,

    @SerializedName("humidity")
    @ColumnInfo(name = "humidity")
    var humidity: Int = 0,

    @SerializedName("pressure")
    @ColumnInfo(name = "pressure")
    var pressure: Int = 0,

    @SerializedName("rain")
    @ColumnInfo(name = "rain")
    var rain: Double = 0.0,

    @SerializedName("sunrise")
    @ColumnInfo(name = "sunrise")
    var sunrise: Int = 0,

    @SerializedName("sunset")
    @ColumnInfo(name = "sunset")
    var sunset: Int = 0,

    @SerializedName("temp")
    @TypeConverters(ConverterTempObject::class)
    var temp: Temp,

    @SerializedName("uvi")
    @ColumnInfo(name = "uvi")
    var uvi: Double = 0.0,

    @SerializedName("weather")
    @ColumnInfo(name = "weather")
    var weather: String = "",

    @SerializedName("wind_deg")
    @ColumnInfo(name = "winddeg")
    var wind_deg: Int = 0,

    @SerializedName("wind_speed")
    @ColumnInfo(name = "windspeed")
    var wind_speed: Int = 0

)

@Entity(tableName = "feelsliks")
data class FeelsLike(
    @PrimaryKey
    var id_daily: String = UUID.randomUUID().toString(),

    @TypeConverters(ConverterWeatherDetailDailyObject::class)
    var weatherdailydetail: WeatherDetailDaily = WeatherDetailDaily(),

    @SerializedName("day")
    var day: Double = 0.0,

    @SerializedName("eve")
    var eve: Double = 0.0,

    @SerializedName("morn")
    var morn: Double = 0.0,

    @SerializedName("night")
    var night: Double = 0.0
)

@Entity(tableName = "weatherDetailDaily")
data class WeatherDetailDaily(

    @PrimaryKey
    @ColumnInfo(name = "weatherdailyId")
    var weatherdailyid: String = UUID.randomUUID().toString(),

    @SerializedName("id_daily")
    var id_daily: String = "",

    @SerializedName("description")
    var description: String = "",

    @SerializedName("icon")
    var icon: String = "",

    @SerializedName("id")
    var idDaily: Int = 0,

    @SerializedName("main")
    var main: String = ""
)