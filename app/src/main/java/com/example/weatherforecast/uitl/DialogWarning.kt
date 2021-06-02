package com.example.weatherforecast.uitl

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.Window
import com.example.weatherforecast.R
import kotlinx.android.synthetic.main.dialog_warning.*

object DialogWarning {

    class Builder(private val context: Context) {

        private var dialog: Dialog? = null

        fun create(message: String): Builder {
            try {
                dialog = Dialog(context)

                dialog?.apply {
                    requestWindowFeature(Window.FEATURE_NO_TITLE)
                    setCancelable(false)
                    setContentView(R.layout.dialog_warning)
                    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    window?.setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    txt_message.text = message
                    bt_ok.setOnClickListener {
                        dialog?.dismiss()
                    }
                    show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return this
        }
    }
}
