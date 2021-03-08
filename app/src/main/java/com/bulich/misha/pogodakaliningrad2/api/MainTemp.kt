package com.bulich.misha.pogodakaliningrad2.api

import com.google.gson.annotations.SerializedName

data class MainTemp(
    @SerializedName("temp")
    val temp: Float,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int
)
