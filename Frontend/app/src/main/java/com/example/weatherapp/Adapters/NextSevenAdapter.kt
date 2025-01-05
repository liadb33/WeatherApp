package com.example.weatherapp.Adapters

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.Data.ForecastDay
import com.example.weatherapp.R
import com.example.weatherapp.Utilities.WeatherIconMapper
import com.example.weatherapp.databinding.NextSevenItemBinding

class NextSevenAdapter(
    private val nextSevenForecastList : List<ForecastDay>
) : RecyclerView.Adapter<NextSevenAdapter.NextSevenViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextSevenViewHolder {
        val binding = NextSevenItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)

        return NextSevenViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return nextSevenForecastList.size
    }

    private fun getItem(position: Int) : ForecastDay{
        return nextSevenForecastList[position]
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NextSevenViewHolder, position: Int) {
        with(holder){
            with(nextSevenForecastList[position]){
                binding.nextSevenForecastLLC.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
                binding.nextSevenForecastCARD.setOnClickListener {
                    val v = if(binding.nextSevenForecastDetailsLLC.visibility == View.VISIBLE) View.GONE else View.VISIBLE
                    binding.nextSevenForecastDetailsLLC.visibility = v
                    binding.nextSevenForecastSpaceView.visibility = v
                }

                binding.nextSevenForecastDate.text = date
                binding.nextSevenForecastDegree.text = day.maxtemp_c.toString() + "°"
                binding.nextSevenForecastIMG.setImageResource(WeatherIconMapper.getWeatherIcon(day.condition.text))
                binding.nextSevenForecastRainText.text = day.totalprecip_mm.toString() + " mm"
                binding.nextSevenForecastWindText.text = day.maxwind_kph.toString() + " km/h"
                binding.nextSevenForecastHumidityText.text = day.avghumidity.toString() + "%"


            }
        }
    }


    inner class NextSevenViewHolder(val binding : NextSevenItemBinding) : RecyclerView.ViewHolder(binding.root){

    }


}