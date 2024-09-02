package com.myweather.data.model

private const val KEY = "12afaa95"

data class WeatherRequest(
    val key: String = KEY,
    val cityName: String? = null,
    val latitude: Float? = null,
    val longitude: Float? = null
)
