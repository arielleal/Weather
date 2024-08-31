package com.myweather.data.service

import com.myweather.data.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("weather")
    suspend fun getWeather(
        @Query("key") key: String,
        @Query("city_name") cityName: String?,
        @Query("lat") latitude: String?,
        @Query("lon") longitude: String?
    ): WeatherResponse
}