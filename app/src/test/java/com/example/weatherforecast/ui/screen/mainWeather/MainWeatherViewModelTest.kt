package com.example.weatherforecast.ui.screen.mainWeather

import com.example.weatherforecast.base.BaseViewModelTest
import com.example.weatherforecast.ui.screen.mainWeather.model.Weather
import com.example.weatherforecast.usecase.WeatherSingleUseCase
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.amshove.kluent.mock
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class MainWeatherViewModelTest : BaseViewModelTest() {

    @Mock
    private lateinit var getWeatherSingleUseCase: WeatherSingleUseCase

    private lateinit var weatherViewModelTest: MainWeatherViewModel

    override fun setUpTest() {
        weatherViewModelTest = MainWeatherViewModelImpl(
            getWeatherSingleUseCase
        )
    }

    @Test
    fun `getWeatherCountry,Can fetch Weather data`() {
        val mockCountry = "London"
        val dataWeather: Weather = mock()
        `getWeatherSingleUseCase,Should request weather api`(mockCountry, dataWeather)
        weatherViewModelTest.input.getWeatherCountry(mockCountry)

        verify(getWeatherSingleUseCase).execute(mockCountry)
    }

    @Test
    fun `untiTemporalChange,Can set change Unit Temporal`() {
        val mockCountry = "London"
        val dataWeather = Weather(tempType = "C", tempMain = 12.0)

        `getWeatherSingleUseCase,Should request weather api`(mockCountry, dataWeather)
        weatherViewModelTest.setWeatherData(dataWeather)
        weatherViewModelTest.setUntiTemporal(false)
        weatherViewModelTest.changeUntiTemporalChange()

        assertEquals("F", weatherViewModelTest.weather.value?.tempType)
    }

    private fun `getWeatherSingleUseCase,Should request weather api`(
        mockCountry: String,
        weatherMock: Weather
    ) {
        whenever(
            getWeatherSingleUseCase.execute(mockCountry)
        ).doReturn(
            Single.just(weatherMock)
        )
    }
}