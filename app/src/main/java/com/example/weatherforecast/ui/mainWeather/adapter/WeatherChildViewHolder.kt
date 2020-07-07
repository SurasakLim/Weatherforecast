package com.example.weatherforecast.ui.mainWeather.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.weatherforecast.ui.mainWeather.model.WeatherDetial
import com.example.weatherforecast.uitl.ImageExtension.loadImage
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import kotlinx.android.synthetic.main.future_weather_item_child.view.*

class WeatherChildViewHolder(var view: View):ChildViewHolder(view) {
    private val imgWeather: ImageView? = view.img_status_child
    private val dayChild: TextView? = view.tit_day_child
    private val tempChild: TextView? = view.txt_temp_child

    fun onBind(childItem: WeatherDetial) {
        view.rootView?.context?.let { imgWeather?.loadImage(childItem.weatherX[0].icon) }
        dayChild?.text = childItem.dt_txt
        tempChild?.text = childItem.main.temp.toString()
    }




}
