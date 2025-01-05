package com.example.weatherapp.Utilities

import com.example.weatherapp.R

object WeatherIconMapper {
    private val weatherIconMapping = mapOf(
        "Sunny" to R.drawable.sunny_icon,
        "Clear" to R.drawable.sunny_icon,
        "Partly Cloudy" to R.drawable.sunny_cloudy,
        "Cloudy" to R.drawable.cloudy_icon,
        "Overcast" to R.drawable.cloudy_icon,
        "Mist" to R.drawable.cloudy_icon,
        "Fog" to R.drawable.cloudy_icon,
        "Freezing fog" to R.drawable.cloudy_icon,
        "Patchy rain possible" to R.drawable.cloudy_rain_storm_icon,
        "Patchy rain nearby" to R.drawable.cloudy_rain_storm_icon,
        "Light rain" to R.drawable.cloudy_rain_storm_icon,
        "Moderate rain" to R.drawable.cloudy_rain_storm_icon,
        "Heavy rain" to R.drawable.cloudy_rain_storm_icon,
        "Light freezing rain" to R.drawable.cloudy_rain_storm_icon,
        "Moderate or heavy freezing rain" to R.drawable.cloudy_rain_storm_icon,
        "Light drizzle" to R.drawable.cloudy_rain_storm_icon,
        "Freezing drizzle" to R.drawable.cloudy_rain_storm_icon,
        "Heavy freezing drizzle" to R.drawable.cloudy_rain_storm_icon,
        "Patchy light drizzle" to R.drawable.cloudy_rain_storm_icon,
        "Patchy light rain" to R.drawable.cloudy_rain_storm_icon,
        "Moderate rain at times" to R.drawable.cloudy_rain_storm_icon,
        "Heavy rain at times" to R.drawable.cloudy_rain_storm_icon,
        "Light rain shower" to R.drawable.cloudy_rain_storm_icon,
        "Moderate or heavy rain shower" to R.drawable.cloudy_rain_storm_icon,
        "Torrential rain shower" to R.drawable.cloudy_rain_storm_icon,
        "Patchy light rain with thunder" to R.drawable.cloudy_rain_storm_icon,
        "Moderate or heavy rain with thunder" to R.drawable.cloudy_rain_storm_icon,
        "Patchy snow possible" to R.drawable.snowy_icon,
        "Light snow" to R.drawable.snowy_icon,
        "Moderate snow" to R.drawable.snowy_icon,
        "Heavy snow" to R.drawable.snowy_icon,
        "Patchy moderate snow" to R.drawable.snowy_icon,
        "Patchy heavy snow" to R.drawable.snowy_icon,
        "Blowing snow" to R.drawable.snowy_icon,
        "Blizzard" to R.drawable.snowy_icon,
        "Patchy light snow" to R.drawable.snowy_icon,
        "Moderate or heavy snow showers" to R.drawable.snowy_icon,
        "Light snow showers" to R.drawable.snowy_icon,
        "Patchy light snow with thunder" to R.drawable.cloudy_rain_storm_icon,
        "Moderate or heavy snow with thunder" to R.drawable.cloudy_rain_storm_icon,
        "Thundery outbreaks possible" to R.drawable.cloudy_rain_storm_icon,
        "Patchy sleet possible" to R.drawable.cloudy_rain_storm_icon,
        "Patchy freezing drizzle possible" to R.drawable.cloudy_rain_storm_icon,
        "Light sleet" to R.drawable.cloudy_rain_storm_icon,
        "Moderate or heavy sleet" to R.drawable.cloudy_rain_storm_icon,
        "Light sleet showers" to R.drawable.cloudy_rain_storm_icon,
        "Moderate or heavy sleet showers" to R.drawable.cloudy_rain_storm_icon,
        "Light showers of ice pellets" to R.drawable.cloudy_rain_storm_icon,
        "Moderate or heavy showers of ice pellets" to R.drawable.cloudy_rain_storm_icon,
        "Ice pellets" to R.drawable.cloudy_rain_storm_icon
    )

    fun getWeatherIcon(conditionText: String): Int {
        return weatherIconMapping[conditionText.trim()] ?: R.drawable.wifi
    }
}