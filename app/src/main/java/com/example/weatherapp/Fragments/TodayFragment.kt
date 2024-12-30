package com.example.weatherapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.Adapters.HourAdapter
import com.example.weatherapp.Data.HourForecast
import com.example.weatherapp.R
import com.example.weatherapp.Utilities.RetrofitClient
import com.example.weatherapp.databinding.FragmentTodayBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TodayFragment : Fragment() {

    private lateinit var binding : FragmentTodayBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTodayBinding.inflate(layoutInflater,container,false)

        initViews()
        return binding.root
    }

    private fun initViews() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = RecyclerView.HORIZONTAL
        binding.fragmentLSTToday.layoutManager = linearLayoutManager
        binding.todayShimmerLayout.startShimmer()
    }

    fun loadHourForecast(hourForecast: List<HourForecast>) {

        val currentTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        val filteredForecast = hourForecast.filter { forecast ->
            val forecastTime = LocalDateTime.parse(forecast.time, formatter)
            forecastTime.isAfter(currentTime)
        }

        binding.todayShimmerLayout.stopShimmer()
        binding.todayShimmerLayout.visibility = View.GONE

        val hourAdapter = HourAdapter(filteredForecast)
        binding.fragmentLSTToday.adapter = hourAdapter
        binding.fragmentLSTToday.visibility = View.VISIBLE
    }

    fun startShimmering(){
        binding.fragmentLSTToday.visibility = View.GONE
        binding.todayShimmerLayout.visibility = View.VISIBLE
        binding.todayShimmerLayout.startShimmer()
    }



}