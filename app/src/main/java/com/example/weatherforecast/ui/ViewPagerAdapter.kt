package com.example.weatherforecast.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherforecast.ui.news.NewsFragment
import com.example.weatherforecast.ui.mainWeather.WearterDetailFragment

open class ViewPagerAdapter(fragment: FragmentManager, lifet:Lifecycle) : FragmentStateAdapter(fragment,lifet) {

    var listFragment = listOf(
        WearterDetailFragment(),
        NewsFragment()
    )
    override fun getItemCount(): Int {
        return listFragment.size
    }

    override fun createFragment(position: Int): Fragment = listFragment[position]

}