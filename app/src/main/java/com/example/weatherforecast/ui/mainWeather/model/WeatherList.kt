package com.example.weatherforecast.ui.mainWeather.model

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class WeatherList (
    var nameTitleDay:String = "",
    var tempMax:Double =0.0,
    var tempMin:Double =0.0,
    var dataChild:ArrayList<WeatherDetial> = arrayListOf()
) : ExpandableGroup<WeatherDetial>(nameTitleDay,dataChild){

    override fun getItems(): MutableList<WeatherDetial> {
        return dataChild
    }

}