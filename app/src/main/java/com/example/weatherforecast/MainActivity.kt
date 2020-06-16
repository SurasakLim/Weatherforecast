package com.example.weatherforecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.weatherforecast.ui.baseActivity.BaseAppCompatActivity
import com.example.weatherforecast.ui.mainWeather.MainWeatherContract
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hostMain = NavHostFragment.create(R.navigation.nav_weather)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainWeatherFragment,hostMain)
            .setPrimaryNavigationFragment(hostMain)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
//        val fragment = this@MainActivity.supportFragmentManager.findFragmentById(R.id.mainWeatherFragment)
//        (fragment as? MainWeatherContract.ViewController)?.onBackStack()
    }
}
