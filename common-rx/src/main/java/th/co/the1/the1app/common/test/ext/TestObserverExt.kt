package th.co.the1.the1app.common.test.ext

import io.reactivex.observers.TestObserver

fun <T> TestObserver<T>.firstValue(): T {
    return this.values().first()
}

fun <T> TestObserver<T>.latestValue(): T {
    return this.values().last()
}
