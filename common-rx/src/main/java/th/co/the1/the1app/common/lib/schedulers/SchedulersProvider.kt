package th.co.the1.the1app.common.lib.schedulers

import io.reactivex.Scheduler

interface SchedulersProvider {
    fun io(): Scheduler

    fun computation(): Scheduler

    fun main(): Scheduler
}
