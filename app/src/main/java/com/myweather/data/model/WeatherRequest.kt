package com.myweather.data.model

data class WeatherRequest(
    val key: String,
    val cityName: String?,
    val latitude: String?,
    val longitude: String?
)
