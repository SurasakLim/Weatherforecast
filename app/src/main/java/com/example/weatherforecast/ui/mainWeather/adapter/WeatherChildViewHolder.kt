package com.example.weatherforecast.ui.mainWeather.adapter

import android.os.Parcelable
import android.view.View
import com.example.weatherforecast.ui.mainWeather.model.WeatherDetial
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import kotlinx.android.synthetic.main.future_weather_item_child.view.*

class WeatherChildViewHolder(var view: View):ChildViewHolder(view) {
    fun bind(weatherDetial: WeatherDetial) {
        view.apply {
            tit_day_child.text = weatherDetial.dt_txt
        }
    }


}
