package com.example.weatherforecast.ui.mainWeather

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.FragmentWearterDetailBinding
import com.example.weatherforecast.ui.mainWeather.model.Weather
import com.example.weatherforecast.uitl.DialogWarning
import com.example.weatherforecast.uitl.ImageExtension.setBackGround
import com.example.weatherforecast.uitl.StringExtenion.dateToDay
import com.example.weatherforecast.uitl.StringExtenion.onDone
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_wearter_detail.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


class WearterDetailFragment : DaggerFragment(), MainWeatherContract.View {
    companion object {

        private const val AUTO_HIDE = true

        private const val AUTO_HIDE_DELAY_MILLIS = 3000

        private const val UI_ANIMATION_DELAY = 300
    }
    private val hideRunnable = Runnable { hide() }
    private var visible: Boolean = false
    private val hideHandler = Handler()
    private lateinit var binding: FragmentWearterDetailBinding

    @Inject
    lateinit var viewModelWeath: MainWeatherViewModel

    @Inject
    lateinit var presenter: MainWeatherPresenter
    private var adapterWeather: WeatherAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModelWeath =
            ViewModelProviders.of(requireActivity()).get(MainWeatherViewModel::class.java)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_wearter_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModelWeath.weather.value?.data?.list?.isNotEmpty()!!) {
            onShowWeather(viewModelWeath.weather.value?.data!!)
        } else {
            presenter.onGetWeatherData("London")
            bg_loading.visibility = View.VISIBLE
            progressBar.visibility = View.VISIBLE
        }
        binding.apply {
            lifecycleOwner = this@WearterDetailFragment
        }

        setDefalutUi()
    }

    fun setDefalutUi() {
        editText.setText("London")
        editText.onDone {
            bg_loading.visibility = View.VISIBLE
            progressBar.visibility = View.VISIBLE
            presenter.onGetWeatherData(editText.text.toString())
        }
        content_weather.setOnClickListener {
            toggle()
        }
        delayedHide(AUTO_HIDE_DELAY_MILLIS)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onShowWeather(data: Weather) {
        binding.apply {
            currentItem = viewModelWeath.weather.value?.data
            viewModel = viewModelWeath
        }
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val currentDateTime = LocalDateTime.now().format(formatter).dateToDay()
        textView15.text = currentDateTime
        viewModelWeath.apply {
            mainTemp.value = data.list[0].main.temp
            tempFeelLike.value = data.list[0].main.feels_like
        }

        adapterWeather = WeatherAdapter(viewModelWeath.getGroupWeatherDay()!!, 0)
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        featue_list.apply {
            adapter = adapterWeather
            this.layoutManager = layoutManager
        }

        bg_main.setBackGround(data)

        chang_temp.setOnClickListener {
            viewModelWeath.apply {
                if (untiTemporalChange.value!!) {
                    untiTemporalChange.postValue(!untiTemporalChange.value!!)
                    tit_temp_f.setTextColor(this@WearterDetailFragment.context?.getColor(R.color.colorDetiveItem)!!)
                    tit_temp_c.setTextColor(this@WearterDetailFragment.context?.getColor(R.color.colorActiveItem)!!)
                } else {
                    tit_temp_c.setTextColor(this@WearterDetailFragment.context?.getColor(R.color.colorDetiveItem)!!)
                    tit_temp_f.setTextColor(this@WearterDetailFragment.context?.getColor(R.color.colorActiveItem)!!)
                    untiTemporalChange.postValue(!untiTemporalChange.value!!)
                }
            }
        }

        view_current.setOnClickListener {
            val bundle = bundleOf("weatherDetial" to data)
            findNavController().navigate(
                R.id.action_wearterDetailFragment_to_mainWeatherFragment2,
                bundle
            )
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        hide()
    }

    override fun onErrorWeather(message: String) {
        this@WearterDetailFragment.context?.let {
            DialogWarning.Builder(it).create(message)
        }
    }

    override fun onLoading() {
        bg_loading.visibility = View.VISIBLE
        progressBar.visibility = View.VISIBLE
    }

    override fun onLoaded() {
        bg_loading.visibility = View.GONE
        progressBar.visibility = View.GONE
    }
    private fun hide() {
        // Schedule a runnable to remove the status and navigation bar after a delay
        visible = false
        hideHandler.postDelayed(hidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    @Suppress("InlinedApi")
    private fun show() {

        // Schedule a runnable to display UI elements after a delay
        visible = true
        hideHandler.removeCallbacks(hidePart2Runnable)
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }

    @Suppress("InlinedApi")
    private val hidePart2Runnable = Runnable {

        val flags =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        activity?.window?.decorView?.systemUiVisibility = flags
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }

    private fun toggle() {
        if (visible) {
            hide()
        } else {
            show()
        }
    }

    private fun delayedHide(delayMillis: Int) {
        hideHandler.removeCallbacks(hideRunnable)
        hideHandler.postDelayed(hideRunnable, delayMillis.toLong())
    }

}
