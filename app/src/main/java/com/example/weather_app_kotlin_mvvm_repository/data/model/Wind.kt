package com.example.weather_app_kotlin_mvvm_repository.data.model


import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("gust")
    val gust: Int,
    @SerializedName("speed")
    val speed: Double
)