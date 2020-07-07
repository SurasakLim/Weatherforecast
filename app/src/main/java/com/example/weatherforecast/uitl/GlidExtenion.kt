package com.example.weatherforecast.uitl

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.mainWeather.model.Weather
import com.example.weatherforecast.ui.mainWeather.model.WeatherStatusMain
import kotlinx.android.synthetic.main.fragment_wearter_detail.*

object ImageExtension {

    fun ImageView.loadImage(weatherImg: String) {
        Glide.with(this).load(" http://openweathermap.org/img/wn/$weatherImg@2x.png").into(this)
    }

    fun View.setBackGround(data: Weather){
        val bgStatus: Drawable
        when (data.list[0].weatherX[0].main) {
            WeatherStatusMain.Clear.names -> {
                bgStatus = context?.getDrawable(R.drawable.bg_clear_sky)!!
            }
            WeatherStatusMain.Clouds.names -> {
                bgStatus = context?.getDrawable(R.drawable.bg_cloud)!!
            }
            WeatherStatusMain.Rain.names -> {
                bgStatus = context?.getDrawable(R.drawable.bg_rain)!!
            }
            WeatherStatusMain.Snow.names -> {
                bgStatus = context?.getDrawable(R.drawable.bg_snow)!!
            }
            else -> {
                bgStatus = context?.getDrawable(R.drawable.bg_clear_sky)!!
            }
        }
        this.background = bgStatus
    }
}