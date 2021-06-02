package com.example.weatherapi.operators

import com.example.weatherapi.exception.ApiException
import com.example.weatherapi.exception.UnknownException
import com.example.weatherapi.response.ApiErrorResponse
import com.google.gson.*
import io.reactivex.FlowableOperator
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import retrofit2.Response
import java.lang.reflect.Type

class MobileApiErrorOperator<T>
internal constructor() : FlowableOperator<T, Response<T>> {

    override fun apply(subscriber: Subscriber<in T>): Subscriber<in Response<T>> {
        return object : Subscriber<Response<T>> {
            override fun onComplete() {
                subscriber.onComplete()
            }

            override fun onSubscribe(s: Subscription) {
                subscriber.onSubscribe(s)
            }

            override fun onNext(response: Response<T>) {
                if (!response.isSuccessful) {
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

    private fun emitError(subscriber: Subscriber<in T>, response: Response<T>?) {
        try {
            if (response?.message() != null || response?.errorBody() != null) {
                val errorBody = response.errorBody()?.string() ?: ""

                val source = if (response.message().isNullOrEmpty()) {
                    errorBody
                } else {
                    response.message()
                }
                val apiError = try {
                    parseApiError(source)
                } catch (exception: JsonSyntaxException) {
                    parseApiError(errorBody)
                }
                subscriber.onError(
                    ApiException(
                        status = response.code(),
                        code = apiError?.code ?: GENERIC_ERROR,
                        message = apiError?.message,
                        data = apiError?.data
                    )
                )
            } else {
                subscriber.onError(UnknownException)
            }
        } catch (exception: Exception) {
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

    private fun parseApiError(source: String): ApiErrorResponse? {
        return source.let {
            val gsonBuilder =
                GsonBuilder().registerTypeAdapter(ApiErrorResponse::class.java, deserializer)
                    .create()
            gsonBuilder.fromJson(source, ApiErrorResponse::class.java)
        }
    }

    private var deserializer = object : JsonDeserializer<ApiErrorResponse> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): ApiErrorResponse? {
            json?.let {
                val jsonObject = it.asJsonObject
                val code = jsonObject.get(CODE)?.asString ?: GENERIC_ERROR
                val message = jsonObject.get(MESSAGE)?.asString
                val data = jsonObject.get(DATA)?.toString()
                return ApiErrorResponse(
                    code = code,
                    message = message,
                    data = data
                )
            }
            return null
        }
    }
}

const val GENERIC_ERROR = "generic error"
const val CODE = "code"
const val MESSAGE = "message"
const val DATA = "data"
