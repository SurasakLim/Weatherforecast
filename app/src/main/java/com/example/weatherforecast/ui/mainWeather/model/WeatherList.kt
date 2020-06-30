package com.example.weatherforecast.ui.mainWeather.model

import android.os.Parcelable
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import kotlinx.android.parcel.Parcelize

@Parcelize
class WeatherList (
    var nameTitleDay:String = "",
    var tempMax:Double =0.0,
    var tempMin:Double =0.0,
    var position : Int = 0,
    var isExpland:Boolean = false,
    var dataChild:ArrayList<WeatherDetial> = arrayListOf()
) : ExpandableGroup<WeatherDetial>(nameTitleDay,dataChild), Parcelable{

    override fun getItems(): MutableList<WeatherDetial> {
        return dataChild
    }



}