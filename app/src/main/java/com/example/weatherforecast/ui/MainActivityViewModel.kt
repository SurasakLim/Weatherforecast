package com.example.weatherforecast.ui

import com.example.weatherforecast.ui.base.BaseViewModel
import javax.inject.Inject

abstract class MainActivityViewModel : BaseViewModel()

class MainActivityViewModelImpl @Inject constructor() : MainActivityViewModel()
