package com.example.weatherforecast.ui.mainWeather

import com.example.weatherforecast.ui.mainWeather.model.Weather
import com.example.weatherforecast.ui.mainWeather.model.WeatherDetial
import com.example.weatherforecast.ui.mainWeather.model.WeatherList
import com.example.weatherforecast.uitl.StringExtenion.dateToDay
import java.util.ArrayList

class WeatherPresenter(var view:WeatherPresenterContract.ViewWeather):WeatherPresenterContract.Presenter{
    private var dataParss: ArrayList<WeatherDetial> = arrayListOf()

    override fun onGroupSetData(weath: Weather) {

        dataParss = weath.list.apply {
            this.map {
                it.dt_txt = it.dt_txt.dateToDay()!!
            }
        }.toCollection(arrayListOf())
        dataParss = dataParss.distinctBy { it.dt_txt }.toCollection(arrayListOf())

        val dataExan = arrayListOf<WeatherList>()


        dataParss.forEach {it->
            val head = WeatherList(
                it.dt_txt,
                it.main.temp_max,
                it.main.temp_min,
                weath.list.filter {it2->
                    it.dt_txt == it2.dt_txt
                }.toCollection(arrayListOf())
            )
            dataExan.add(head)
        }
        view.onSetDataAdapter(dataExan)
    }
    fun getDataGroup(): ArrayList<WeatherDetial> = dataParss

}