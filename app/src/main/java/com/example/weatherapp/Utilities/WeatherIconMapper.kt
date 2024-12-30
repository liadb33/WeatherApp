package com.example.weatherapp.Utilities

object WeatherIconMapper {
    private val weatherIconMapping = mapOf(
        "Sunny" to "sunny_icon",
        "Clear" to "sunny_icon",
        "Partly cloudy" to "sunny_icon",
        "Cloudy" to "cloudy_icon",
        "Overcast" to "cloudy_icon",
        "Mist" to "cloudy_icon",
        "Fog" to "cloudy_icon",
        "Freezing fog" to "cloudy_icon",
        "Patchy rain possible" to "rainy_icon",
        "Light rain" to "rainy_icon",
        "Moderate rain" to "rainy_icon",
        "Heavy rain" to "rainy_icon",
        "Light freezing rain" to "rainy_icon",
        "Moderate or heavy freezing rain" to "rainy_icon",
        "Light drizzle" to "rainy_icon",
        "Freezing drizzle" to "rainy_icon",
        "Heavy freezing drizzle" to "rainy_icon",
        "Patchy light drizzle" to "rainy_icon",
        "Patchy light rain" to "rainy_icon",
        "Moderate rain at times" to "rainy_icon",
        "Heavy rain at times" to "rainy_icon",
        "Light rain shower" to "rainy_icon",
        "Moderate or heavy rain shower" to "rainy_icon",
        "Torrential rain shower" to "rainy_icon",
        "Patchy light rain with thunder" to "storm_icon",
        "Moderate or heavy rain with thunder" to "storm_icon",
        "Patchy snow possible" to "snowy_icon",
        "Light snow" to "snowy_icon",
        "Moderate snow" to "snowy_icon",
        "Heavy snow" to "snowy_icon",
        "Patchy moderate snow" to "snowy_icon",
        "Patchy heavy snow" to "snowy_icon",
        "Blowing snow" to "snowy_icon",
        "Blizzard" to "snowy_icon",
        "Patchy light snow" to "snowy_icon",
        "Moderate or heavy snow showers" to "snowy_icon",
        "Light snow showers" to "snowy_icon",
        "Patchy light snow with thunder" to "storm_icon",
        "Moderate or heavy snow with thunder" to "storm_icon",
        "Thundery outbreaks possible" to "storm_icon",
        "Patchy sleet possible" to "storm_icon",
        "Patchy freezing drizzle possible" to "storm_icon",
        "Light sleet" to "storm_icon",
        "Moderate or heavy sleet" to "storm_icon",
        "Light sleet showers" to "storm_icon",
        "Moderate or heavy sleet showers" to "storm_icon",
        "Light showers of ice pellets" to "storm_icon",
        "Moderate or heavy showers of ice pellets" to "storm_icon",
        "Ice pellets" to "storm_icon"
    )

    fun getWeatherIcon(conditionText: String): String {
        return weatherIconMapping[conditionText] ?: "default_icon"
    }
}