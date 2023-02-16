package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("grnd_level") val grndLevel: Int,
    @SerializedName("sea_level") val seaLevel: Int,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("temp_min") val tempMin: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
)