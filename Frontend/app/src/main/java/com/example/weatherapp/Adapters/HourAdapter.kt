package com.example.weatherapp.Adapters

import android.annotation.SuppressLint
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.text.style.SuperscriptSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.Data.HourForecast
import com.example.weatherapp.R
import com.example.weatherapp.Utilities.WeatherIconMapper
import com.example.weatherapp.databinding.ForecastItemBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HourAdapter(
    private val hourForecastList : List<HourForecast>
) : RecyclerView.Adapter<HourAdapter.HourViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        val binding = ForecastItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return HourViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return hourForecastList.size
    }

    private fun getItem(position: Int) : HourForecast{
        return hourForecastList[position]
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HourViewHolder, position: Int) {
        with(holder){
            with(hourForecastList[position]){
                binding.forecastHourTime.text = extractTimeFromDateTime(time)
                binding.forecastIcon.setImageResource(WeatherIconMapper.getWeatherIcon(condition.text))
                Log.e("ConditionIcon","s${condition.text}s")
                binding.forecastTemperature.text = formatTemperatureText(temp_c)
            }
        }

    }

    private fun extractTimeFromDateTime(input: String): String {
        val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val outputFormat = DateTimeFormatter.ofPattern("HH:mm")

        val dateTime = LocalDateTime.parse(input, inputFormat)
        return dateTime.format(outputFormat)
    }

    // function to format the temperature with °C
    private fun formatTemperatureText(temperature: Double): SpannableString {
        val temperatureText = "${temperature}°C"
        val spannableString = SpannableString(temperatureText)
        val superscriptSpan = SuperscriptSpan()
        val relativeSize = RelativeSizeSpan(0.5f)  // Makes the °C half the size

        spannableString.setSpan(
            superscriptSpan,
            temperatureText.indexOf("°"),
            temperatureText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            relativeSize,
            temperatureText.indexOf("°"),
            temperatureText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return spannableString
    }


    inner class HourViewHolder(val binding : ForecastItemBinding) : RecyclerView.ViewHolder(binding.root){

    }
}