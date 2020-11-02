package com.example.weatherforecast.ui.mainWeather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import com.bumptech.glide.Glide
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.mainWeather.adapter.WeatherExpanAdapter
import com.example.weatherforecast.ui.mainWeather.model.Weather
import com.example.weatherforecast.ui.mainWeather.model.WeatherDetial
import com.example.weatherforecast.ui.mainWeather.model.WeatherList
import com.example.weatherforecast.uitl.ImageExtension.setBackGround
import com.example.weatherforecast.uitl.StringExtenion.celToFah
import com.example.weatherforecast.uitl.StringExtenion.dateToDay
import com.example.weatherforecast.uitl.StringExtenion.fahToCal
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureCelsius
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureFahrenheit
import com.example.weatherforecast.uitl.StringExtenion.getCelsiusToFahrenheit
import com.example.weatherforecast.uitl.StringExtenion.getFahrenheitToCelsius
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.card_weath_main.*
import kotlinx.android.synthetic.main.main_weather_fragment.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


class MainWeatherFragment : DaggerFragment(), WeatherPresenterContract.ViewWeather {

    private lateinit var presenter: WeatherPresenter
    private lateinit var adapterExpan: WeatherExpanAdapter
    private var switcherTemp = false
    private lateinit var data: Weather
    private var dataParss: ArrayList<WeatherDetial> = arrayListOf()

    @Inject
    lateinit var viewModel: MainWeatherViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = WeatherPresenter(this)
        setUpAdapter()
    }

    private fun setUpAdapter() {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val currentDateTime = LocalDateTime.now().format(formatter).dateToDay()
        txt_date_now.text = currentDateTime
        viewModel.weather.value?.data?.let { presenter.onGroupSetData(it) }
        bg_detail_main.setBackGround(viewModel.weather.value?.data!!)
        onShowWeather()
    }

    @SuppressLint("SetTextI18n", "UseRequireInsteadOfGet")
    fun onShowWeather() {
        viewModel.weather.value?.apply {
            title_cityname.text = data.city.name
            weath_status.text = data.city.name
        }

        presenter.getDataGroup().value?.get(0)?.let {
            val tempMax = it.main.temp_max.toString().fromatTemperatureCelsius()
            val tempMin = it.main.temp_min.toString().fromatTemperatureCelsius()
            title_temp.text = it.main.temp.toString().fromatTemperatureCelsius()
            title_temp_sup.text = "$tempMax/$tempMin"
            val humidity = it.main.humidity.toString()
            weath_status_sub.text = "Humidity $humidity %"
            weath_status.text = it.weatherX[0].description
            it.weatherX[0].icon.let {
                Glide.with(this@MainWeatherFragment)
                    .load("http://openweathermap.org/img/wn/$it@2x.png")
                    .into(icon_weather)
            }
        }

        switch_temp.setOnClickListener {
            presenter.getDataGroup().value?.get(0)?.let {onSwitchTemp(it.copy())}
        }


    }

    @SuppressLint("UseRequireInsteadOfGet", "SetTextI18n")
    private fun onSwitchTemp(listTemp: WeatherDetial) {

        listTemp.let {
            val mainTemp: String
            val tempMax: String
            val tempMin: String
            if (!switcherTemp) {
                switcherTemp = !switcherTemp
                mainTemp = it.main.temp.celToFah().toString().fromatTemperatureFahrenheit()
                tempMax = it.main.temp_max.celToFah().toString().fromatTemperatureFahrenheit()
                tempMin = it.main.temp_min.celToFah().toString().fromatTemperatureFahrenheit()
                tit_temp_f_d.setTextColor(this@MainWeatherFragment.context?.getColor(R.color.colorDetiveItem)!!)
                tit_temp_c_d.setTextColor(this@MainWeatherFragment.context?.getColor(R.color.colorActiveItem)!!)

            } else {
                switcherTemp = !switcherTemp
                mainTemp = it.main.temp.toString().fromatTemperatureCelsius()
                tempMax = it.main.temp_max.toString().fromatTemperatureCelsius()
                tempMin = it.main.temp_min.toString().fromatTemperatureCelsius()
                tit_temp_f_d.setTextColor(this@MainWeatherFragment.context?.getColor(R.color.colorActiveItem)!!)
                tit_temp_c_d.setTextColor(this@MainWeatherFragment.context?.getColor(R.color.colorDetiveItem)!!)

            }
            adapterExpan.switchWemp()
            title_temp.text = mainTemp
            title_temp_sup.text = "$tempMax/$tempMin"
        }
    }


    override fun onSetDataAdapter(dataExan: ArrayList<WeatherList>) {

        adapterExpan = WeatherExpanAdapter(dataExan)
        val layoutManager = LinearLayoutManager(this.context)

        list_future_weather.apply {
            this.layoutManager = layoutManager
            adapter = adapterExpan
        }
        val animator: ItemAnimator = list_future_weather.itemAnimator!!
        if (animator is DefaultItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }

}
