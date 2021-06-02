package com.example.weatherapi.operators

object ApiOperators {

    fun <T> mobileApiError(): MobileApiErrorOperator<T> {
        return MobileApiErrorOperator()
    }
}
