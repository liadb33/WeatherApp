package com.example.weatherapp.Utilities

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.weatherapp.Data.WeatherResponse
import com.example.weatherapp.Interfaces.LocationUpdateCallback
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherWorker(
    context: Context, workerParams: WorkerParameters)
    : Worker(context, workerParams) {


    var resultFlag : Boolean = false
    override fun doWork(): Result {

        //gets last location from device
        LocationManager.getInstance().locationUpdateCallback = object : LocationUpdateCallback {
            override fun onLocationUpdated(latitude: Double, longitude: Double) {
                if(latitude != 0.0 && longitude != 0.0 ){
                    Log.e("weatherWorker", "onLocationUpdated: $latitude,$longitude")
                    getCurrentWeather("$latitude,$longitude")
                    getForecastAhead("$latitude,$longitude")
                }
            }
        }
        LocationManager.getInstance().getLastKnownLocation()

        return when(resultFlag){
            true -> Result.success()
            false -> Result.failure()
        }
    }

    private fun getForecastAhead(location: String)  {

        RetrofitClient.instance.getForecastWeather(query = location).enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(p0: Call<WeatherResponse>, p1: Response<WeatherResponse>) {
                SharedPreferencesManagerV3.getInstance().putString("hourlyForecast", Gson().toJson(p1.body()))
                resultFlag = true
            }

            override fun onFailure(p0: Call<WeatherResponse>, p1: Throwable) {
                resultFlag = false
            }

        })

    }

    //uses last location from device and puts it in sharedpref
    private fun getCurrentWeather(location: String) {

        RetrofitClient.instance.getCurrentWeather(query = location).enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(p0: Call<WeatherResponse>, p1: Response<WeatherResponse>) {
                SharedPreferencesManagerV3.getInstance().putString("lastWeather", Gson().toJson(p1.body()))
                resultFlag = true
                Log.e("weatherWorker", "onResponse: ${p1.body()}")
            }

            override fun onFailure(p0: Call<WeatherResponse>, p1: Throwable) {
                resultFlag = false
            }

        })


    }
}