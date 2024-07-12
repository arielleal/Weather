package com.myweather.data.model

import retrofit2.http.Query

data class WeatherRequest(
    val key: String,
    val cityName: String,
    val latitude: String,
    val longitude: String
)
