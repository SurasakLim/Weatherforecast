package com.example.weatherforecast.ui.mainWeather

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.mainWeather.model.WeatherDetial
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
       holder.bindData(item[position],holder)
    }

    fun setDataChange(list: MutableList<WeatherDetial>) {
        itemFuture = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(weatherDetial: WeatherDetial, holder: ViewHolder
        ) {
            view.apply {
                tit_day.text = weatherDetial.dt_txt
                imageView.loadIconImg(holder.view.rootView.context,weatherDetial.weatherX[0].icon)
            }
        }

    }

    fun ImageView.loadIconImg(context: Context, icon:String){
        Glide.with(context).load("http://openweathermap.org/img/wn/$icon@2x.png").into(this)
    }
}