package com.bulich.misha.pogodakaliningrad2.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class WeatherParceble(
    val main: Float,
    val name: String,
    val pressure: Int,
    val humidity: Int,
    val speed: Double
) : Parcelable
