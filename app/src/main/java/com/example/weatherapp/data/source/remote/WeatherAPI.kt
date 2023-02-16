package com.example.weatherapp.data.source.remote

import com.example.weatherapp.data.model.WeatherModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

//http://api.openweathermap.org/data/2.5/weather?q=izmir&APPID=b4623ed7cf69b69d9a6c0304a7e8c7d8

interface WeatherAPI {

    @GET("data/2.5/weather?&units=metric&APPID=b4623ed7cf69b69d9a6c0304a7e8c7d8")
    fun getData(
        @Query("q") cityName: String
    ): Single<WeatherModel>
}