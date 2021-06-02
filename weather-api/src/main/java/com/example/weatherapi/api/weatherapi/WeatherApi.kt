package com.example.weatherapi.api.weatherapi

import com.example.weatherapi.api.weatherapi.response.WeatherResponse
import com.example.weatherapi.api.weatherapi.response.WeatherOneCall
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast?")
    fun getWeatherForCityId(
        @Query("q") cityId: String, @Query("appid") ApiKey: String,
        @Query("units") units: String
    ): Flowable<Response<WeatherResponse>>

    @GET("onecall?")
    fun getWeatherOneCall(
        @Query("lat") lat: String, @Query("lon") lon: String,
        @Query("appid") ApiKey: String, @Query("units") units: String
    ): Flowable<Response<WeatherOneCall>>
}
