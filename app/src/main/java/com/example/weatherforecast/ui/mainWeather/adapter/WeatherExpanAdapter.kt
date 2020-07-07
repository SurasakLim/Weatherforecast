package com.example.weatherforecast.ui.mainWeather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.mainWeather.model.WeatherList
import com.example.weatherforecast.uitl.StringExtenion.celToFah
import com.example.weatherforecast.uitl.StringExtenion.fahToCal
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureCelsius
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureFahrenheit
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

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



    override fun onCreateChildViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): WeatherChildViewHolder {
        val mInfater = LayoutInflater.from(parent?.context)
        return WeatherChildViewHolder(mInfater.inflate(R.layout.future_weather_item_child,parent,false))
    }

    override fun getItemCount(): Int {
        return listExapnd.size
    }

    override fun onBindChildViewHolder(
        holder: WeatherChildViewHolder?,
        flatPosition: Int,
        group: ExpandableGroup<*>?,
        childIndex: Int
    ) {
        val childItem = (group as WeatherList).items[childIndex]
        holder?.onBind(childItem)
    }



    fun ImageView.loadIconImg(context: Context, icon:String){
        Glide.with(context).load("http://openweathermap.org/img/wn/$icon@2x.png").into(this)
    }

    fun switchWemp(){

        if (switcherTemp) {
            switcherTemp = !switcherTemp
            listExapnd.map {
                it.tempMax = it.tempMax.fahToCal()
                it.tempMin = it.tempMin.fahToCal()
            }

        } else {
            switcherTemp = !switcherTemp
            listExapnd.map {
                it.tempMax = it.tempMax.celToFah()
                it.tempMin = it.tempMin.celToFah()
            }
        }
        notifyDataSetChanged()
    }

    override fun onBindGroupViewHolder(
        holder: WeatherGroupViewHodler?,
        flatPosition: Int,
        group: ExpandableGroup<*>?
    ) {
        val head = listExapnd[flatPosition]
        holder?.apply {
            titDay.text = head.nameTitleDay
            if(switcherTemp){
                titTempHead.text = head.tempMax.toString().fromatTemperatureFahrenheit() +"/"+  head.tempMin.toString().fromatTemperatureFahrenheit()
            } else {
                titTempHead.text = head.tempMax.toString().fromatTemperatureCelsius() +"/"+  head.tempMin.toString().fromatTemperatureCelsius()
            }
        }
    }


}
