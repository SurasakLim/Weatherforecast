package com.example.weatherforecast

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.weatherforecast.ui.ViewPagerAdapter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleViewFragment()
    }

    private fun handleViewFragment() {
        supportFragmentManager.let { it ->
            ViewPagerAdapter(it,this.lifecycle).also {
                mainWeatherFragment.adapter = it
            }
        }
    }

}
