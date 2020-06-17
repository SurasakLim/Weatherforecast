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
    @SuppressLint("SetTextI18n")
    fun bind(it: ExpandableGroup<*>) {

        val titDay = view.findViewById<TextView>(R.id.tit_day)
        val titTempHead = view.findViewById<TextView>(R.id.txt_temp_head)

        if(it is WeatherList){
            view.apply {
                titDay.text = it.nameTitleDay
                titTempHead.text = it.tempMax + it.tempMin
            }
        }
    }


}
