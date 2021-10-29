package com.marvelapp.repository.network.client

import android.content.Context
import com.marvelapp.repository.BuildConfig
import com.marvelapp.repository.network.interceptor.GeneralInterceptor
import com.marvelapp.repository.network.interceptor.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.util.concurrent.TimeUnit

abstract class BaseOkHttpClient {

    protected open fun buildClient(builder: OkHttpClient.Builder, context: Context): OkHttpClient {

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(Level.BODY)
            builder.addInterceptor(loggingInterceptor)
        }

        builder.addInterceptor(GeneralInterceptor())
        builder.addInterceptor(NetworkConnectionInterceptor(context))

        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.writeTimeout(30, TimeUnit.SECONDS)

        return builder.build()
    }
}