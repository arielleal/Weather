package com.myweather.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("by")
    val type: String,
    @SerializedName("results")
    val weatherResults: ResultResponse,

)

data class ResultResponse(
    @SerializedName("temp")
    val temp: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("humidity")
    val humidity: String,
    @SerializedName("wind_speedy")
    val windSpeedy: String,
    @SerializedName("moon_phase")
    val moonPhase: String,
    @SerializedName("forecast")
    val forecastList: List<ForecastResponse>
)

data class ForecastResponse(
    @SerializedName("date")
    val date: String,
    @SerializedName("weekday")
    val weekday: String,
    @SerializedName("max")
    val maxTemp: String,
    @SerializedName("min")
    val minTemp: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("condition")
    val condition: String,
)
