package com.example.weatherforecast.uitl

import android.app.Activity
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import javax.inject.Inject

class Toaster @Inject constructor(private val activity: Activity) {

    private var toast: Toast? = null

    fun display(message: String) {
        toast?.cancel()
        toast = makeText(activity, message, LENGTH_LONG).also { it.show() }
    }

//    fun displayAsSnack(@StringRes message: Int) {
//        val text = activity.getText(message)
//        displayAsSnack(text)
//    }

//    @SuppressLint("InflateParams")
//    fun displayAsSnack(message: CharSequence) {
//        toast?.cancel()
//        val layout = LayoutInflater.from(activity).inflate(R.layout.layout_toast_snack, null)
//        val textView = layout.findViewById<TextView>(R.id.tvMessage)
//        textView.text = message
//        toast = Toast(activity).apply {
//            setGravity(Gravity.FILL_HORIZONTAL or Gravity.BOTTOM, 0, 0)
//            view = layout
//            duration = LENGTH_SHORT
//        }.also { it.show() }
//    }
//
//    @SuppressLint("InflateParams")
//    fun displayAsSnack(message: CharSequence, @DrawableRes resId: Int) {
//        toast?.cancel()
//        val layout =
//            LayoutInflater.from(activity).inflate(R.layout.layout_toast_snack_with_icon, null)
//        val textView = layout.findViewById<TextView>(R.id.tvMessage)
//        textView.text = message
//        textView.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0)
//        toast = Toast(activity).apply {
//            setGravity(Gravity.FILL_HORIZONTAL or Gravity.BOTTOM, 0, 0)
//            view = layout
//            duration = LENGTH_SHORT
//        }.also { it.show() }
//    }
//
//    fun displaySuccessAsSnackOnTop(message: CharSequence) {
//        displayAsSnackOnTop(message, R.drawable.ic_check_circle)
//    }
//
//    fun displayErrorAsSnackOnTop(message: CharSequence) {
//        displayAsSnackOnTop(message, R.drawable.ic_error)
//    }
//
//    @SuppressLint("InflateParams")
//    fun displayAsSnackOnTop(message: CharSequence, @DrawableRes resId: Int) {
//        toast?.cancel()
//        val layout =
//            LayoutInflater.from(activity).inflate(R.layout.layout_toast_snack_with_icon, null)
//        val textView = layout.findViewById<TextView>(R.id.tvMessage)
//        textView.text = message
//        textView.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0)
//        toast = Toast(activity).apply {
//            setGravity(Gravity.FILL_HORIZONTAL or Gravity.TOP, 0, 0)
//            view = layout
//            duration = LENGTH_SHORT
//        }.also { it.show() }
//    }
//
//    fun displayAsNotification(@StringRes message: Int) {
//        val text = activity.getText(message)
//        displayAsNotification(text)
//    }

//    @SuppressLint("InflateParams")
//    fun displayAsNotification(message: CharSequence) {
//        val layout = LayoutInflater.from(activity).
//        inflate(R.layout.layout_toast_notification, null)
//        val textView = layout.findViewById<TextView>(R.id.tvNotificationMessage)
//        textView.text = message
//        toast = Toast(activity).apply {
//            setGravity(Gravity.FILL_HORIZONTAL or Gravity.TOP, 0, 0)
//            view = layout
//            duration = LENGTH_LONG
//        }.also { it.show() }
//    }
}
