package com.example.weatherforecast.ui.mainWeather.model

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import kotlinx.android.parcel.Parcelize

@Parcelize
class WeatherList (
    var nameTitleDay:String = "",
    var tempMax:String ="",
    var tempMin:String ="",
    var isExpland:Boolean = false,
    var dataChild:ArrayList<WeatherDetial> = arrayListOf()
) : ExpandableGroup<WeatherDetial>(nameTitleDay,dataChild)
