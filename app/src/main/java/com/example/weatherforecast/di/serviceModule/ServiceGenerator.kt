package com.example.weatherforecast.di.serviceModule

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.weatherforecast.uitl.Config
import com.example.weatherforecast.uitl.LiveMonitorInterface
import com.example.weatherforecast.uitl.NoNetworkException
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.io.IOException
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object ServiceGenerator {
    fun <T> create(context: Context?, networkMonitor: LiveMonitorInterface, config: Config, clazz: Class<T>): T {

        val httpClient = OkHttpClient.Builder()
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)

        context?.let {
            val cacheDir = File(it.cacheDir, UUID.randomUUID().toString())
            // 10 MiB cache
            val cache = Cache(cacheDir, 10 * 1024 * 1024)
            httpClient.cache(cache)
        }

        try {

            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {

                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()

                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}

                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            httpClient.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            httpClient.hostnameVerifier { _, _ -> true }

        } catch (e: Exception) {
            Log.e("Error",e.message!!)
        }

        val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.i("Error",message) })
        logger.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(logger)

        httpClient.addInterceptor { chain ->
            val original = chain.request()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder().apply {
                addHeader("Authorization", "${config.accessToken.tokenType} ${config.accessToken.accessToken}")
                addHeader("Accept-Language", config.languageKey)
            }

            val request = requestBuilder.build()

            chain.proceed(request)
        }

        // Network monitor interceptor:
        httpClient.addInterceptor { chain ->
            if (networkMonitor.isConnected()) {
                return@addInterceptor chain.proceed(chain.request())
            } else {
                throw NoNetworkException()
            }
        }

        val client = httpClient.build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(config.httpUrl)
            .client(client)
            .build()

        return retrofit.create(clazz)
    }

    /**
     * GET Value Request
     *
     * @param request
     * @return
     */
    private fun toString(request: Request): String? {
        try {
            if (request.method() == "GET") {
                return request.url().query()
            } else {
                val copy = request.newBuilder().build()
                if (copy != null) {
                    val buffer = Buffer()
                    val body = copy.body()
                    if (body != null) {
                        body.writeTo(buffer)
                        return buffer.readUtf8()
                    }
                }
            }
            return "null"
        } catch (e: IOException) {
            return "did not work"
        }

    }
}