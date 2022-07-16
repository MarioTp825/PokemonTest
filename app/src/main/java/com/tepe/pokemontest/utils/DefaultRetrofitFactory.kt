package com.tepe.pokemontest.utils

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class DefaultRetrofitFactory <T>
        private constructor(
            private val base_url: String,
            private val serviceClass: Class<T>,
            readTimeout:Long,
            connectTimeout:Long,
        ) {

    private var gson = GsonBuilder()
        .setLenient()
        .create()

    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(readTimeout, TimeUnit.SECONDS)
        .connectTimeout(connectTimeout, TimeUnit.SECONDS)
        .build()

    private val _retrofit = Retrofit.Builder()
        .baseUrl(base_url)
        .client(okHttpClient)
        .addConverterFactory(
            GsonConverterFactory.create(gson)
        ).build()
        .create(serviceClass)

    val retrofit: T get() = _retrofit

    companion object {

        fun <T> newInstance(
            baseUrl: String,
            serviceClass: Class<T>,
            readTimeout: Long = 10,
            connectTimeout: Long = 5,
        ) = DefaultRetrofitFactory(
            base_url = baseUrl,
            serviceClass = serviceClass,
            readTimeout = readTimeout,
            connectTimeout = connectTimeout,
        ).retrofit

    }
}