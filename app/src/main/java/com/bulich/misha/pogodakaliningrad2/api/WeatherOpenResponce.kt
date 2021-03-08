package com.bulich.misha.pogodakaliningrad2.api



import com.google.gson.annotations.SerializedName

data class WeatherOpenResponce(
    @SerializedName("main")
    val main: MainTemp,
    @SerializedName("name")
    val name: String,
    @SerializedName("wind")
    val wind: Wind

)

