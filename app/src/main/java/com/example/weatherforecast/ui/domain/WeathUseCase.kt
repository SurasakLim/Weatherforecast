package com.example.weatherforecast.ui.domain

import com.example.weatherforecast.uitl.NoNetworkException
import com.example.weatherforecast.ui.mainWeather.model.WeathResponse
import com.example.weatherforecast.ui.mainWeather.model.Weather
import com.example.weatherforecast.uitl.AppExecutors
import com.example.weatherforecast.uitl.NetworkBoundResourceObserver
import io.reactivex.Observable
import retrofit2.Response
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class WeathUseCase @Inject constructor(var dataSourceRemote: WeathDataSourceRemote):WeathInterface{

    private val appExecutors = AppExecutors.newInstance()

    override fun getServiceWeath(cityID: String, apiKey: String,
                                 callBack: WeathDataSourceRemoteInterface.RequestServiceCallback) {
        val dataResponse = WeathResponse(false,"",Weather())

        NetworkBoundResourceObserver(appExecutors, object : NetworkBoundResourceObserver.Callback<Weather,Response<Weather>>(){
            override fun onCreateObservable(): Observable<Response<Weather>> {
                return dataSourceRemote.onRequestWeathService(cityID,apiKey)
            }

            override fun onResponse(code: Int, message: String, item: Weather?) {
                when(code){
                    in 200..201 -> {
                        if(item != null){
                            dataResponse.apply {
                                success = true
                                data = item
                            }
                            callBack.onSuccessRespose(dataResponse)
                        }
                    } else ->{
                        dataResponse.apply {
                            success = true
                            data = item!!
                            errorMessage = message
                        }
                        callBack.onErrorResponse(dataResponse)
                    }
                }
            }

            override fun onTimeout(): Long {
                return 1800
            }

            override fun onUnauthorized() {
                dataResponse.apply {
                    success = false
                    errorMessage = "Unauthorized"
                }
                callBack.onErrorResponse(dataResponse)
            }

            override fun onError(t: Throwable) {
                dataResponse.apply {
                    success = false
                    errorMessage = t.message!!
                }
                callBack.onErrorResponse(dataResponse)
            }

            override fun onWarning(t: Throwable) {
                dataResponse.apply {
                    success = false
                    errorMessage = t.message!!
                }
                callBack.onErrorResponse(dataResponse)
            }

            override fun onNetworkUnavailable(t: NoNetworkException) {
                dataResponse.apply {
                    success = false
                    errorMessage = t.message!!
                }
                callBack.onNetworkUnavailable(t.localizedMessage!!)
            }

            override fun onTimeoutException(e: TimeoutException) {
                dataResponse.apply {
                    success = false
                    errorMessage = e.message!!
                }
                callBack.onNetworkUnavailable(e.message!!)
            }

        })
    }

}