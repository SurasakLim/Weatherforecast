package com.example.weatherforecast.ui.domain

import javax.inject.Inject

class WeathRepository @Inject constructor(private val dataSourceRemote:WeathDataSourceRemoteInterface): WeathDataSourceRemoteInterface by dataSourceRemote