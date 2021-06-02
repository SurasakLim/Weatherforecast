package com.example.weatherapi.provides

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import java.util.*

internal object MoshiBuilderProvider {
    val moshiBuilder: Moshi.Builder
        get() {
            return Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter())
                .add(ResourceAdapterProvider().resourceFactory)
        }
}
