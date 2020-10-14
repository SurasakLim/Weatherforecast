package com.example.weatherforecast.db.tblModel

import androidx.room.TypeConverter
import com.example.weatherforecast.di.serviceModule.ServiceGenerator.create
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ConvertresDalyList {

    @TypeConverter
    fun toJsonString(dataFrom: List<Daily>): String = Gson().toJson(dataFrom)

    @TypeConverter
    fun fromJsonList(list: String): List<Daily> {
        val gson = GsonBuilder().create()
        return gson.fromJson(list, Array<Daily>::class.java).toList()
    }
}

class ConvertresHourlyList {

    @TypeConverter
    fun toJsonString(dataFrom: List<Hourly>): String = Gson().toJson(dataFrom)

    @TypeConverter
    fun fromJsonList(list: String): List<Hourly> {
        val listType: Type = object : TypeToken<List<Hourly>>() {}.type
        return Gson().fromJson(list, listType)
    }
}

class ConvertresWeatherList {

    @TypeConverter
    fun toJsonString(dataFrom: List<Weather>): String = Gson().toJson(dataFrom)

    @TypeConverter
    fun fromJsonList(list: String): List<Weather> {
        val listType: Type = object : TypeToken<List<Weather>>() {}.type
        return Gson().fromJson(list, listType)
    }
}

class ConvertreMinutelyList {

    @TypeConverter
    fun toJsonString(dataFrom: List<Minutely>): String = Gson().toJson(dataFrom)

    @TypeConverter
    fun fromJsonList(list: String): List<Minutely> {
        val listType: Type = object : TypeToken<List<Minutely>>() {}.type
        return Gson().fromJson(list, listType)
    }
}

class ConverterTypeObject {
    @TypeConverter
    fun  toJsonString(dataFrom: Current): String = Gson().toJson(dataFrom)

    @TypeConverter
    fun  fromJsonList(list: String): Current {
        return Gson().fromJson(list, Current::class.java)
    }
}

class ConverterFeelsLikeObject {
    @TypeConverter
    fun  toJsonString(dataFrom: FeelsLike): String = Gson().toJson(dataFrom)

    @TypeConverter
    fun  fromJsonList(list: String): FeelsLike {
        return Gson().fromJson(list, FeelsLike::class.java)
    }
}

class ConverterWeatherDetailDailyObject {
    @TypeConverter
    fun  toJsonString(dataFrom: WeatherDetailDaily): String = Gson().toJson(dataFrom)

    @TypeConverter
    fun  fromJsonList(list: String): WeatherDetailDaily {
        return Gson().fromJson(list, WeatherDetailDaily::class.java)
    }
}

class ConverterTempObject {
    @TypeConverter
    fun  toJsonString(dataFrom: Temp): String = Gson().toJson(dataFrom)

    @TypeConverter
    fun  fromJsonList(list: String): Temp {
        return Gson().fromJson(list, Temp::class.java)
    }
}