package com.example.weatherapi.api.weatherapi

import com.example.weatherapi.api.weatherapi.response.WeatherResponse
import com.example.weatherapi.api.weatherapi.response.WeatherOneCall
import com.example.weatherapi.operators.ApiOperators
import io.reactivex.Single
import javax.inject.Inject

interface WeatherRepository {
    fun getWeatherWithCountryId(countryId: String, appKey: String): Single<WeatherResponse>
    fun getWeatherWithOneCall(
        lat: String,
        lon: String,
        appKey: String,
        unite: String
    ): Single<WeatherOneCall>
}

class WeatherRepositoryImpl @Inject constructor(private val weatherApi: WeatherApi) :
    WeatherRepository {

    override fun getWeatherWithCountryId(countryId: String, appKey: String): Single<WeatherResponse> {
        return weatherApi
            .getWeatherForCityId(countryId, appKey, "metric")
            .lift(ApiOperators.mobileApiError())
            .map { it }
            .firstOrError()
    }

    override fun getWeatherWithOneCall(
        lat: String,
        lon: String,
        appKey: String,
        unite: String
    ): Single<WeatherOneCall> {
        return weatherApi.getWeatherOneCall(lat, lon, appKey, unite)
            .lift(ApiOperators.mobileApiError())
            .map { it }
            .firstOrError()
    }
}
