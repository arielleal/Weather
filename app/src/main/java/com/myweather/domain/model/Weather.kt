package com.myweather.domain.model

data class Weather(
    val type: String,
    val weatherResults: Results,
)

data class Results(
    val temp: String,
    val city: String,
    val humidity: String,
    val windSpeedy: String,
    val moonPhase: String,
    val forecastList: List<Forecast>
)

data class Forecast(
    val date: String,
    val weekday: String,
    val maxTemp: String,
    val minTemp: String,
    val description: String,
    val condition: String,
)

