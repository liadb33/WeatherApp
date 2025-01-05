package com.example.weatherapp.Data

data class WeatherResponse(
    val location : WeatherLocation,
    val current : CurrentWeather,
    val forecast : Forecast
)

data class WeatherLocation(
    val name : String,
    val region : String,
    val country : String,
    val localtime : String,
    val lat : Double,
    val lon : Double
)

data class CurrentWeather(
    val condition : WeatherCondition,
    val temp_c : Double,
    val temp_f : Double,
    val wind_kph : Double,
    val precip_mm : Double,
    val humidity : Double,
    val feelslike_c : Double
)


data class WeatherCondition(
    val text : String
)

data class Forecast(
   val forecastday : List<ForecastDay>
)

data class ForecastDay(
    val date : String,
    val day : DayForecast,
    val hour : List<HourForecast>
)

data class DayForecast(
    val maxtemp_c : Double,
    val condition : WeatherCondition,
    val maxwind_kph : Double,
    val avghumidity : Double,
    val totalprecip_mm : Double
)

data class HourForecast(
    val time : String,
    val temp_c: Double,
    val condition: WeatherCondition
)



