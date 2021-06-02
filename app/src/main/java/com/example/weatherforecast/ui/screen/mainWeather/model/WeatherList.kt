package com.example.weatherforecast.ui.screen.mainWeather.model

import android.os.Parcelable
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class WeatherList(
    var nameTitleDay: String,
    var tempMax: Double,
    var tempMin: Double,
    var dataChild: ArrayList<WeatherDetial>
) : ExpandableGroup<WeatherDetial>(nameTitleDay, dataChild), Parcelable {

    override fun getItems(): ArrayList<WeatherDetial> {
        return dataChild
    }
}
