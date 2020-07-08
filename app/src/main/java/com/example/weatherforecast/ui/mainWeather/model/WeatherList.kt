package com.example.weatherforecast.ui.mainWeather.model

import android.os.Parcelable
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import kotlinx.android.parcel.Parcelize

class WeatherList (
    var nameTitleDay:String,
    var tempMax:Double,
    var tempMin:Double,
    var dataChild:ArrayList<WeatherDetial>
) : ExpandableGroup<WeatherDetial>(nameTitleDay,dataChild), Parcelable{

    override fun getItems(): ArrayList<WeatherDetial> {
        return dataChild
    }



}