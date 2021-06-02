package th.co.the1.the1app.common.lib.schedulers

interface RxSchedulerProvider {
    fun io(): RxScheduler

    fun computation(): RxScheduler

    fun main(): RxScheduler
}
