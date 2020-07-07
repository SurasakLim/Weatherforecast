//package com.example.weatherforecast.db.tblModel
//
//import androidx.room.*
//import com.google.gson.annotations.SerializedName
//import java.util.*
//
//
//@Entity(tableName = "tblweatheronecall")
//data class TblWeatherOneCall(
//    @PrimaryKey
//    @ColumnInfo(name = "tblweatheroncallId")
//    var id: String = UUID.randomUUID().toString(),
//
//    @SerializedName("current")
//    @Relation(
//        parentColumn = "currentId",
//        entityColumn = "tblweatheroncallId"
//    )
//    var current: Current = Current(),
//
//    @SerializedName("daily")
//    @Relation(
//        parentColumn = "dailyId",
//        entityColumn = "tblweatheroncallId"
//    )
//    var daily: List<Daily> = listOf(),
//
//    @SerializedName("hourly")
//    @Relation(
//        parentColumn = "hourlyId",
//        entityColumn = "tblweatheroncallId"
//    )
//    var hourly: List<Hourly> = listOf(),
//
//    @SerializedName("lat")
//    @ColumnInfo(name = "lat")
//    var lat: Double = 0.0,
//
//    @SerializedName("lon")
//    @ColumnInfo(name = "lon")
//    var lon: Double = 0.0,
//
//    @SerializedName("minutely")
//    @Relation(
//        parentColumn = "minutelyId",
//        entityColumn = "tblweatheroncallId"
//    )
//    var minutely: List<Minutely> = listOf(),
//
//    @SerializedName("timezone")
//    @ColumnInfo(name = "timezone")
//    var timezone: String = "",
//
//    @SerializedName("timezone_offset")
//    @ColumnInfo(name = "timezoneoffset")
//    var timezone_offset: Int = 0
//)
//
//@Entity(tableName = "current")
//data class Current(
//    @PrimaryKey
//    @ColumnInfo(name = "currentId")
//    var currentId: String = UUID.randomUUID().toString(),
//
//    @SerializedName("clouds")
//    @ColumnInfo(name = "clouds")
//    var clouds: Int = 0,
//
//    @SerializedName("dew_point")
//    @ColumnInfo(name = "dewpoint")
//    var dew_point: Double = 0.0,
//
//    @SerializedName("dt")
//    @ColumnInfo(name = "dt")
//    var dt: Int = 0,
//
//    @SerializedName("feels_like")
//    @ColumnInfo(name = "feelslike")
//    var feels_like: Double = 0.0,
//
//    @SerializedName("humidity")
//    @ColumnInfo(name = "humidity")
//    var humidity: Int = 0,
//
//    @SerializedName("pressure")
//    @ColumnInfo(name = "pressure")
//    var pressure: Int = 0,
//
//    @SerializedName("sunrise")
//    @ColumnInfo(name = "sunrise")
//    var sunrise: Int = 0,
//
//    @SerializedName("sunset")
//    @ColumnInfo(name = "sunset")
//    var sunset: Int = 0,
//
//    @SerializedName("temp")
//    @ColumnInfo(name = "temp")
//    var temp: Double = 0.0,
//
//    @SerializedName("uvi")
//    @ColumnInfo(name = "uvi")
//    var uvi: Double = 0.0,
//
//    @SerializedName("visibility")
//    @ColumnInfo(name = "visibility")
//    var visibility: Int = 0,
//
//    @SerializedName("weather")
//    @Relation(
//        parentColumn = "currentId",
//        entityColumn = "weatherId",
//        entity = Weather::class
//    )
//    var weather: List<Weather> = listOf(),
//
//    @SerializedName("wind_deg")
//    @ColumnInfo(name = "winddeg")
//    var wind_deg: Int = 0,
//
//    @SerializedName("wind_speed")
//    @ColumnInfo(name = "windspeed")
//    var wind_speed: Double = 0.0
//)
//
//@Entity(tableName = "daily")
//data class Daily(
//    @PrimaryKey
//    @ColumnInfo(name = "dailyId")
//    var id: String = UUID.randomUUID().toString(),
//
//    @SerializedName("clouds")
//    @ColumnInfo(name = "clouds")
//    var clouds: Int = 0,
//
//    @SerializedName("dew_point")
//    @ColumnInfo(name = "dewpoint")
//    var dew_point: Double = 0.0,
//
//    @SerializedName("dt")
//    @ColumnInfo(name = "dt")
//    var dt: Int = 0,
//
//    @SerializedName("feels_like")
//    @Relation(
//        parentColumn = "dailyId",
//        entityColumn = "feelsliksId"
//    )
//    var feels_like: FeelsLike,
//
//    @SerializedName("humidity")
//    @ColumnInfo(name = "humidity")
//    var humidity: Int = 0,
//
//    @SerializedName("pressure")
//    @ColumnInfo(name = "pressure")
//    var pressure: Int = 0,
//
//    @SerializedName("rain")
//    @ColumnInfo(name = "rain")
//    var rain: Double = 0.0,
//
//    @SerializedName("sunrise")
//    @ColumnInfo(name = "sunrise")
//    var sunrise: Int = 0,
//
//    @SerializedName("sunset")
//    @ColumnInfo(name = "sunset")
//    var sunset: Int = 0,
//
//    @SerializedName("temp")
//    @Relation(
//        parentColumn = "dailyId",
//        entityColumn = "tempId"
//    )
//    var temp: Temp = Temp(),
//
//    @SerializedName("uvi")
//    @ColumnInfo(name = "uvi")
//    var uvi: Double = 0.0,
//
//    @SerializedName("weather")
//    @Relation(
//        parentColumn = "dailyId",
//        entityColumn = "weatherdailyId"
//    )
//    var weather: List<WeatherDetailDaily>,
//
//    @SerializedName("wind_deg")
//    @ColumnInfo(name = "winddeg")
//    var wind_deg: Int = 0,
//
//    @SerializedName("wind_speed")
//    @ColumnInfo(name = "windspeed")
//    var wind_speed: Int = 0
//
//)
//
//@Entity(tableName = "hourly")
//data class Hourly(
//    @PrimaryKey
//    @ColumnInfo(name = "hourlyId")
//    var id: String = UUID.randomUUID().toString(),
//
//    @SerializedName("clouds")
//    @ColumnInfo(name = "clouds")
//    var clouds: Int = 0,
//
//    @SerializedName("dew_point")
//    @ColumnInfo(name = "dewpoint")
//    var dew_point: Double = 0.0,
//
//    @SerializedName("dt")
//    @ColumnInfo(name = "dt")
//    var dt: Int = 0,
//
//    @SerializedName("feels_like")
//    @ColumnInfo(name = "feelslike")
//    var feels_like: Double = 0.0,
//
//    @SerializedName("humidity")
//    @ColumnInfo(name = "humidity")
//    var humidity: Int = 0,
//
//    @SerializedName("pressure")
//    @ColumnInfo(name = "pressure")
//    var pressure: Int = 0,
//
//    @SerializedName("rain")
//    @Relation(
//        parentColumn = "hourlyId",
//        entityColumn = "rainId"
//    )
//    var rain: Rain = Rain(),
//
//    @SerializedName("temp")
//    @ColumnInfo(name = "temp")
//    var temp: Double = 0.0,
//
//    @SerializedName("weather")
//    @Relation(
//        parentColumn = "hourlyId",
//        entityColumn = "weatherdetailId"
//    )
//    var weather: List<WeatherDetail> = listOf(),
//
//    @SerializedName("wind_deg")
//    @ColumnInfo(name = "winddeg")
//    var wind_deg: Int = 0,
//
//    @SerializedName("wind_speed")
//    @ColumnInfo(name = "windspeed")
//    var wind_speed: Double = 0.0
//)
//
//@Entity(tableName = "minutely")
//data class Minutely(
//    @PrimaryKey
//    @ColumnInfo(name = "minutelyId")
//    var id: String = UUID.randomUUID().toString(),
//
//    @SerializedName("dt")
//    var dt: Int = 0,
//
//    @SerializedName("precipitation")
//    var precipitation: Int = 0
//)
//
//@Entity(tableName = "weather")
//data class Weather(
//    @PrimaryKey
//    @ColumnInfo(name = "weatherId")
//    var WeatherId: String = UUID.randomUUID().toString(),
//
//    @SerializedName("description")
//    @ColumnInfo(name = "description")
//    var description: String = "",
//
//    @SerializedName("icon")
//    @ColumnInfo(name = "icon")
//    var icon: String = "",
//
//    @SerializedName("id")
//    @ColumnInfo(name = "idWeather")
//    var idWeather: Int = 0,
//
//    @SerializedName("main")
//    @ColumnInfo(name = "main")
//    var main: String = ""
//)
//
//@Entity(tableName = "feelsliks")
//data class FeelsLike(
//    @PrimaryKey
//    @ColumnInfo(name = "feelsliksId")
//    var weatherdailyid: String = UUID.randomUUID().toString(),
//
//    @SerializedName("day")
//    var day: Double = 0.0,
//
//    @SerializedName("eve")
//    var eve: Double = 0.0,
//
//    @SerializedName("morn")
//    var morn: Double = 0.0,
//
//    @SerializedName("night")
//    var night: Double = 0.0
//)
//
//@Entity(tableName = "temp")
//data class Temp(
//    @PrimaryKey
//    @ColumnInfo(name = "tempId")
//    var weatherdailyid: String = UUID.randomUUID().toString(),
//
//    @SerializedName("day")
//    var day: Double = 0.0,
//
//    @SerializedName("eve")
//    var eve: Double = 0.0,
//
//    @SerializedName("nigmaxht")
//    var max: Double = 0.0,
//
//    @SerializedName("min")
//    var min: Double = 0.0,
//
//    @SerializedName("morn")
//    var morn: Double = 0.0,
//
//    @SerializedName("night")
//    var night: Double = 0.0
//)
//
//@Entity(tableName = "weatherDetailDaily")
//data class WeatherDetailDaily(
//    @PrimaryKey
//    @ColumnInfo(name = "weatherdailyId")
//    var weatherdailyid: String = UUID.randomUUID().toString(),
//
//    @SerializedName("description")
//    var description: String = "",
//
//    @SerializedName("icon")
//    var icon: String = "",
//
//    @SerializedName("id")
//    var idDaily: Int = 0,
//
//    @SerializedName("main")
//    var main: String = ""
//)
//
//@Entity(tableName = "rain")
//data class Rain(
//    @PrimaryKey
//    @ColumnInfo(name = "rainId")
//    var id: String = UUID.randomUUID().toString(),
//
//    @SerializedName("1h")
//    var `1h`: Double = 0.0
//)
//
//@Entity(tableName = "weatherdetail")
//data class WeatherDetail(
//    @PrimaryKey
//    @ColumnInfo(name = "weatherdetailId")
//    var weatheDetailyId: String = UUID.randomUUID().toString(),
//
//    @SerializedName("description")
//    var description: String = "",
//
//    @SerializedName("icon")
//    var icon: String = "",
//
//    @SerializedName("id")
//    var idDetail: Int = 0,
//
//    @SerializedName("main")
//    var main: String = ""
//)