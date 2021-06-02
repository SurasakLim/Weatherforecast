package com.example.weatherforecast.base

import org.junit.Before
import th.co.the1.the1app.common.test.schedulers.TestRxSchedulerProviderImpl

abstract class BaseUseCaseTest {

    val testRxSchedulerProvider: TestRxSchedulerProviderImpl = TestRxSchedulerProviderImpl()

    @Before
    fun setUp() {
        setUpTest()
    }

    protected abstract fun setUpTest()
}
