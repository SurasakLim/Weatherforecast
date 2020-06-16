package com.example.weatherforecast.ui.mainWeather

import com.example.weatherforecast.di.serviceModule.ServiceApi
import com.example.weatherforecast.ui.domain.WeathDataSourceRemoteInterface
import com.example.weatherforecast.ui.domain.WeathUseCase
import com.example.weatherforecast.ui.mainWeather.model.WeathResponse
import com.example.weatherforecast.ui.mainWeather.model.Weather
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainWeatherPresenter @Inject constructor(
    var view: MainWeatherContract.View,
    private val weatherUseCase: WeathUseCase,
    private val viewModel: MainWeatherViewModel
) : MainWeatherContract.Presenter, CoroutineScope {

    private val apiKey = ServiceApi.ApiKeyProvid()

    override fun onGetWeatherData(cityId: String) {
        view.onLoading()
        weatherUseCase.getServiceWeath(
            cityId,
            apiKey,
            object : WeathDataSourceRemoteInterface.RequestServiceCallback {

                override fun onLoading() {

                }

                override fun onSuccessRespose(weatherRes: WeathResponse<Weather>) {
                    launch {
                        withContext(Dispatchers.Main) {
                            if (weatherRes.success) {
                                viewModel.weather.value = weatherRes
                                view.onShowWeather(weatherRes.data)
                            } else {
                                view.onErrorWeather(weatherRes.errorMessage)
                            }
                            view.onLoaded()
                        }
                    }
                }

                override fun onErrorResponse(weatherRes: WeathResponse<Weather>) {
                    launch {
                        withContext(Dispatchers.Main) {
                            view.onErrorWeather(weatherRes.errorMessage)
                            view.onLoaded()
                        }
                    }
                }

                override fun onNetworkUnavailable(messageError: String) {
                    launch {
                        withContext(Dispatchers.Main) {
                            view.onErrorWeather(messageError)
                            view.onLoaded()
                        }
                    }
                }

                override fun onError(messageError: String) {
                    launch {
                        withContext(Dispatchers.Main) {
                            view.onErrorWeather(messageError)
                            view.onLoaded()
                        }
                    }
                }

                override fun onLoaded() {
                    view.onLoaded()
                }

            })
    }

    val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}