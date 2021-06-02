package com.example.weatherapi

import android.content.Context
import com.example.weatherapi.provides.ApiEndPointUrl
import com.example.weatherapi.provides.Secrets
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class NetworkModule {

    @Provides
    fun provideApiEndPointUrl() = Secrets.apiEndpointUrl

    @Provides
    fun provideAppKey() = Secrets.apiEndpointUrl.key

    @Provides
    fun provideRetrofitService(
        context: Context,
        apiEndPointUrl: ApiEndPointUrl
    ): Retrofit {
        return WeatherApiCore.retrofitService(context, apiEndPointUrl)
    }
}
