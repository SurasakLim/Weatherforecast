package com.example.weatherapi.exception

class ApiException(
    override val message: String?,
    val status: Int,
    val code: String,
    val data: String?
) : Throwable()

const val PARTIAL_PROCESSING = "PARTIAL_PROCESSING"
