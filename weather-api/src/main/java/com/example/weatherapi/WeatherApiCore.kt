package com.example.weatherapi

import android.content.Context
import com.example.weatherapi.provides.ApiEndPointUrl
import com.example.weatherapi.provides.RetrofitProvider
import retrofit2.Retrofit

object WeatherApiCore {

    fun retrofitService(
        context: Context,
        apiEndPointUrl: ApiEndPointUrl,
    ): Retrofit {
        return RetrofitProvider.getRetrofit(
            context = context,
            apiEndPointUrl = apiEndPointUrl
        )
    }
}
