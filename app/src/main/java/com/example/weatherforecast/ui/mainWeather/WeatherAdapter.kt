package com.example.weatherforecast.ui.mainWeather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.mainWeather.model.WeatherDetial
import com.example.weatherforecast.uitl.StringExtenion.dateToDay
import kotlinx.android.synthetic.main.future_weather_item.view.*

class WeatherAdapter(var item:MutableList<WeatherDetial>) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private var itemFuture : MutableList<WeatherDetial> = item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mInfater = LayoutInflater.from(parent.context)
        return ViewHolder(mInfater.inflate(R.layout.future_weather_item,parent,false))
    }

    override fun getItemCount(): Int {
        return item.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bindData(item[position])
    }

    fun setDataChange(list: MutableList<WeatherDetial>) {
        itemFuture = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(weatherDetial: WeatherDetial) {
            view.apply {
                tit_day.text = weatherDetial.dt_txt
            }
        }

    }
}