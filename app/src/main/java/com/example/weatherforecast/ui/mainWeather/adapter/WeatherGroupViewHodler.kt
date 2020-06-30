package com.example.weatherforecast.ui.mainWeather.adapter

import android.view.View
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import kotlinx.android.synthetic.main.future_weather_item.view.*


class WeatherGroupViewHodler(var view: View): GroupViewHolder(view) {

    val titDay: TextView = view.tit_day
    val titTempHead: TextView = view.txt_temp_head
    private val imgHead: ImageView = view.img_head

    override fun expand() {
        animateExpand()
    }

    override fun collapse() {
        animateCollapse()
    }

    private fun animateExpand() {
        val rotate = RotateAnimation(
            360F,
            180F,
            RELATIVE_TO_SELF,
            0.5f,
            RELATIVE_TO_SELF,
            0.5f
        )
        rotate.duration = 300
        rotate.fillAfter = true
        imgHead.animation = rotate
    }

    private fun animateCollapse() {
        val rotate = RotateAnimation(
            180F,
            360F,
            RELATIVE_TO_SELF,
            0.5f,
            RELATIVE_TO_SELF,
            0.5f
        )
        rotate.duration = 300
        rotate.fillAfter = true
        imgHead.animation = rotate
    }

}
