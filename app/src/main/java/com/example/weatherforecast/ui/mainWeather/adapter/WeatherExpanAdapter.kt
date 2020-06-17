package com.example.weatherforecast.ui.mainWeather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.mainWeather.model.WeatherDetial
import com.example.weatherforecast.ui.mainWeather.model.WeatherList
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class WeatherExpanAdapter(listEx:ArrayList<WeatherList>) :
    ExpandableRecyclerViewAdapter<WeatherGroupViewHodler, WeatherChildViewHolder>(listEx) {

    override fun onCreateGroupViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): WeatherGroupViewHodler {
        val mInfater = LayoutInflater.from(parent?.context)
        return WeatherGroupViewHodler(mInfater.inflate(R.layout.future_weather_item,parent,false))
    }

    override fun onCreateChildViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): WeatherChildViewHolder {
        val mInfater = LayoutInflater.from(parent?.context)
        return WeatherChildViewHolder(mInfater.inflate(R.layout.future_weather_item_child,parent,false))
    }

    override fun onBindChildViewHolder(
        holder: WeatherChildViewHolder?,
        flatPosition: Int,
        group: ExpandableGroup<*>?,
        childIndex: Int
    ) {
        holder?.bind(group?.items?.get(childIndex) as WeatherDetial)
    }

    override fun onBindGroupViewHolder(
        holder: WeatherGroupViewHodler?,
        flatPosition: Int,
        group: ExpandableGroup<*>?
    ) {
        group?.let { holder?.bind(it) }
    }

}
