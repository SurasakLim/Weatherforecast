package com.example.weatherforecast.ui.screen.weathercurrent

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import com.bumptech.glide.Glide
import com.example.weatherforecast.databinding.MainWeatherFragmentBinding
import com.example.weatherforecast.ui.base.BaseFragment
import com.example.weatherforecast.ui.base.BaseFragmentCallBack
import com.example.weatherforecast.ui.screen.mainWeather.model.WeatherList
import com.example.weatherforecast.ui.screen.weathercurrent.adapter.WeatherExpanAdapter
import com.example.weatherforecast.uitl.ImageExtension.setBackGround
import com.example.weatherforecast.uitl.StringExtenion.dateToDay
import th.co.the1.the1app.common.lib.extension.subscribeOnClick
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WeatherCurrentFragment : BaseFragment<WeatherCurrentViewModel>(), BaseFragmentCallBack {

    private lateinit var adapterExpan: WeatherExpanAdapter
    private var _binding: MainWeatherFragmentBinding? = null
    private val args: WeatherCurrentFragmentArgs by navArgs()

    private val binding get() = _binding!!

    override fun viewModel(): WeatherCurrentViewModel =
        ViewModelProvider(
            this, viewModelFactory
        ).get(WeatherCurrentViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainWeatherFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViewModel() {
        viewModel.input.onGroupSetData(args.itemWeatherDeatial)
    }

    override fun setUpView() {
        setUpWeatherCurrentPage()
    }

    private fun setUpWeatherCurrentPage() {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val currentDateTime = LocalDateTime.now().format(formatter).dateToDay()
        binding.cardWeatherMain.tvDateNow.text = currentDateTime
        binding.bgWeatherDetail.setBackGround(args.itemWeatherDeatial)
        onShowWeather()
    }

    override fun bindViewEvent() {
    }

    override fun bindViewModel() {
        viewModel.dataPassDetail.observe(this) {
            onSetDataAdapter(it)
        }
    }

    fun onSetDataAdapter(dataExan: List<WeatherList>) {
        val layoutManager = LinearLayoutManager(this.context)
        with(binding) {
            with(rvFetureWeather) {
                this.layoutManager = layoutManager
                adapter = WeatherExpanAdapter(dataExan).also {
                    adapterExpan = it
                }
            }
            val animator: ItemAnimator = rvFetureWeather.itemAnimator!!
            if (animator is DefaultItemAnimator) {
                animator.supportsChangeAnimations = false
            }
        }
    }

    @SuppressLint("SetTextI18n", "UseRequireInsteadOfGet")
    fun onShowWeather() {
        with(args.itemWeatherDeatial) {
            with(binding.cardWeatherMain) {
                tvTitleCityName.text = cityName
                list.firstOrNull()?.run {
                    tvMainTemp.text = tempFormate
                    tvSubTemp.text = "$tempMaxFormate/$tempMinFormate"
                    val humidity = humidity.toString()
                    tvSupStatus.text = "Humidity $humidity %"
                }

                list.firstOrNull()?.weatherX?.first()?.let {
                    tvWeatherStatus.text = it.description
                    Glide.with(this@WeatherCurrentFragment)
                        .load("http://openweathermap.org/img/wn/${it.icon}@2x.png")
                        .into(ivIconWeather)
                }

                bSwitchTemp.subscribeOnClick {
                }
            }
        }
    }
}
