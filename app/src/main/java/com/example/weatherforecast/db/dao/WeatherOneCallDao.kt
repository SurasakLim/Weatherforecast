package com.example.weatherforecast.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherforecast.db.tblModel.TblWeatherOneCall
import io.reactivex.Completable
import io.reactivex.Flowable

interface WeatherOneCallDao {

    @Query("SELECT * FROM TblWeatherOneCall")
    fun getTblWeather(): Flowable<TblWeatherOneCall>

    @Query("DELETE  FROM TblWeatherOneCall")
    fun onDeleteAllWeatherOneCall()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun onInsertWeatherOneCallDao(dataInsert: TblWeatherOneCall): Completable
}