package com.example.weatherforecast.ui.mainWeather.adapter

import android.os.Parcelable
import android.view.View
import com.example.weatherforecast.ui.mainWeather.model.WeatherDetial
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import kotlinx.android.synthetic.main.future_weather_item_child.view.*

class WeatherChildViewHolder(var view: View):ChildViewHolder(view) {
    val imgWeather = view.img_status_child
    val dayChild = view.tit_day_child
    val tempChild = view.txt_temp_child


}
