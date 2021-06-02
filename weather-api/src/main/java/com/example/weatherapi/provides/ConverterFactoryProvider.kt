package com.example.weatherapi.provides

import com.google.gson.Gson
import com.squareup.moshi.Moshi
import moe.banana.jsonapi2.JsonApiConverterFactory
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

internal object ConverterFactoryProvider {
    fun getJsonApiConverterFactory(moshi: Moshi): Converter.Factory {
        return JsonApiConverterFactory.create(moshi)
    }

    fun getDefaultMoshiConverterFactory(): Converter.Factory {
        return MoshiConverterFactory.create()
    }

    fun getGsonConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }
}
