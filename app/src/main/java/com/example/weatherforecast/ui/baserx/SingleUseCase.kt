package com.example.weatherforecast.ui.baserx

import com.example.weatherforecast.data.error.AppError
import io.reactivex.Single
import th.co.the1.the1app.common.lib.schedulers.RxScheduler

abstract class SingleUseCase<in UseCaseInput, T>(
    private val executionThread: RxScheduler,
    private val postExecutionThread: RxScheduler,
    defaultError: (Throwable) -> AppError
) : BaseUseCase(defaultError) {

    protected abstract fun create(input: UseCaseInput): Single<T>

    fun execute(input: UseCaseInput): Single<T> {
        return create(input)
            .onErrorResumeNext {
                if (isCanceledException(it)) {
                    Single.never()
                } else {
                    Single.error<T>(composeError(it))
                }
            }
            .doOnError(::doOnError)
            .subscribeOn(executionThread.get())
            .observeOn(postExecutionThread.get())
    }

    private fun doOnError(throwable: Throwable) {
        throwable.printStackTrace()
    }
}
