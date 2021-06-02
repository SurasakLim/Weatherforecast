package com.example.weatherforecast.ui.base

import android.os.Bundle
import android.view.View
import com.example.weatherforecast.data.error.userReadableMessage
import com.example.weatherforecast.di.serviceModule.factory.ActivityViewModelFactory
import com.example.weatherforecast.di.serviceModule.factory.FragmentViewModelFactory
import com.example.weatherforecast.uitl.Toaster
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel> : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: FragmentViewModelFactory

    @Inject
    lateinit var activityViewModelFactory: ActivityViewModelFactory

    @Inject
    lateinit var toaster: Toaster

    private val disposables = CompositeDisposable()

    val viewModel: VM by lazy { viewModel() }

    abstract fun viewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this as? BaseFragmentCallBack)?.let { initViewModel() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (this as? BaseFragmentCallBack)?.let {
            setUpView()
            bindViewEvent()
            bindViewModel()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    protected open fun displayError(error: Throwable) {
        val message = error.userReadableMessage(requireContext())
        toaster.display(message)
    }

//    protected open fun displayErrorAsSnackOnTop(error: Throwable) {
//        val message = error.userReadableMessage(requireContext())
//        toaster.displayErrorAsSnackOnTop(message)
//    }

    protected open fun displayErrorMessageFromServer(error: Throwable) {
        val message = error.cause?.message
        toaster.display(message ?: error.userReadableMessage(requireContext()))
    }

    protected fun Disposable.addToDisposables() = addTo(disposables)
}
