package com.example.weather_app_kotlin_mvvm_repository.data.repo

import android.util.Log
import com.example.weather_app_kotlin_mvvm_repository.data.model.WeatherModel
import com.example.weather_app_kotlin_mvvm_repository.data.service.APIService
import com.example.weather_app_kotlin_mvvm_repository.data.util.Resource
import com.example.weather_app_kotlin_mvvm_repository.data.util.Util.api_KEY
import javax.inject.Inject

class WeatherRepo @Inject constructor(private val apiService: APIService) {

    suspend fun weatherResponse(cityName : String) :Resource<WeatherModel> {
        return try {
            val response = apiService.searchWeather(cityName)
            Log.d("verifyresponse","${response}")
            Log.d("verifyresponse","${response.message()}")

            if(response.isSuccessful){
                response.body()?.let {
                    Resource.success(it)
                } ?: Resource.error("Error ${response.message()}",null)
            }else{
                Resource.error("Errorr ${response.message()}",null)
            }
        }catch (e: Exception){
            Resource.error(e.localizedMessage,null)
        }
    }

}