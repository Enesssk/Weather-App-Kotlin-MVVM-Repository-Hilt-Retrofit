package com.example.weather_app_kotlin_mvvm_repository.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app_kotlin_mvvm_repository.data.model.WeatherModel
import com.example.weather_app_kotlin_mvvm_repository.data.repo.WeatherRepo
import com.example.weather_app_kotlin_mvvm_repository.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repo: WeatherRepo): ViewModel() {


    private val _weather = MutableLiveData<Resource<WeatherModel>>()
    val weather : LiveData<Resource<WeatherModel>>
        get() = _weather

    fun searchWeather(cityName : String){

        _weather.value = Resource.loading(null)

        viewModelScope.launch {
            val response = repo.weatherResponse(cityName)
            _weather.value = response
        }
    }


}