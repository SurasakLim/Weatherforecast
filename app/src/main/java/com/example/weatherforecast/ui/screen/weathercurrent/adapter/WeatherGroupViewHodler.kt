package com.example.weatherforecast.ui.screen.weathercurrent.adapter

import android.view.View
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import kotlinx.android.synthetic.main.future_weather_item.view.*

class WeatherGroupViewHodler(var view: View) : GroupViewHolder(view) {

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
            DEGREES_START,
            DEGREES_END,
            RELATIVE_TO_SELF,
            PIVOT_X,
            RELATIVE_TO_SELF,
            PIVOT_Y
        )
        rotate.duration = DURATION
        rotate.fillAfter = true
        imgHead.animation = rotate
    }

    private fun animateCollapse() {
        val rotate = RotateAnimation(
            DEGREES_START,
            DEGREES_END,
            RELATIVE_TO_SELF,
            PIVOT_X,
            RELATIVE_TO_SELF,
            PIVOT_Y
        )
        rotate.duration = DURATION
        rotate.fillAfter = true
        imgHead.animation = rotate
    }
}

private const val DEGREES_START = 360F
private const val DEGREES_END = 180F
private const val PIVOT_X = 0.5f
private const val PIVOT_Y = 0.5f
private const val DURATION: Long = 300
