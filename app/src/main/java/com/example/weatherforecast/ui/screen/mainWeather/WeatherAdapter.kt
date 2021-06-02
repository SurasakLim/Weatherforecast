package com.example.weatherforecast.ui.screen.mainWeather

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.screen.mainWeather.model.WeatherDetial
import com.example.weatherforecast.uitl.StringExtenion.toDateString
import kotlinx.android.synthetic.main.item_current_feature.view.*

class WeatherAdapter(var item: List<WeatherDetial>, viewType: Int) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    private var viewTypeItem = viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mInfater = LayoutInflater.from(parent.context)
        return ViewHolder(mInfater.inflate(R.layout.item_current_feature, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return viewTypeItem
    }

    override fun getItemCount(): Int {
        return item.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(item[position], holder)
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bindData(weatherDetial: WeatherDetial, holder: ViewHolder) {
            view.apply {
                txt_fu_date.text =
                    "${weatherDetial.dtFromat}\n${weatherDetial.dtTxt.toDateString()}"
                txt_temp.text =
                    "${weatherDetial.tempMaxFormate}/${weatherDetial.tempMinFormate}"
                img_status.loadIconImg(holder.view.rootView.context, weatherDetial.weatherX[0].icon)
            }
        }
    }

    fun ImageView.loadIconImg(context: Context, icon: String) {
        Glide.with(context)
            .load("http://openweathermap.org/img/wn/$icon@2x.png")
            .placeholder(context.getDrawable(R.drawable.ic_sun_main))
            .into(this)
    }
}
