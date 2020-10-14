package com.example.weatherforecast.db.tblModel

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(
    tableName = "minutely",
    foreignKeys = [ForeignKey(
        entity = TblWeatherOneCall::class,
        parentColumns = ["tblweatheroncallId"],
        childColumns = ["id_tblonecall"])
    ],
    indices = [Index("id_tblonecall")]
)
data class Minutely(
    @ColumnInfo(name = "id_tblonecall")
    var id_tblonecall:String,

    @PrimaryKey
    @ColumnInfo(name = "minutelyId")
    var id: String = UUID.randomUUID().toString(),

    @SerializedName("dt")
    var dt: Int = 0,

    @SerializedName("precipitation")
    var precipitation: Int = 0
)