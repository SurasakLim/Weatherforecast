package com.example.weatherforecast.ui.screen.mainWeather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.FragmentWearterDetailBinding
import com.example.weatherforecast.ui.base.BaseFragment
import com.example.weatherforecast.ui.base.BaseFragmentCallBack
import com.example.weatherforecast.ui.screen.mainWeather.model.Weather
import com.example.weatherforecast.ui.screen.mainWeather.model.mapGroupWeatherDay
import com.example.weatherforecast.uitl.DialogWarning
import com.example.weatherforecast.uitl.ImageExtension.setBackGround
import com.example.weatherforecast.uitl.StringExtenion.dateToDay
import com.example.weatherforecast.uitl.StringExtenion.fromatTemperatureCelsius
import com.example.weatherforecast.uitl.visibleOrGone
import th.co.the1.the1app.common.lib.extension.subscribeOnClick
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WearterMainFragment : BaseFragment<MainWeatherViewModel>(), BaseFragmentCallBack {

    private var _binding: FragmentWearterDetailBinding? = null

    private val binding get() = _binding!!

    private var adapterWeather: WeatherAdapter? = null

    override fun viewModel(): MainWeatherViewModel =
        ViewModelProvider(this, viewModelFactory).get(MainWeatherViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =
            FragmentWearterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViewModel() {
        viewModel.input.getWeatherCountry("London")
    }

    override fun setUpView() {
        binding.editText.doOnTextChanged { text, _, _, count ->
            if (text?.length!! > LENGHT_LIMIT) {
                viewModel.input.getWeatherCountry(text.toString())
            }
        }
    }

    override fun bindViewEvent() {
        viewModel.showLoading
            .subscribe {
                showLoading(it)
            }.addToDisposables()
    }

    override fun bindViewModel() {
        viewModel.weather.observe(this) {
            onShowWeather(it)
        }
    }

    fun showLoading(isShowLoad: Boolean) {
        with(binding) {
            vLoading.visibleOrGone(isShowLoad)
            progressBar.visibleOrGone(isShowLoad)
            csWeatherPage.visibleOrGone(!isShowLoad)
        }
    }

    @SuppressLint("UseRequireInsteadOfGet", "SetTextI18n")
    private fun onShowWeather(data: Weather) {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val currentDateTime = LocalDateTime.now().format(formatter).dateToDay() ?: ""
        binding.textView15.text = currentDateTime
        val listGruopWeather = data.list.mapGroupWeatherDay(currentDateTime)
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        with(binding) {
            titlecityName.text = data.cityName
            titleTemp.text = data.tempFormate

            with(data.list.first()) {
                tvWind.text = getString(R.string.wind_unite).format(windSpeed.toString())
                tvFeelLike.text = feelsLike.toString().fromatTemperatureCelsius()
                tvCloud.text = getString(R.string.cloud_unite).format(cloud.toString())
                tvHumity.text = "$humidity%"
                tvSnow.text = "$snow%"
                tvStatusTemp.text = weatherX.first().description
            }

            with(featureList) {
                this.layoutManager = layoutManager
                adapter = WeatherAdapter(listGruopWeather, 0).also {
                    adapterWeather = it
                }
            }

            bgMain.setBackGround(data)
            switchTemp.subscribeOnClick {
                tvTempF.setTextColor(
                    this@WearterMainFragment.context?.getColor(R.color.colorDetiveItem)!!
                )
                tvTempC.setTextColor(
                    this@WearterMainFragment.context?.getColor(R.color.colorActiveItem)!!
                )
                viewModel.changeUntiTemporalChange()
            }.addToDisposables()

            tvSwithToCurrentTemp.setOnClickListener {
                val bundle = bundleOf("itemWeatherDeatial" to data)
                findNavController().navigate(
                    R.id.actionMainWeatherFragmentToWeatherCurrentFragment,
                    bundle
                )
            }
        }
    }

    fun onErrorWeather(message: String) {
        this@WearterMainFragment.context?.let {
            DialogWarning.Builder(it).create(message)
        }
    }
}

private const val LENGHT_LIMIT = 3
