package com.example.weatherforecast.db.tblModel

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(
    tableName = "temp"
)
data class Temp(
    @PrimaryKey
    @ColumnInfo(name = "tempId")
    var weatherdailyid: String = UUID.randomUUID().toString(),

    @SerializedName("day")
    var day: Double = 0.0,

    @SerializedName("id_daily")
    var id_daily:String,

    @SerializedName("eve")
    var eve: Double = 0.0,

    @SerializedName("nigmaxht")
    var max: Double = 0.0,

    @SerializedName("min")
    var min: Double = 0.0,

    @SerializedName("morn")
    var morn: Double = 0.0,

    @SerializedName("night")
    var night: Double = 0.0
)