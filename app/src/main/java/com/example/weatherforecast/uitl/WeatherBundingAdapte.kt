package com.example.weatherforecast.uitl

import android.annotation.SuppressLint
import android.os.Build
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.weatherforecast.uitl.WeatherBundingAdapte.dateToDay
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object WeatherBundingAdapte {

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("android:txtToC")
    fun tempWeatherToC(textView:TextView,data:Double){
        textView.text = "$data \u2103"?:""
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("android:txtToF")
    fun tempWeatherToF(textView:TextView,data:String){
        textView.text = "$data \u2109"?:""
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("android:txtFormatWind")
    fun txtFormatWind(textView:TextView,data:Double){
        textView.text = "$data meter/sec,"?:""
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("android:txtFormatHumidity")
    fun txtFormatHumidity(textView:TextView,data:Double){
        textView.text = "$data %"?:""
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("android:txtFormatSnow")
    fun txtFormatSmow(textView:TextView,data:Double){
        textView.text = "$data volume of last 3 hours"?: ""
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("android:txtFormatDay")
    fun txtFormatDay(textView:TextView,data:String?){
        textView.text = data?.dateToDay()
    }

    private fun String.dateToDay(): String? {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val outputFormat = DateTimeFormatter.ofPattern("EEEE dd")

            LocalDate.parse(this, inputFormat).format(outputFormat)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }
}