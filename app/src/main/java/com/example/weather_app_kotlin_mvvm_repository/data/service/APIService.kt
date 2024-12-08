package com.example.weather_app_kotlin_mvvm_repository.data.service

import com.example.weather_app_kotlin_mvvm_repository.data.model.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {


    @GET("data/2.5/weather?appid=937871ce6e334e836aaa378e7e26b614")
    suspend fun searchWeather(
        @Query("q") cityName : String
    ) : Response<WeatherModel>


}