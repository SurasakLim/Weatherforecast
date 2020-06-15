package com.example.weatherforecast.ui.mainWeather

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherforecast.MainActivity
import com.example.weatherforecast.MainApplication

import com.example.weatherforecast.R
import com.example.weatherforecast.di.serviceModule.annotation.Injectable
import com.example.weatherforecast.ui.mainWeather.model.WeathResponse
import com.example.weatherforecast.ui.mainWeather.model.Weather
import com.example.weatherforecast.ui.mainWeather.model.WeatherDetial
import com.example.weatherforecast.uitl.GlidExtenion.loadImage
import com.example.weatherforecast.uitl.StringExtenion.dateToDay
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureCelsius
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.card_weath_main.*
import kotlinx.android.synthetic.main.main_weather_fragment.*
import javax.inject.Inject

class MainWeatherFragment : DaggerFragment(), MainWeatherContract.View {

    private var adapterWeather: WeatherAdapter? = null

    @Inject
    lateinit var viewModelWeath: MainWeatherViewModel

    @Inject
    lateinit var presenter: MainWeatherPresenter
    private val formatC = "\u2013"
    private val formatF = "\u2019"

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onGetWeatherData("London")
    }

    private fun setUpAdapter() {
        var data = viewModelWeath.weather.value?.data?.list
        data?.forEach {
            it.dt_txt = it.dt_txt.dateToDay()!!
        }
        var sIn = ""
        data?.dropLastWhile {
            it.dt_txt == it.dt_txt
        }
        adapterWeather = viewModelWeath.weather.value?.data?.list?.let {
            WeatherAdapter(
                it.toCollection(
                    mutableListOf()
                ).subList(0, 6)
            )
        }
        list_future_weather.apply {
            val layoutManagerView: RecyclerView.LayoutManager =
                LinearLayoutManager(this@MainWeatherFragment.context)
            layoutManager = layoutManagerView
            adapter = adapterWeather
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onShowWeather(data: Weather) {
        setUpAdapter()
        title_cityname.text = viewModelWeath.weather.value?.data?.city?.name
        weath_status.text = viewModelWeath.weather.value?.data?.city?.name
        viewModelWeath.weather.value?.data?.list?.get(0).let { it ->
            it?.main?.let {
                val tempMax = it.temp_max.toString().fromatTemperatureCelsius()
                val tempMin = it.temp_min.toString().fromatTemperatureCelsius()
                title_temp.text = it.temp.toString().fromatTemperatureCelsius()
                title_temp_sup.text = "$tempMax/$tempMin"
            }

            weath_status.text = it?.weatherX?.get(0)?.description
            val humidity = it?.main?.humidity.toString()
            weath_status_sub.text = "Humidity  $humidity %"
            it?.weatherX?.get(0)?.icon?.let {
                Glide.with(this).load("http://openweathermap.org/img/wn/$it@2x.png")
                    .into(icon_weather)
            }
        }

    }

    private fun onAdapterWeather(data: Weather) {
        adapterWeather?.setDataChange(data.list.toCollection(mutableListOf()))
        adapterWeather?.notifyDataSetChanged()
    }

    override fun onErrorWeather(messageError: String) {
//        viewModelWeath.weather.value
//        testData.text = viewModelWeath.weather.value?.errorMessage
    }

}
