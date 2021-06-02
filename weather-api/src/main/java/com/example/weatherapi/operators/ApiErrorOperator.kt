package com.example.weatherapi.operators

import com.example.weatherapi.exception.JsonApiException
import com.example.weatherapi.exception.UnknownException
import com.squareup.moshi.Moshi
import io.reactivex.FlowableOperator
import moe.banana.jsonapi2.Document
import okio.BufferedSource
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import retrofit2.Response
import java.io.IOException
import java.util.*

class ApiErrorOperator<T>
internal constructor(private val moshi: Moshi) : FlowableOperator<T, Response<T>> {

    override fun apply(subscriber: Subscriber<in T>): Subscriber<in Response<T>> {
        return object : Subscriber<Response<T>> {
            override fun onComplete() {
                subscriber.onComplete()
            }

            override fun onSubscribe(s: Subscription) {
                subscriber.onSubscribe(s)
            }

            override fun onNext(response: Response<T>) {
                if (!response.isSuccessful || response.body() == null) {
                    emitError(subscriber, response)
                } else {
                    subscriber.onNext(response.body())
                    subscriber.onComplete()
                }
            }

            override fun onError(throwable: Throwable) {
                subscriber.onError(throwable)
            }
        }
    }

    private fun parseJsonApiDocumentError(source: BufferedSource?): Document? {
        return source?.let {
            moshi.adapter(Document::class.java).fromJson(source)
        }
    }

    private fun emitError(subscriber: Subscriber<in T>, response: Response<T>?) {
        try {
            val errorResponse = response?.errorBody()?.source()
            val documentError = parseJsonApiDocumentError(errorResponse)
            if (documentError?.errors != null) {
                val error = documentError.errors.first()
                // Returning the errors[0] from the response, also including the original response
                subscriber.onError(
                    response?.let { JsonApiException(error, it) }
                )
            } else {
                subscriber.onError(UnknownException)
            }
        } catch (exception: IOException) {
            subscriber.onError(exception)
        } catch (exception: NoSuchElementException) {
            subscriber.onError(exception)
        }
    }

    private fun getResponseHeader(rawResponse: okhttp3.Response?, key: String): String {
        return rawResponse?.header(key, "") ?: ""
    }

    private fun getRequestHeader(rawResponse: okhttp3.Response?, key: String): String {
        return rawResponse?.request?.header(key) ?: ""
    }

    private fun getRequestURL(rawResponse: okhttp3.Response?): String {
        return rawResponse?.request?.url.toString()
    }
}

const val CRASHLYTICS_NAME_TRACE_ID = "log-v2-x-trace-id"
const val CRASHLYTICS_NAME_URL = "log-v2-x-url"
const val CRASHLYTICS_NAME_DEVICE = "log-v2-x-device"
const val CRASHLYTICS_NAME_HTTP_STATUS = "log-v2-x-http-status"
const val CRASHLYTICS_NAME_ERROR_DATA = "log-v2-x-error-data"

const val CRASHLYTICS_KEY_X_TRACE_ID = "x-trace-id"
const val CRASHLYTICS_KEY_X_DEVICE_ID = "X-Device-Id"
