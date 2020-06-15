package com.example.weatherforecast.uitl

import com.example.weatherforecast.uitl.AccessToken
import okhttp3.HttpUrl

data class Config(
    val httpUrl: HttpUrl,
    val languageKey:String,
    val accessToken: AccessToken
)