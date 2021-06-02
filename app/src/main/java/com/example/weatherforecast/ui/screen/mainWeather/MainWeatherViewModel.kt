package com.example.weatherforecast.ui.screen.mainWeather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherforecast.ui.base.BaseViewModel
import com.example.weatherforecast.ui.screen.mainWeather.model.Weather
import com.example.weatherforecast.ui.screen.mainWeather.model.toWeatherDetailTempC
import com.example.weatherforecast.ui.screen.mainWeather.model.toWeatherDetailTempF
import com.example.weatherforecast.uitl.StringExtenion.celToFah
import com.example.weatherforecast.uitl.StringExtenion.fahToCal
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureCelsius
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureFahrenheit
import com.example.weatherforecast.usecase.WeatherSingleUseCase
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import java.time.format.DateTimeFormatter
import javax.inject.Inject

abstract class MainWeatherViewModel : BaseViewModel() {
    abstract val weather: LiveData<Weather>
    abstract val input: Input
    abstract val showLoading: Observable<Boolean>
    abstract val untiTemporalChange: LiveData<Boolean>
    abstract fun changeUntiTemporalChange()
}

interface Input {
    fun getWeatherCountry(countryId: String)
}

class MainWeatherViewModelImpl @Inject constructor(
    private val getWeatherSingleUseCase: WeatherSingleUseCase
) : MainWeatherViewModel(), Input {

    private val _weather = MutableLiveData<Weather>()
    override val weather: LiveData<Weather>
        get() = _weather

    override val input: Input
        get() = this

    private val _showLoading = BehaviorSubject.create<Boolean>()
    override val showLoading: Observable<Boolean>
        get() = _showLoading

    private val _untiTemporalChange = MutableLiveData(false)
    override val untiTemporalChange: LiveData<Boolean>
        get() = _untiTemporalChange

    override fun getWeatherCountry(countryId: String) {
        getWeatherSingleUseCase.execute(countryId)
            .doOnSuccess { _showLoading.onNext(true) }
            .doFinally { _showLoading.onNext(false) }
            .subscribeBy { it ->
                it.list.distinctBy {
                    it.dtTxt
                }
                it.list.map {
                    it.tempMaxFormate = it.tempMax.toString().fromatTemperatureCelsius()
                    it.tempMinFormate = it.tempMin.toString().fromatTemperatureCelsius()
                }
                setWeatherData(it)
            }.addToDisposables()
    }

    override fun changeUntiTemporalChange() {
        setUntiTemporal(!_untiTemporalChange.value!!)
        if (_untiTemporalChange.value!!) {
            with(_weather.value) {
                this?.tempType = "F"
                this?.tempMain = this?.tempMain?.celToFah()
                this?.tempFormate =
                    _weather.value?.tempMain.toString().fromatTemperatureFahrenheit()
                this?.list?.toWeatherDetailTempF()
            }
        } else {
            with(_weather.value) {
                this?.tempType = "C"
                this?.tempMain = this?.tempMain?.fahToCal()
                this?.tempFormate = _weather.value?.tempMain.toString().fromatTemperatureCelsius()
                this?.list?.toWeatherDetailTempC()
            }
        }
        setWeatherData(_weather.value)
    }

    private fun setUntiTemporal(onChange: Boolean) {
        _untiTemporalChange.value = onChange
    }

    private fun setWeatherData(weather: Weather?) {
        _weather.value = weather
    }

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
}
