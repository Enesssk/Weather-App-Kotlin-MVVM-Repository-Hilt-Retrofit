package com.example.weather_app_kotlin_mvvm_repository.data.service

import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {


    @GET("/data/2.5/weather")
    suspend fun searchWeather(
        @Query("q") cityName : String,
        @Query("appid") apiKey : String
    )


}