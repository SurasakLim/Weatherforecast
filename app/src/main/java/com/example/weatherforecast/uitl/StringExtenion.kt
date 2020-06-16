package com.example.weatherforecast.uitl

import android.os.Build
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
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