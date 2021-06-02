package com.example.weatherforecast.base

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler.ExecutorWorker
import io.reactivex.plugins.RxJavaPlugins
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.*

class RxImmediateSchedulerRule : TestRule {
    private val immediate: Scheduler = object : Scheduler() {

        override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
            return super.scheduleDirect(run, 0, unit)
        }

        override fun createWorker(): Worker {
            return ExecutorWorker(Executor { obj: Runnable -> obj.run() }, true)
        }
    }

    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins
                    .setInitIoSchedulerHandler { scheduler: Callable<Scheduler?>? ->
                        immediate
                    }
                RxJavaPlugins
                    .setInitComputationSchedulerHandler { scheduler: Callable<Scheduler?>? ->
                        immediate
                    }
                RxJavaPlugins
                    .setInitNewThreadSchedulerHandler { scheduler: Callable<Scheduler?>? ->
                        immediate
                    }
                RxJavaPlugins
                    .setInitSingleSchedulerHandler { scheduler: Callable<Scheduler?>? ->
                        immediate
                    }
                RxAndroidPlugins
                    .setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? ->
                        immediate
                    }
                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}
