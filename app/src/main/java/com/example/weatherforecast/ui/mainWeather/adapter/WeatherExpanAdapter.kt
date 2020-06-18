package com.example.weatherforecast.ui.mainWeather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.mainWeather.model.WeatherList
import com.example.weatherforecast.uitl.StringExtenion.getCelsiusToFahrenheit
import com.example.weatherforecast.uitl.StringExtenion.getFahrenheitToCelsius
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import kotlin.math.abs

class WeatherExpanAdapter(listEx:ArrayList<WeatherList>) :
    ExpandableRecyclerViewAdapter<WeatherGroupViewHodler, WeatherChildViewHolder>(listEx) {

    private var switcherTemp:Boolean = false
    private var listExapnd : ArrayList<WeatherList> = listEx

    override fun onCreateGroupViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): WeatherGroupViewHodler {
        val mInfater = LayoutInflater.from(parent?.context)
        return WeatherGroupViewHodler(mInfater.inflate(R.layout.future_weather_item,parent,false))
    }

    override fun getItemCount(): Int {
        return listExapnd.size
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
        val childItem = listExapnd[flatPosition].dataChild[childIndex]
        holder?.apply {
            holder.view.rootView?.context?.let { imgWeather?.loadIconImg(it,childItem.weatherX[0].icon) }
            dayChild?.text = childItem.dt_txt
            tempChild?.text = childItem.main.temp.toString()
        }
    }


    override fun onBindGroupViewHolder(
        holder: WeatherGroupViewHodler?,
        flatPosition: Int,
        group: ExpandableGroup<*>?
    ) {
        val head = listExapnd[flatPosition]
        holder?.apply {
            titDay?.text = head.nameTitleDay
            titTempHead?.text = head.tempMax + head.tempMin
        }
    }
    fun ImageView.loadIconImg(context: Context, icon:String){
        Glide.with(context).load("http://openweathermap.org/img/wn/$icon@2x.png").into(this)
    }

    fun switchWemp(){
        val mainTemp: String
        val tempMax: String
        val tempMin: String
        if (switcherTemp) {
            switcherTemp = !switcherTemp
            listExapnd.map {
                it.tempMax = getFahrenheitToCelsius(it.tempMax.toDouble())
                it.tempMin = getFahrenheitToCelsius(it.tempMin.toDouble())
            }

        } else {
            switcherTemp = !switcherTemp
            listExapnd.map {
                it.tempMax = getCelsiusToFahrenheit(it.tempMax.toDouble())
                it.tempMin = getCelsiusToFahrenheit(it.tempMin.toDouble())
            }
        }
        notifyDataSetChanged()
    }
}
