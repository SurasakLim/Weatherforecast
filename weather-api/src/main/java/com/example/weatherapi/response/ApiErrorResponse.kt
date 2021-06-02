package com.example.weatherapi.response

import com.google.gson.annotations.SerializedName

data class ApiErrorResponse(
    @SerializedName("status") val status: Int? = null,
    @SerializedName("code") val code: String? = null,
    @SerializedName("message") val message: String? = null,
    val data: String? = null
)
