package com.example.weatherforecast.di.serviceModule

import com.example.weatherforecast.ui.mainWeather.model.Weather
import com.example.weatherforecast.ui.weathOneCall.module.WeatherOneCall
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface WeatherApi {

    @GET("forecast?")
    fun getWeatherForCityId(@Query("q") cityId:String, @Query("appid") ApiKey:String,
                            @Query("units") units:String): Observable<Response<Weather>>

    @GET("onecall?")
    fun getWeatherOneCall(@Query("lat") lat:String, @Query("lon") lon:String,
                          @Query("appid") ApiKey:String,@Query("units") units:String): Observable<Response<WeatherOneCall>>
}