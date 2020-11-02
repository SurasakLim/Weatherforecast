package com.example.weatherforecast.ui.mainWeather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforecast.ui.mainWeather.model.Weather
import com.example.weatherforecast.ui.mainWeather.model.WeatherDetial
import com.example.weatherforecast.ui.mainWeather.model.WeatherList
import com.example.weatherforecast.uitl.StringExtenion.dateToDay
import java.util.ArrayList

class WeatherPresenter(var view:WeatherPresenterContract.ViewWeather):WeatherPresenterContract.Presenter,ViewModel(){
    private var dataParssDetail = MutableLiveData<MutableList<WeatherDetial>>()

    override fun onGroupSetData(weath: Weather) {

        dataParssDetail.value = weath.list.apply {
            this.map {
                it.dt_txt = it.dt_txt.dateToDay()!!
            }
        }.toCollection(arrayListOf()).distinctBy {
            it.dt_txt
        }.toCollection(mutableListOf())

        val dataExan = arrayListOf<WeatherList>()

        dataParssDetail.value!!.forEach { it->
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

    fun getDataGroup(): MutableLiveData<MutableList<WeatherDetial>> = dataParssDetail

}