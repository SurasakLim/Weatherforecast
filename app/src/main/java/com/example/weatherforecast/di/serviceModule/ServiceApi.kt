package com.example.weatherforecast.di.serviceModule

import android.content.Context
import com.example.weatherforecast.uitl.AccessToken
import com.example.weatherforecast.uitl.Config
import com.example.weatherforecast.uitl.LiveNetworkMonitor
import okhttp3.HttpUrl

object ServiceApi {
    val url = "https://api.openweathermap.org/data/2.5/"

    fun ApiKeyProvid() = "daa5b0cffa5014d563257c5bad88e499"

    fun <T>provideService(context: Context,baseUrl:String,clazz: Class<T>): T{
        return ServiceGenerator.create(
            context,
            LiveNetworkMonitor(context),
            provideConfig(baseUrl),
            clazz
        )
    }

    fun provideConfig(baseUrl: String): Config {
        return Config(
            HttpUrl.parse(baseUrl)!!,
            "en",
            AccessToken()
        )
    }
}