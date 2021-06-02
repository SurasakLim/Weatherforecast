package com.example.weatherforecast.ui.screen.weathercurrent.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.screen.mainWeather.model.WeatherList
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureCelsius
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class WeatherExpanAdapter(listEx: List<WeatherList>) :
    ExpandableRecyclerViewAdapter<WeatherGroupViewHodler, WeatherChildViewHolder>(listEx) {

    private var switcherTemp: Boolean = false

    override fun onCreateGroupViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): WeatherGroupViewHodler {
        val mInfater = LayoutInflater.from(parent?.context)
        return WeatherGroupViewHodler(mInfater.inflate(R.layout.future_weather_item, parent, false))
    }

    override fun onCreateChildViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): WeatherChildViewHolder {
        val mInfater = LayoutInflater.from(parent?.context)
        return WeatherChildViewHolder(
            mInfater.inflate(
                R.layout.future_weather_item_child,
                parent,
                false
            )
        )
    }

    override fun onBindChildViewHolder(
        holder: WeatherChildViewHolder?,
        flatPosition: Int,
        group: ExpandableGroup<*>?,
        childIndex: Int
    ) {
        val childItem = (group as WeatherList).items[childIndex]
        holder?.onBind(childItem, switcherTemp)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindGroupViewHolder(
        holder: WeatherGroupViewHodler?,
        flatPosition: Int,
        group: ExpandableGroup<*>?
    ) {
        if (group is WeatherList) {
            holder?.run {
                titDay.text = group.nameTitleDay
                val max = group.tempMax
                val min = group.tempMin
                titTempHead.text = "${max.toString().fromatTemperatureCelsius()} / ${
                    min.toString().fromatTemperatureCelsius()
                }"
            }
        }
    }
}
