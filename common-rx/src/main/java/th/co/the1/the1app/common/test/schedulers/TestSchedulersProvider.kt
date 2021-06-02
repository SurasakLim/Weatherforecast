package th.co.the1.the1app.common.test.schedulers

import io.reactivex.schedulers.Schedulers
import th.co.the1.the1app.common.lib.schedulers.SchedulersProvider

@Suppress("IllegalIdentifier")
class TestSchedulersProvider : SchedulersProvider {
    override fun io() = Schedulers.trampoline()

    override fun computation() = Schedulers.trampoline()

    override fun main() = Schedulers.trampoline()

}
