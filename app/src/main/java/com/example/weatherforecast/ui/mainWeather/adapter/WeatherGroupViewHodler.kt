package com.example.weatherforecast.ui.mainWeather.adapter

import android.annotation.SuppressLint
import android.os.Parcelable
import android.view.View
import android.widget.TextView
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.mainWeather.model.WeatherList
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import kotlinx.android.synthetic.main.future_weather_item.view.*

class WeatherGroupViewHodler(var view: View): GroupViewHolder(view) {

    val titDay = view.tit_day
    val titTempHead = view.txt_temp_head

}
