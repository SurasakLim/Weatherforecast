package com.example.weatherforecast.uitl

import android.annotation.SuppressLint
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
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

    fun getCelsiusToFahrenheit(data: Double?): String = data?.celToFah().toString().fromatTemperatureFahrenheit()
    fun getFahrenheitToCelsius(data: Double?): String = data?.fahToCal().toString().fromatTemperatureCelsius()

    fun EditText.onDone(callback: (v : View) -> Unit) {
        // These lines optional if you don't want to set in Xml
        imeOptions = EditorInfo.IME_ACTION_DONE
        maxLines = 1
        setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                callback.invoke(v)
                true
            }
            false
        }
    }

    fun Double.celToFah() = abs( (9/5.0 * this) + 32)
    fun Double.fahToCal() = 5/9.0 * (this - 32)

    fun String.dateToDay(): String? {

        return try {
            val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val outputFormat = DateTimeFormatter.ofPattern("EEEE dd")
            LocalDate.parse(this, inputFormat).format(outputFormat)
        } catch (e :Exception){
            this
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun String.toDateString(): String {
        return try {
            val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val outputFormat = DateTimeFormatter.ofPattern("HH:mm:ss")
            LocalDateTime.parse(this, inputFormat).format(outputFormat)
        } catch (e :Exception){
            this
        }
    }
}