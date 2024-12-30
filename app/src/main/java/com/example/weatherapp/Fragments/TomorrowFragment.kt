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
import com.example.weatherapp.databinding.FragmentTomorrowBinding


class TomorrowFragment : Fragment() {
    private var binding: FragmentTomorrowBinding? = null
    private var pendingHourForecast: List<HourForecast>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTomorrowBinding.inflate(inflater, container, false)
        initViews()
        pendingHourForecast?.let {
            loadHourForecast(it)
            pendingHourForecast = null
        }
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initViews() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = RecyclerView.HORIZONTAL
        binding?.fragmentLSTTomorrow?.layoutManager = linearLayoutManager
    }

    fun loadHourForecast(hourForecast: List<HourForecast>) {
        if (binding == null) {
            pendingHourForecast = hourForecast
            return
        }

        val hourAdapter = HourAdapter(hourForecast)
        binding?.fragmentLSTTomorrow?.adapter = hourAdapter
    }
}
