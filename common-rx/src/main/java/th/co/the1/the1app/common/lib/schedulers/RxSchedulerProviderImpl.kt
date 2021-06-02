package th.co.the1.the1app.common.lib.schedulers

class RxSchedulerProviderImpl : RxSchedulerProvider {
    override fun io(): RxScheduler = RxScheduler.IoThread

    override fun computation(): RxScheduler = RxScheduler.ComputationThread

    override fun main(): RxScheduler = RxScheduler.MainThread
}
