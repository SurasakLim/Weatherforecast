package com.example.weatherforecast.ui.mainWeather.domain

import com.example.weatherforecast.ui.mainWeather.model.WeathResponse
import com.example.weatherforecast.ui.mainWeather.model.Weather
import com.example.weatherforecast.ui.weathOneCall.module.WeatherOneCall
import io.reactivex.Observable
import retrofit2.Response

interface WeathDataSourceRemoteInterface {

    interface mainWeath : WeathDataSourceRemoteInterface {
        fun onRequestWeathService(cityId: String, apiKey: String): Observable<Response<Weather>>
        fun onRequestWeatherOneCall(
            lat: String,
            lon: String,
            apiKey: String
        ): Observable<Response<WeatherOneCall>>
    }

    interface RequestServiceCallback {
        fun onLoading()
        fun onSuccessRespose(weatherRes: WeathResponse<Weather>)
        fun onErrorResponse(weatherRes: WeathResponse<Weather>)
        fun onNetworkUnavailable(messageError: String)
        fun onError(messageError: String)
        fun onLoaded()
    }

    interface RequestWeatherOneCall {
        fun onResponse(result:WeathResponse<WeatherOneCall>)
    }
}