package com.example.weatherforecast.uitl

import android.os.Build
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.abs

object StringExtenion {

    fun String.fromatTemperatureCelsius(): String {
        val decimalFormat = DecimalFormat().apply {
            applyPattern("###.#")
        }
        return decimalFormat.format(this.toDouble()) + "\u2103"
    }

    fun String.fromatTemperatureFahrenheit(): String {
        val decimalFormat = DecimalFormat().apply {
            applyPattern("###.#")
        }
        return decimalFormat.format(this.toDouble()) + "\u2109"
    }



    fun Double.celToFah() = abs(((this - 32) * 5) / 9)
    fun Double.fahToCal() = ((this * 9) / 5) + 32

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