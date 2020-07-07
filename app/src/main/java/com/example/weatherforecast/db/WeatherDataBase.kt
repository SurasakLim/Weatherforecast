//package com.example.weatherforecast.db
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.weatherforecast.db.dao.WeatherOneCallDao
//import com.example.weatherforecast.db.tblModel.*
//
//@Database(
//    entities = [TblWeatherOneCall::class, Current::class, Weather::class, FeelsLike::class, Temp::class, WeatherDetailDaily::class, Rain::class, WeatherDetail::class],
//    version = 2,
//    exportSchema = false
//)
//abstract class WeatherDataBase : RoomDatabase() {
//
//    abstract fun weatherOneCallDao(): WeatherOneCallDao
//
//    companion object {
//
//        @Volatile
//        private var INSTANCE: WeatherDataBase? = null
//
//        fun getInstance(context: Context): WeatherDataBase = INSTANCE ?: synchronized(this) {
//            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
//        }
//
//        private fun buildDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext
//                , WeatherDataBase::class.java,
//                "WeatherDb.db"
//            ).build()
//    }
//}