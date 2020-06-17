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
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureCelsius
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureFahrenheit
import kotlinx.android.synthetic.main.future_weather_item.view.*

class WeatherAdapter(var item:MutableList<WeatherDetial>,var navigate: MainWeatherContract.ListnerNavigate) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private var itemFuture : MutableList<WeatherDetial> = item
    private var switcherTemp :Boolean = false

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

    fun setDataChange(
        list: MutableList<WeatherDetial>,
        switcherTemp: Boolean
    ) {
        itemFuture = list
        this.switcherTemp = switcherTemp
        notifyDataSetChanged()
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(weatherDetial: WeatherDetial, holder: ViewHolder
        ) {
            view.apply {
                tit_day.text = weatherDetial.dt_txt
                var tempMax =""
                var tempMin = ""
                if(switcherTemp){
                     tempMax = weatherDetial.main.temp_max.toString().fromatTemperatureFahrenheit()
                     tempMin = weatherDetial.main.temp_min.toString().fromatTemperatureFahrenheit()
                } else {
                     tempMax = weatherDetial.main.temp_max.toString().fromatTemperatureCelsius()
                     tempMin = weatherDetial.main.temp_min.toString().fromatTemperatureCelsius()
                }

                txt_temp_head.text = "$tempMax/$tempMin"
                imageView.loadIconImg(holder.view.rootView.context,weatherDetial.weatherX[0].icon)
                current_weather_select.setOnClickListener {
                    navigate.onNavigateView(weatherDetial)
                }
            }
        }

    }

    fun ImageView.loadIconImg(context: Context, icon:String){
        Glide.with(context).load("http://openweathermap.org/img/wn/$icon@2x.png").into(this)
    }
}