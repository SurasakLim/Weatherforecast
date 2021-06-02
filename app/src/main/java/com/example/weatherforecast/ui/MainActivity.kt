package com.example.weatherforecast.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.ActivityMainBinding
import com.example.weatherforecast.ui.base.BaseActivity
import com.example.weatherforecast.ui.base.ViewBindingContract

class MainActivity : BaseActivity<MainActivityViewModel>(), ViewBindingContract {

    override fun viewModel(): MainActivityViewModel =
        ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)

    override val layoutResource: Int
        get() = R.layout.activity_main

    override val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
