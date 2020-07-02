package com.example.weatherforecast.DaoTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weatherforecast.db.WeatherDataBase
import com.example.weatherforecast.db.tblModel.TblWeatherOneCall
import com.example.weatherforecast.unitlity.HelperTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherDao {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: WeatherDataBase


    @Before fun initDb() {

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherDataBase::class.java
        ).allowMainThreadQueries().build()
    }

    @After fun closeDb(){
        database.close()
    }

    @Test
    fun insertWeatherOneCallTest(){

        val requestSuccess =  HelperTest.getStringFromFile("request-weather-success")
        val weatherOnCallData = HelperTest.getDataFromJson<TblWeatherOneCall>(requestSuccess)

        database.weatherOneCallDao().onInsertWeatherOneCallDao(weatherOnCallData!!).blockingAwait()
        database.weatherOneCallDao().getTblWeather().test().assertValue{
            it.current.humidity != 0
        }
    }


}