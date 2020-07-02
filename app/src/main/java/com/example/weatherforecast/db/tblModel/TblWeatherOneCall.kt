package com.example.weatherforecast.db.tblModel

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "TblWeatherOneCall")
class TblWeatherOneCall(
    @PrimaryKey
    @ColumnInfo(name = "TblWeatherOnCallId")
    val id: String = UUID.randomUUID().toString(),

    @SerializedName("current")
    @Relation(
        parentColumn = "TblWeatherOnCallId",
        entityColumn = "CurrentId"
    )
    val current: Current = Current(),

    @SerializedName("daily")
    @Relation(
        parentColumn = "TblWeatherOnCallId",
        entityColumn = "DailyId"
    )
    val daily: List<Daily> = listOf(),

    @SerializedName("hourly")
    @Relation(
        parentColumn = "TblWeatherOnCallId",
        entityColumn = "HourlyId"
    )
    val hourly: List<Hourly> = listOf(),

    @SerializedName("lat")
    @ColumnInfo(name = "lat")
    val lat: Double = 0.0,

    @SerializedName("lon")
    @ColumnInfo(name = "lon")
    val lon: Double = 0.0,

    @SerializedName("minutely")
    @Relation(
        parentColumn = "TblWeatherOnCallId",
        entityColumn = "Minutely"
    )
    val minutely: List<Minutely> = listOf(),

    @SerializedName("timezone")
    @ColumnInfo(name = "timezone")
    val timezone: String = "",

    @SerializedName("timezone_offset")
    @ColumnInfo(name = "timezone_offset")
    val timezone_offset: Int = 0
)

@Entity(tableName = "Current")
data class Current(
    @PrimaryKey
    @ColumnInfo(name = "CurrentId")
    val id: String = UUID.randomUUID().toString(),

    @SerializedName("clouds")
    @ColumnInfo(name = "clouds")
    val clouds: Int = 0,

    @SerializedName("dew_point")
    @ColumnInfo(name = "dew_point")
    val dew_point: Double = 0.0,

    @SerializedName("dt")
    @ColumnInfo(name = "dt")
    val dt: Int = 0,

    @SerializedName("feels_like")
    @ColumnInfo(name = "feels_like")
    val feels_like: Double = 0.0,

    @SerializedName("humidity")
    @ColumnInfo(name = "humidity")
    val humidity: Int = 0,

    @SerializedName("pressure")
    @ColumnInfo(name = "pressure")
    val pressure: Int = 0,

    @SerializedName("sunrise")
    @ColumnInfo(name = "sunrise")
    val sunrise: Int = 0,

    @SerializedName("sunset")
    @ColumnInfo(name = "sunset")
    val sunset: Int = 0,

    @SerializedName("temp")
    @ColumnInfo(name = "temp")
    val temp: Double = 0.0,

    @SerializedName("uvi")
    @ColumnInfo(name = "uvi")
    val uvi: Double = 0.0,

    @SerializedName("visibility")
    @ColumnInfo(name = "visibility")
    val visibility: Int = 0,

    @SerializedName("weather")
    @Relation(
        parentColumn = "CurrentId",
        entityColumn = "WeatherId"
    )
    val weather: List<Weather> = listOf(),

    @SerializedName("wind_deg")
    @ColumnInfo(name = "wind_deg")
    val wind_deg: Int = 0,

    @SerializedName("wind_speed")
    @ColumnInfo(name = "wind_speed")
    val wind_speed: Double = 0.0
)

@Entity(tableName = "Daily")
data class Daily(
    @PrimaryKey
    @ColumnInfo(name = "DailyId")
    val id: String = UUID.randomUUID().toString(),

    @SerializedName("clouds")
    @ColumnInfo(name = "clouds")
    val clouds: Int = 0,

    @SerializedName("dew_point")
    @ColumnInfo(name = "dew_point")
    val dew_point: Double = 0.0,

    @SerializedName("dt")
    @ColumnInfo(name = "dt")
    val dt: Int = 0,

    @SerializedName("feels_like")
    @Relation(
        parentColumn = "DailyId",
        entityColumn = "feelsliksid"
    )
    val feels_like: FeelsLike,

    @SerializedName("humidity")
    @ColumnInfo(name = "humidity")
    val humidity: Int = 0,

    @SerializedName("pressure")
    @ColumnInfo(name = "pressure")
    val pressure: Int = 0,

    @SerializedName("rain")
    @ColumnInfo(name = "rain")
    val rain: Double = 0.0,

    @SerializedName("sunrise")
    @ColumnInfo(name = "sunrise")
    val sunrise: Int = 0,

    @SerializedName("sunset")
    @ColumnInfo(name = "sunset")
    val sunset: Int = 0,

    @SerializedName("temp")
    @Relation(
        parentColumn = "DailyId",
        entityColumn = "TempId"
    )
    val temp: Temp,

    @SerializedName("uvi")
    @ColumnInfo(name = "uvi")
    val uvi: Double = 0.0,

    @SerializedName("weather")
    @Relation(
        parentColumn = "DailyId",
        entityColumn = "WeatherDailyId"
    )
    val weather: List<WeatherDetailDaily>,

    @SerializedName("wind_deg")
    @ColumnInfo(name = "wind_deg")
    val wind_deg: Int = 0,

    @SerializedName("wind_speed")
    @ColumnInfo(name = "wind_speed")
    val wind_speed: Int = 0

)

@Entity(tableName = "Hourly")
data class Hourly(
    @PrimaryKey
    @ColumnInfo(name = "HourlyId")
    val id: String = UUID.randomUUID().toString(),

    @SerializedName("clouds")
    val clouds: Int = 0,

    @SerializedName("dew_point")
    val dew_point: Double = 0.0,

    @SerializedName("dt")
    val dt: Int = 0,

    @SerializedName("feels_like")
    val feels_like: Double = 0.0,

    @SerializedName("humidity")
    val humidity: Int = 0,

    @SerializedName("pressure")
    val pressure: Int = 0,

    @SerializedName("rain")
    @Relation(
        parentColumn = "HourlyId",
        entityColumn = "RainID"
    )
    val rain: Rain,

    @SerializedName("temp")
    val temp: Double = 0.0,

    @SerializedName("weather")
    @Relation(
        parentColumn = "HourlyId",
        entityColumn = "weatherdetailid"
    )
    val weather: List<WeatherDetail> = listOf(),

    @SerializedName("wind_deg")
    val wind_deg: Int = 0,

    @SerializedName("wind_speed")
    val wind_speed: Double = 0.0
)

@Entity(tableName = "Minutely")
data class Minutely(
    @PrimaryKey
    @ColumnInfo(name = "MinutelyId")
    val id: String = UUID.randomUUID().toString(),

    @SerializedName("dt")
    val dt: Int = 0,

    @SerializedName("precipitation")
    val precipitation: Int = 0
)

@Entity(tableName = "Weather")
data class Weather(
    @PrimaryKey
    @ColumnInfo(name = "WeatherId")
    val WeatherId: String = UUID.randomUUID().toString(),

    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String = "",

    @SerializedName("icon")
    @ColumnInfo(name = "icon")
    val icon: String = "",

    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @SerializedName("main")
    @ColumnInfo(name = "main")
    val main: String = ""
)

@Entity(tableName = "feelsliks")
data class FeelsLike(
    @PrimaryKey
    @ColumnInfo(name = "feelsliksid")
    val weatherDailyId: String = UUID.randomUUID().toString(),

    @SerializedName("day")
    val day: Double = 0.0,

    @SerializedName("eve")
    val eve: Double = 0.0,

    @SerializedName("morn")
    val morn: Double = 0.0,

    @SerializedName("night")
    val night: Double = 0.0
)

@Entity(tableName = "Temp")
data class Temp(
    @PrimaryKey
    @ColumnInfo(name = "TempId")
    val weatherDailyId: String = UUID.randomUUID().toString(),

    @SerializedName("day")
    val day: Double = 0.0,

    @SerializedName("eve")
    val eve: Double = 0.0,

    @SerializedName("nigmaxht")
    val max: Double = 0.0,

    @SerializedName("min")
    val min: Double = 0.0,

    @SerializedName("morn")
    val morn: Double = 0.0,

    @SerializedName("night")
    val night: Double = 0.0
)

@Entity(tableName = "WeatherDetailDaily")
data class WeatherDetailDaily(
    @PrimaryKey
    @ColumnInfo(name = "WeatherDailyId")
    val weatherDailyId: String = UUID.randomUUID().toString(),

    @SerializedName("description")
    val description: String = "",

    @SerializedName("icon")
    val icon: String = "",

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("main")
    val main: String = ""
)

@Entity(tableName = "Rain")
data class Rain(
    @PrimaryKey
    @ColumnInfo(name = "RainID")
    val rainId: String = UUID.randomUUID().toString(),

    @SerializedName("1h")
    val `1h`: Double = 0.0
)

@Entity(tableName = "WeatherDetail")
data class WeatherDetail(
    @PrimaryKey
    @ColumnInfo(name = "weatherdetailid")
    val weatheDetailyId: String = UUID.randomUUID().toString(),

    @SerializedName("description")
    val description: String = "",

    @SerializedName("icon")
    val icon: String = "",

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("main")
    val main: String = ""
)