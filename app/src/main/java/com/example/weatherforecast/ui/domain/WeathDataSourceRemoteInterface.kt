package com.example.weatherforecast.ui.domain

import com.example.weatherforecast.ui.mainWeather.model.WeathResponse
import com.example.weatherforecast.ui.mainWeather.model.Weather
import io.reactivex.Observable
import retrofit2.Response

interface WeathDataSourceRemoteInterface {

    interface mainWeath:WeathDataSourceRemoteInterface{
        fun onRequestWeathService(cityId:String,apiKey:String): Observable<Response<Weather>>
    }

    interface RequestServiceCallback{
        fun onLoading()
        fun onSuccessRespose(weatherRes:WeathResponse<Weather>)
        fun onErrorResponse(weatherRes:WeathResponse<Weather>)
        fun onNetworkUnavailable(messageError:String)
        fun onError(messageError:String)
        fun onLoaded()
    }
}