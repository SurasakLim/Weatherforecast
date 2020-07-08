package com.example.weatherforecast.ui.mainWeather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.mainWeather.model.WeatherList
import com.example.weatherforecast.uitl.StringExtenion.celToFah
import com.example.weatherforecast.uitl.StringExtenion.fahToCal
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureCelsius
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureFahrenheit
import com.example.weatherforecast.uitl.StringExtenion.getCelsiusToFahrenheit
import com.example.weatherforecast.uitl.StringExtenion.getFahrenheitToCelsius
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class WeatherExpanAdapter(listEx: ArrayList<WeatherList>) :
    ExpandableRecyclerViewAdapter<WeatherGroupViewHodler, WeatherChildViewHolder>(listEx) {

    private var defalteTemp: Boolean = true
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
        holder?.onBind(childItem,switcherTemp)
    }

    fun switchWemp() {
        defalteTemp = false
        switcherTemp = !switcherTemp
        notifyDataSetChanged()
    }

    override fun onBindGroupViewHolder(
        holder: WeatherGroupViewHodler?,
        flatPosition: Int,
        group: ExpandableGroup<*>?
    ) {
        val head = (group as WeatherList)
        holder?.apply {
            titDay.text = head.nameTitleDay
            val max = head.tempMax
            val min = head.tempMin
            if(!defalteTemp){
                if (switcherTemp) {
                    titTempHead.text = getFahrenheitToCelsius(max)+ "/" + getFahrenheitToCelsius(min)
                } else {
                    titTempHead.text = getCelsiusToFahrenheit(max)+ "/" + getCelsiusToFahrenheit(min)
                }
            } else {
                titTempHead.text ="${max.toString().fromatTemperatureCelsius()} / ${min.toString().fromatTemperatureCelsius()}"
            }

        }
    }


}
