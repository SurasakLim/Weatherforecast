package com.example.weatherapi.provides

import com.example.weatherapi.api.weatherapi.response.WeatherResponse
import moe.banana.jsonapi2.ResourceAdapterFactory

internal class ResourceAdapterProvider {
    val resourceFactory: ResourceAdapterFactory
        get() = ResourceAdapterFactory
            .builder()
            // Adding all the possible resource definition here
            .add(
            )
            .build()
}
