package com.example.weatherapi.provides

import android.annotation.SuppressLint
import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import th.co.the1.the1app.common.rx.BuildConfig
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object OkHttpClientProvider {

    @JvmStatic
    fun getOkHttpClientBuilder(
        context: Context,
    ): OkHttpClient.Builder {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {

            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()

            @SuppressLint("TrustAllX509TrustManager")
            @Throws(CertificateException::class)
            override fun checkClientTrusted(
                chain: Array<java.security.cert.X509Certificate>,
                authType: String
            ) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            @Throws(CertificateException::class)
            override fun checkServerTrusted(
                chain: Array<java.security.cert.X509Certificate>,
                authType: String
            ) {
            }
        })
        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())

        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory = sslContext.socketFactory

        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        val cacheDir = context.cacheDir
        val applicationCache = Cache(cacheDir, CACHE_SIZE)

        return OkHttpClient.Builder()
            .cache(applicationCache)
            .connectTimeout(DEFAULT_TIMEOUT_IN_SEC, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT_IN_SEC, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT_IN_SEC, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(logging)
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
    }
}

private const val CACHE_SIZE = 10L * 1024 * 1024 // 10Mb
private const val DEFAULT_TIMEOUT_IN_SEC = 60L
