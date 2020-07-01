package com.example.weatherforecast

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import dagger.android.support.DaggerAppCompatActivity

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

}
