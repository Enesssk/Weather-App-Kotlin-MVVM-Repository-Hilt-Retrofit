package com.example.weather_app_kotlin_mvvm_repository.data.model


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Int
)