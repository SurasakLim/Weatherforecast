package com.example.weatherapi.provides

import android.content.Context
import com.example.weatherapi.provides.ConverterFactoryProvider.getDefaultMoshiConverterFactory
import com.example.weatherapi.provides.ConverterFactoryProvider.getGsonConverterFactory
import com.example.weatherapi.provides.ConverterFactoryProvider.getJsonApiConverterFactory
import com.example.weatherapi.provides.OkHttpClientProvider.getOkHttpClientBuilder
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

internal object RetrofitProvider {

    internal fun getRetrofit(
        context: Context,
        apiEndPointUrl: ApiEndPointUrl
    ): Retrofit {
        return assembleRetrofit(
            context,
            apiEndPointUrl, true
        )
    }

    private fun provideMoshi() = MoshiBuilderProvider.moshiBuilder.build()

    private fun provideConverterFactories(
        moshi: Moshi,
        gson: Gson,
        isRestApi: Boolean
    ): Array<Converter.Factory> {
        return when {
            isRestApi -> {
                arrayOf(getGsonConverterFactory(gson))
            }
            else -> {
                arrayOf(
                    getJsonApiConverterFactory(moshi),
                    getDefaultMoshiConverterFactory()
                )
            }
        }
    }

    private fun assembleRetrofit(
        context: Context,
        apiEndPointUrl: ApiEndPointUrl,
        isRestApi: Boolean? = null,
    ): Retrofit {
        val okHttpClient = provideOkHttpClient(context)
        val moshi = provideMoshi()
        val gson = provideGson()
        val converterFactories =
            provideConverterFactories(moshi, gson, isRestApi ?: false)
        return provideRetrofitBuilder(apiEndPointUrl, okHttpClient, *converterFactories).build()
    }

    private fun provideGson() = GsonBuilder()
        .setLenient()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create()

    private fun provideOkHttpClient(
        context: Context,
    ): OkHttpClient {
        return getOkHttpClientBuilder(
            context,
        ).build()
    }

    private fun provideRetrofitBuilder(
        apiEndpointUrl: ApiEndPointUrl,
        okHttpClient: OkHttpClient,
        vararg converterFactories: Converter.Factory
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(apiEndpointUrl.value)
            .client(okHttpClient)
            .also {
                for (converterFactory in converterFactories) {
                    it.addConverterFactory(converterFactory)
                }
            }
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }
}
