package com.example.weatherforecast.ui.mainWeather

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.mainWeather.adapter.WeatherExpanAdapter
import com.example.weatherforecast.ui.mainWeather.model.Weather
import com.example.weatherforecast.ui.mainWeather.model.WeatherDetial
import com.example.weatherforecast.ui.mainWeather.model.WeatherList
import com.example.weatherforecast.uitl.StringExtenion.celToFah
import com.example.weatherforecast.uitl.StringExtenion.dateToDay
import com.example.weatherforecast.uitl.StringExtenion.fahToCal
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureCelsius
import com.example.weatherforecast.uitl.StringExtenion.getCelsiusToFahrenheit
import com.example.weatherforecast.uitl.StringExtenion.getFahrenheitToCelsius
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import kotlinx.android.synthetic.main.card_weath_main.*
import kotlinx.android.synthetic.main.main_weather_fragment.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class MainWeatherFragment : Fragment(), WeatherPresenterContract.ViewWeather {

    private lateinit var presenter: WeatherPresenter
    private lateinit var adapterExpan: WeatherExpanAdapter
    private var adapterWeather: WeatherAdapter? = null
    private var switcherTemp = false
    private lateinit var data: Weather
    private var dataParss: ArrayList<WeatherDetial> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = arguments?.get("weatherDetial") as Weather
        presenter = WeatherPresenter(this)
        setUpAdapter()
    }

    private fun setUpAdapter() {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val currentDateTime = LocalDateTime.now().format(formatter).dateToDay()
        txt_date_now.text = currentDateTime
        presenter.onGroupSetData(data)
        onShowWeather()
    }

    @SuppressLint("SetTextI18n", "UseRequireInsteadOfGet")
    fun onShowWeather() {
        data.apply {
            title_cityname.text = data.city.name
            weath_status.text = data.city.name
            presenter.getDataGroup()[0].let {
                val tempMax = it.main.temp_max.toString().fromatTemperatureCelsius()
                val tempMin = it.main.temp_min.toString().fromatTemperatureCelsius()
                title_temp.text = it.main.temp.toString().fromatTemperatureCelsius()
                title_temp_sup.text = "$tempMax/$tempMin"
                val humidity = it.main.humidity.toString()
                weath_status_sub.text = "Humidity $humidity %"
            }

            presenter.getDataGroup()[0].let { it ->
                weath_status.text = it.weatherX[0].description
                it.weatherX[0].icon.let {
                    Glide.with(this@MainWeatherFragment)
                        .load("http://openweathermap.org/img/wn/$it@2x.png")
                        .into(icon_weather)
                }
            }
        }
        switch_temp.setOnClickListener {
            data.list[0].let { it ->
                val mainTemp: String
                val tempMax: String
                val tempMin: String
                if (switcherTemp) {
                    switcherTemp = !switcherTemp
                    mainTemp = getFahrenheitToCelsius(it.main.temp)
                    tempMax = getFahrenheitToCelsius(it.main.temp_max)
                    tempMin = getFahrenheitToCelsius(it.main.temp_min)
                    tit_temp_f_d.setTextColor(this@MainWeatherFragment.context?.getColor(R.color.colorDetiveItem)!!)
                    tit_temp_c_d.setTextColor(this@MainWeatherFragment.context?.getColor(R.color.colorActiveItem)!!)

                } else {
                    switcherTemp = !switcherTemp
                    mainTemp = getCelsiusToFahrenheit(it.main.temp)
                    tempMax = getCelsiusToFahrenheit(it.main.temp_max)
                    tempMin = getCelsiusToFahrenheit(it.main.temp_min)
                    tit_temp_f_d.setTextColor(this@MainWeatherFragment.context?.getColor(R.color.colorActiveItem)!!)
                    tit_temp_c_d.setTextColor(this@MainWeatherFragment.context?.getColor(R.color.colorDetiveItem)!!)

                }
                adapterExpan.switchWemp()
                title_temp.text = mainTemp
                title_temp_sup.text = "$tempMax/$tempMin"
            }

        }


    }


    override fun onSetDataAdapter(dataExan: ArrayList<WeatherList>) {
        adapterExpan = WeatherExpanAdapter(dataExan)

//        adapterWeather = WeatherAdapter(
//            dataParss.toCollection(
//                mutableListOf()
//            ), this
//        )
        list_future_weather.apply {
            val layoutManagerView: RecyclerView.LayoutManager =
                LinearLayoutManager(this@MainWeatherFragment.context)
            layoutManager = layoutManagerView
            adapter = adapterExpan
        }
    }

}
