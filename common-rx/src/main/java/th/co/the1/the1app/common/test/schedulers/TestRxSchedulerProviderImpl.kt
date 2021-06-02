package th.co.the1.the1app.common.test.schedulers

import th.co.the1.the1app.common.lib.schedulers.RxScheduler
import th.co.the1.the1app.common.lib.schedulers.RxSchedulerProvider

class TestRxSchedulerProviderImpl : RxSchedulerProvider {
    override fun io(): RxScheduler = RxScheduler.TestIoThread

    override fun computation(): RxScheduler = RxScheduler.TestComputationThread

    override fun main(): RxScheduler = RxScheduler.TestMainThread
}
