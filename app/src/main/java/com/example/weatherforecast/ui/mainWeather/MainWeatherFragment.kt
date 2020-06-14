package com.example.weatherforecast.ui.mainWeather

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherforecast.MainActivity
import com.example.weatherforecast.MainApplication

import com.example.weatherforecast.R
import com.example.weatherforecast.di.serviceModule.annotation.Injectable
import com.example.weatherforecast.ui.mainWeather.model.Weather
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_weather_fragment.*
import javax.inject.Inject

class MainWeatherFragment : Fragment(), Injectable,MainWeatherContract.View {

    @Inject lateinit var loginViewModel: MainWeatherViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).loginComponent.inject(this)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        presenter.onGetWeatherData("London")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onShowWeather(data: Weather) {
//        viewModelWeath.weather.value
        testData.text = data.city.name

    }

    override fun onErrorWeather(messageError:String) {
//        viewModelWeath.weather.value
//        testData.text = viewModelWeath.weather.value?.errorMessage
    }

}
