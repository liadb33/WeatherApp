package com.example.weatherapp.Interfaces

import com.example.weatherapp.Data.LocationSearchResult
import com.example.weatherapp.Data.WeatherResponse
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherAPI {
    companion object{
        const val BASE_URL: String = "http://IP_ADDRESS:3000/"
    }

    @GET("weather")
    fun getCurrentWeather(
        @Query("q") query: String
    ): Call<WeatherResponse>

    @GET("weather/forecast")
    fun getForecastWeather(
        @Query("q") query : String,
        @Query("days") days : String = "2"
    ) : Call<WeatherResponse>

    @GET("weather/search")
    fun searchAutoComplete(
        @Query("q") query : String
    ) : Call<List<LocationSearchResult>>

}