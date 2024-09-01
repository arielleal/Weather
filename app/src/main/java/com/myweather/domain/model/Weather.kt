package com.myweather.domain.model

data class Weather(
    val type: String,
    val weatherResults: Results,
)

data class Results(
    val temp: Double,
    val time: String,
    val description: String,
    val city: String,
    val humidity: String,
    val windSpeedy: String,
    val conditionSlug: Int,
    val forecastList: List<Forecast>
)

data class Forecast(
    val date: String,
    val weekday: String,
    val maxTemp: Double,
    val minTemp: Double,
    val description: String,
    val condition: Int,
)

