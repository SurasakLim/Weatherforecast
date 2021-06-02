package com.example.weatherforecast.uitl

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

fun Fragment.addOnBackPressedCallback(block: () -> Unit) {
    activity?.onBackPressedDispatcher?.addCallback(
        this,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                block()
            }
        }
    )
}
