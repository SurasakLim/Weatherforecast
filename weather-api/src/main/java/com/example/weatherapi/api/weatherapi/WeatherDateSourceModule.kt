package com.example.weatherapi.api.weatherapi

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class WeatherDateSourceModule {

    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi)
    }
}