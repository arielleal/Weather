package com.myweather.data.model

private const val KEY = "12afaa95"

data class WeatherRequest(
    val key: String = KEY,
    val cityName: String?,
    val latitude: String?,
    val longitude: String?
)
