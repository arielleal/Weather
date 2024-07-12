package com.myweather.domain.repository

import com.myweather.data.model.WeatherRequest
import com.myweather.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather(request: WeatherRequest): Flow<Weather>
}