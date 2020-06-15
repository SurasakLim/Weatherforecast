package com.example.weatherforecast.uitl

import android.os.Build
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object StringExtenion {

    fun String.fromatTemperatureCelsius() = this + "\u2103"
    fun String.fromatTemperatureFahrenheit() = this + "\u2109"

    fun String.dateToDay(): String? {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val outputFormat = DateTimeFormatter.ofPattern("EEEE-dd")

             LocalDate.parse(this, inputFormat).format(outputFormat)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }
}