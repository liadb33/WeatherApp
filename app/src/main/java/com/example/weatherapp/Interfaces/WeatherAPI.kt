package com.example.weatherapp.Interfaces

import com.example.weatherapp.Data.LocationSearchResult
import com.example.weatherapp.Data.WeatherResponse
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherAPI {
    companion object{
        const val BASE_URL: String = "https://api.weatherapi.com/v1/"
        const val API_KEY: String = "API_KEY"
    }

    @GET("current.json")
    fun getCurrentWeather(
        @Query("key") key: String = API_KEY,
        @Query("q") query: String
    ): Call<WeatherResponse>

    @GET("forecast.json")
    fun getForecastWeather(
        @Query("key") key : String = API_KEY,
        @Query("q") query : String,
        @Query("days") days : String = "2"
    ) : Call<WeatherResponse>

    @GET("search.json")
    fun searchAutoComplete(
        @Query("key") key : String = API_KEY,
        @Query("q") query : String
    ) : Call<List<LocationSearchResult>>

}