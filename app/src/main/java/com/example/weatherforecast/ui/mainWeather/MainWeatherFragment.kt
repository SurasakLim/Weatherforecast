package com.example.weatherforecast.ui.mainWeather

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.mainWeather.model.Weather
import com.example.weatherforecast.ui.mainWeather.model.WeatherDetial
import com.example.weatherforecast.uitl.StringExtenion.celToFah
import com.example.weatherforecast.uitl.StringExtenion.dateToDay
import com.example.weatherforecast.uitl.StringExtenion.fahToCal
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureCelsius
import com.example.weatherforecast.uitl.StringExtenion.getCelsiusToFahrenheit
import com.example.weatherforecast.uitl.StringExtenion.getFahrenheitToCelsius
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.card_weath_main.*
import kotlinx.android.synthetic.main.main_weather_fragment.*
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

class MainWeatherFragment : Fragment() ,MainWeatherContract.ListnerNavigate,
MainWeatherContract.ViewController {

    private var adapterWeather: WeatherAdapter? = null
    private var switcherTemp = false
    private lateinit var data: Weather
    private var dataParss : ArrayList<WeatherDetial> = arrayListOf()
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
        data = arguments?.get("weatherDetial") as Weather
        setUpAdapter()
        onShowWeather()
    }

    private fun setUpAdapter() {

        val currentDateTime = LocalDateTime.now().toString().dateToDay()
        txt_date_now.text = currentDateTime
        dataParss = data.list.apply {
            this.map {
                it.dt_txt = it.dt_txt.dateToDay()!!
            }
        }.toCollection(arrayListOf())

        dataParss.distinctBy{it.dt_txt}
        adapterWeather = WeatherAdapter(
            dataParss.toCollection(
                mutableListOf()
            ),this
        )
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

    @SuppressLint("SetTextI18n")
    fun onShowWeather() {
        data.apply {
            title_cityname.text = data.city.name
            weath_status.text = data.city.name
            data.list[0].let {
                val tempMax = it.main.temp_max.toString().fromatTemperatureCelsius()
                val tempMin = it.main.temp_min.toString().fromatTemperatureCelsius()
                title_temp.text = it.main.temp.toString().fromatTemperatureCelsius()
                title_temp_sup.text = "$tempMax/$tempMin"
                val humidity = it.main.humidity.toString()
                weath_status_sub.text = "Humidity $humidity %"
            }

            data.list[0].let { it ->
                weath_status.text = it.weatherX[0].description
                it.weatherX[0].icon.let {
                    Glide.with(this@MainWeatherFragment).load("http://openweathermap.org/img/wn/$it@2x.png")
                        .into(icon_weather)
                }
            }
        }
        switch_temp.setOnClickListener {
            data.list[0].let { it ->
                var mainTemp = ""
                var tempMax = ""
                var tempMin = ""
                if(switcherTemp){
                    switcherTemp = !switcherTemp
                    mainTemp = getCelsiusToFahrenheit(it.main.temp)
                    tempMax = getCelsiusToFahrenheit(it.main.temp_max)
                    tempMin = getCelsiusToFahrenheit(it.main.temp_min)
                    dataParss.map {
                        it.main.temp = it.main.temp.celToFah()
                    }
                } else {
                    switcherTemp = !switcherTemp
                    mainTemp = getFahrenheitToCelsius(it.main.temp)
                    tempMax = getFahrenheitToCelsius(it.main.temp_max)
                    tempMin = getFahrenheitToCelsius(it.main.temp_min)
                    dataParss.map {
                        it.main.temp = it.main.temp.fahToCal()
                    }
                }
                adapterWeather?.apply {
                    setDataChange(dataParss)
                    notifyDataSetChanged()
                }

                title_temp.text = mainTemp
                title_temp_sup.text = "$tempMax/$tempMin"
            }

        }


    }

    private fun onAdapterWeather(data: Weather) {
        adapterWeather?.setDataChange(data.list.toCollection(mutableListOf()))
        adapterWeather?.notifyDataSetChanged()
    }

    fun onErrorWeather(message: String) {
    }

    override fun onNavigateView(weatherDetial: WeatherDetial) {

    }

    override fun onBackStack() {
    }

}
