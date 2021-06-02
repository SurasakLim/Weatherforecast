package com.example.weatherforecast.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import com.example.weatherforecast.data.error.userReadableMessage
import com.example.weatherforecast.di.serviceModule.factory.ActivityViewModelFactory
import com.example.weatherforecast.ui.base.Injectable.Companion.mockInjector
import com.example.weatherforecast.ui.base.Injectable.Companion.realInjector
import com.example.weatherforecast.uitl.Toaster
import dagger.android.AndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : DaggerAppCompatActivity(), Injectable {

    @Inject
    lateinit var viewModelFactory: ActivityViewModelFactory

    @Inject
    lateinit var toaster: Toaster

    protected val viewModel: VM by lazy { viewModel() }

    abstract fun viewModel(): VM

    @get:LayoutRes
    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        when (this) {
            is ViewBindingContract -> {
                setContentView(binding.root)
            }
            else -> {
                setContentView(layoutResource)
            }
        }
    }

    private val disposables = CompositeDisposable()

    override fun androidInjector(): AndroidInjector<Any> {
        realInjector = super.androidInjector()
        return mockInjector ?: realInjector
    }

    protected fun Disposable.addToDisposables() = addTo(disposables)

    fun displayError(error: Throwable) {
        val message = error.userReadableMessage(this)
        toaster.display(message)
    }
}
