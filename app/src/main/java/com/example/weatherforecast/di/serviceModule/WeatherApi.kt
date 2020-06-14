package com.example.weatherforecast.di.serviceModule

import com.example.weatherforecast.ui.mainWeather.model.Weather
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface WeatherApi {

    @GET("forecast?q=},us&appid=}")
    fun getWeatherForCityId(@Query("q") cityId:String, @Query("appid") ApiKey:String): Observable<Response<Weather>>
}