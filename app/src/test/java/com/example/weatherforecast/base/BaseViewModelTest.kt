package com.example.weatherforecast.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherforecast.base.RxImmediateSchedulerRule
import org.junit.*

abstract class BaseViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Before
    fun setUp() {
        setUpTest()
    }

    protected abstract fun setUpTest()
}
