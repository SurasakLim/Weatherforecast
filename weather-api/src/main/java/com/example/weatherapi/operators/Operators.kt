package com.example.weatherapi.operators

import com.example.weatherapi.provides.MoshiBuilderProvider
import com.squareup.moshi.Moshi

object Operators {
    private val moshi: Moshi by lazy { MoshiBuilderProvider.moshiBuilder.build() }

    fun <T> apiError(): ApiErrorOperator<T> {
        return ApiErrorOperator(moshi)
    }
}
