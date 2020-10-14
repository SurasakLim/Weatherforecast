package com.example.weatherforecast.db.tblModel

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(
    tableName = "hourly",
    foreignKeys = [ForeignKey(
        entity = TblWeatherOneCall::class,
        parentColumns = ["tblweatheroncallId"],
        childColumns = ["id_tblonecall"])
    ],
    indices = [Index("id_tblonecall")]
)
data class Hourly(
    @ColumnInfo(name = "id_tblonecall")
    var id_tblonecall:String,

    @PrimaryKey
    @ColumnInfo(name = "hourlyId")
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
    @ColumnInfo(name = "feelslike")
    var feels_like: Double = 0.0,

    @SerializedName("humidity")
    @ColumnInfo(name = "humidity")
    var humidity: Int = 0,

    @SerializedName("pressure")
    @ColumnInfo(name = "pressure")
    var pressure: Int = 0,

    @SerializedName("rain")
    @Relation(
        parentColumn = "hourlyId",
        entityColumn = "rainId"
    )
    var rain: Rain = Rain(),

    @SerializedName("temp")
    @ColumnInfo(name = "temp")
    var temp: Double = 0.0,

    @SerializedName("weather")
    @ColumnInfo(name = "weather")
    var weather: List<WeatherDetail> = listOf(),

    @SerializedName("wind_deg")
    @ColumnInfo(name = "winddeg")
    var wind_deg: Int = 0,

    @SerializedName("wind_speed")
    @ColumnInfo(name = "windspeed")
    var wind_speed: Double = 0.0
)

@Entity(tableName = "weatherdetail")
data class WeatherDetail(
    @PrimaryKey
    @ColumnInfo(name = "weatherdetailId")
    var weatheDetailyId: String = UUID.randomUUID().toString(),

    @SerializedName("description")
    var description: String = "",

    @SerializedName("icon")
    var icon: String = "",

    @SerializedName("id")
    var idDetail: Int = 0,

    @SerializedName("main")
    var main: String = ""
)

@Entity(    tableName = "rain")
data class Rain(
    @PrimaryKey
    @ColumnInfo(name = "rainId")
    var id: String = UUID.randomUUID().toString(),

    @SerializedName("1h")
    var `1h`: Double = 0.0
)