package com.example.weatherapi.provides

object Secrets {
    val apiEndpointUrl: ApiEndPointUrl
        get() = ApiEndPointUrlImpl()
}

internal class ApiEndPointUrlImpl : ApiEndPointUrl {
    override val value: String
        get() = "https://api.openweathermap.org/data/2.5/"

    override val key: String
        get() = "daa5b0cffa5014d563257c5bad88e499"
}
