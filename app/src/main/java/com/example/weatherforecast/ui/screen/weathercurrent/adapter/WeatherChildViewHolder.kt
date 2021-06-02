package com.example.weatherforecast.ui.screen.weathercurrent.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.weatherforecast.ui.screen.mainWeather.model.WeatherDetial
import com.example.weatherforecast.uitl.ImageExtension.loadImage
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureCelsius
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureFahrenheit
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import kotlinx.android.synthetic.main.future_weather_item_child.view.*

class WeatherChildViewHolder(var view: View) : ChildViewHolder(view) {
    private val imgWeather: ImageView? = view.img_status_child
    private val dayChild: TextView? = view.tit_day_child
    private val tempChild: TextView? = view.txt_temp_child

    fun onBind(
        childItem: WeatherDetial,
        switcherTemp: Boolean
    ) {
        view.rootView?.context?.let {
            imgWeather?.loadImage(view.rootView.context, childItem.weatherX[0].icon)
        }
        dayChild?.text = childItem.dtTxt
        tempChild?.text = childItem.temp.toString()
        if (switcherTemp) {
            tempChild?.text = childItem.temp.toString()
                .fromatTemperatureFahrenheit()
        } else {
            tempChild?.text = childItem.temp.toString()
                .fromatTemperatureCelsius()
        }
    }
}
