package com.bulich.misha.pogodakaliningrad2.api

import com.bulich.misha.pogodakaliningrad2.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory

object MyApi {
    private const val BASE_URL = "https://api.openweathermap.org"
    private const val API_KEY = BuildConfig.API_KEY
    private val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val urlInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val newUrl: HttpUrl = originalRequest.url.newBuilder()
                .addQueryParameter("appid", API_KEY)
                .addQueryParameter("units", "metric")
                .addQueryParameter("lang", "ru")
                .build()
            val newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build()

            return chain.proceed(newRequest)
        }
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(urlInterceptor)
        .build()

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    val retrofitService: WeatherOpenApi by lazy {
        retrofit().create(WeatherOpenApi::class.java)
    }



}