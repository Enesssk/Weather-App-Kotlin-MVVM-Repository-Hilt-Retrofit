package com.example.weather_app_kotlin_mvvm_repository.data.dependencyinjection

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.weather_app_kotlin_mvvm_repository.R
import com.example.weather_app_kotlin_mvvm_repository.data.repo.WeatherRepo
import com.example.weather_app_kotlin_mvvm_repository.data.service.APIService
import com.example.weather_app_kotlin_mvvm_repository.data.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun injectWeatherRepo(apiService: APIService) = WeatherRepo(apiService)

    @Singleton
    @Provides
    fun injectRetrofit() : APIService {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(APIService::class.java)
    }

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background))

}