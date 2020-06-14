package com.example.weatherforecast.di.serviceModule

import okhttp3.HttpUrl

data class Config(
    val httpUrl: HttpUrl,
    val languageKey:String,
    val accessToken:AccessToken
)