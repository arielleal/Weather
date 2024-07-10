package com.myweather.data.service

import com.myweather.data.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

private const val KEY = "12afaa95"
private const val BASE_ENDPOINT = "https://api.hgbrasil.com/weather"

interface WeatherAPI {
    @GET(BASE_ENDPOINT)
    fun getWeather(
        @Query("key") key: String,
        @Query("city_name") cityName: String,
        @Query("lat") latitude: String,
        @Query("lon") longitude: String
    ): Flow<WeatherResponse>
}