package com.example.weatherforecast.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherforecast.base.RxImmediateSchedulerRule
import org.junit.ClassRule
import org.junit.Rule

open class BaseTest {
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()
}
