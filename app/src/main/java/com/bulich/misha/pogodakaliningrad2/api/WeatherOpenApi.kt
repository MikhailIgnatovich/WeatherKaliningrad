package com.bulich.misha.pogodakaliningrad2.api

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherOpenApi {

    @GET("/data/2.5/weather")
    fun getWeather(@Query("q") cityName: String): Observable<WeatherOpenResponce>
}