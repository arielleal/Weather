package com.myweather.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @Json(name = "by")
    val type: String,
    @Json(name = "results")
    val weatherResults: ResultResponse,
)

data class ResultResponse(
    @Json(name = "temp")
    val temp: Double,
    @Json(name = "city")
    val city: String,
    @Json(name = "humidity")
    val humidity: Double,
    @Json(name = "wind_speedy")
    val windSpeedy: String,
    @Json(name = "moon_phase")
    val moonPhase: String,
    @Json(name = "forecast")
    val forecastList: List<ForecastResponse>
)

data class ForecastResponse(
    @Json(name = "date")
    val date: String,
    @Json(name = "weekday")
    val weekday: String,
    @Json(name = "max")
    val maxTemp: Double,
    @Json(name = "min")
    val minTemp: Double,
    @Json(name = "description")
    val description: String,
    @Json(name = "condition")
    val condition: String,
)
