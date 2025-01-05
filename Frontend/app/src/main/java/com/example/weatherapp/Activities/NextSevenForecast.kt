package com.example.weatherapp.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.Adapters.NextSevenAdapter
import com.example.weatherapp.Data.WeatherResponse
import com.example.weatherapp.R
import com.example.weatherapp.Utilities.RetrofitClient
import com.example.weatherapp.databinding.ActivityNextSevenForecastBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextSevenForecast : AppCompatActivity() {

    private lateinit var binding: ActivityNextSevenForecastBinding
    private lateinit var locationName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNextSevenForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.nextSevenForecastRecyclerView.layoutManager = linearLayoutManager
        locationName = intent.getStringExtra("location")!!
        binding.nextSevenForecastTitle.text = "Next 7 days in $locationName"
        loadNextSevenForecast()


        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() 
        }
    }

    private fun loadNextSevenForecast(){

        RetrofitClient.instance.getForecastWeather(query = locationName, days = "7").enqueue(object :
            Callback<WeatherResponse> {
            override fun onResponse(p0: Call<WeatherResponse>, p1: Response<WeatherResponse>) {
                p1.body()?.let {
                    val nextSevenAdapter = NextSevenAdapter(it.forecast.forecastday)
                    binding.nextSevenForecastRecyclerView.adapter = nextSevenAdapter
                }
            }

            override fun onFailure(p0: Call<WeatherResponse>, p1: Throwable) {

            }

        })
    }
}