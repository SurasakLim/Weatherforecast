package com.example.weatherforecast.data.error

import android.content.Context
import androidx.annotation.StringRes
import com.example.weatherapi.exception.ApiException
import com.example.weatherapi.exception.JsonApiException
import com.example.weatherforecast.R

open class AppError(
    cause: Throwable?,
    open val readableMessage: String? = null,
    @StringRes open val readableMessageRes: Int? = null
) : Throwable(cause) {

    open val code: String?
        get() = if (cause is ApiException) {
            (cause as? ApiException)?.code
        } else {
            (cause as? JsonApiException)?.error?.code
        }

    fun getMessageError(cause: Throwable?): String? {
        return if (cause is ApiException) {
            (cause as? ApiException)?.message
        } else {
            (cause as? JsonApiException)?.error?.detail
        }
    }
}

class Ignored(cause: Throwable?) : AppError(cause, null, null)

fun Throwable.userReadableMessage(context: Context): String {
    return when (this) {
        is AppError -> {
            readableMessage ?: context.getString(readableMessageRes ?: R.string.error_generic)
        }
        else -> context.getString(R.string.error_generic)
    }
}

val Throwable.errorCode: String?
    get() = (this as AppError).code
