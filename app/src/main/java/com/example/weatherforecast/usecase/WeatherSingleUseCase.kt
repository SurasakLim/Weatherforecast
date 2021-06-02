package com.example.weatherforecast.usecase

import com.example.weatherapi.api.weatherapi.WeatherRepository
import com.example.weatherapi.provides.ApiEndPointUrl
import com.example.weatherforecast.data.error.Ignored
import com.example.weatherforecast.ui.baserx.SingleUseCase
import com.example.weatherforecast.ui.screen.mainWeather.model.Weather
import com.example.weatherforecast.ui.screen.mainWeather.model.toWeather
import io.reactivex.Single
import th.co.the1.the1app.common.lib.schedulers.RxSchedulerProvider
import javax.inject.Inject

class WeatherSingleUseCase @Inject constructor(
    rxSchedulerProvider: RxSchedulerProvider,
    private val secretKey: ApiEndPointUrl,
    private val weatherRepository: WeatherRepository
) : SingleUseCase<String, Weather>(
    rxSchedulerProvider.io(),
    rxSchedulerProvider.main(),
    ::Ignored
) {

    override fun create(input: String): Single<Weather> {
        return weatherRepository
            .getWeatherWithCountryId(input, secretKey.key)
            .map { it.toWeather() }
    }
}
