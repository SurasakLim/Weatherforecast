package th.co.the1.the1app.common.test.schedulers

import io.reactivex.schedulers.TestScheduler
import th.co.the1.the1app.common.lib.schedulers.SchedulersProvider

@Suppress("IllegalIdentifier")
class AdvancedTestSchedulersProvider : SchedulersProvider {
    val scheduler = TestScheduler()

    override fun io() = scheduler

    override fun computation() = scheduler

    override fun main() = scheduler
}
