package com.example.weatherforecast.ui.screen.weathercurrent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherforecast.ui.base.BaseViewModel
import com.example.weatherforecast.ui.screen.mainWeather.model.Weather
import com.example.weatherforecast.ui.screen.mainWeather.model.WeatherList
import com.example.weatherforecast.uitl.StringExtenion.dateToDay
import javax.inject.Inject

abstract class WeatherCurrentViewModel : BaseViewModel() {
    abstract val input: Input
    abstract val dataPassDetail: LiveData<List<WeatherList>>
}

interface Input {
    fun onGroupSetData(weath: Weather)
}

class WeatherCurrentViewModelImpl @Inject constructor() : WeatherCurrentViewModel(), Input {
    override val input: Input
        get() = this

    private var _dataPassDetail = MutableLiveData<List<WeatherList>>()
    override val dataPassDetail: LiveData<List<WeatherList>>
        get() = _dataPassDetail

    override fun onGroupSetData(weath: Weather) {
        weath.list.apply {
            this.map {
                it.dtTxt = it.dtTxt.dateToDay()!!
            }
        }.distinctBy {
            it.dtTxt
        }.run {
            val dataExan = arrayListOf<WeatherList>()
            forEach { it ->
                val head = WeatherList(
                    it.dtTxt,
                    it.tempMax,
                    it.tempMin,
                    weath.list.filter { it2 ->
                        it.dtTxt == it2.dtTxt
                    }.toCollection(arrayListOf())
                )
                dataExan.add(head)
                setDataPassDetail(dataExan)
            }
        }
    }

    private fun setDataPassDetail(data: ArrayList<WeatherList>) {
        _dataPassDetail.value = data
    }
}
