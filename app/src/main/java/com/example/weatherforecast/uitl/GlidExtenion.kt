package com.example.weatherforecast.uitl

import android.widget.ImageView
import com.bumptech.glide.Glide

object GlidExtenion {

    fun ImageView.loadImage(weatherImg: String) {
        Glide.with(this).load(" http://openweathermap.org/img/wn/$weatherImg@2x.png").into(this);
    }
}