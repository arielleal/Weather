package com.myweather.domain.usecase

import com.myweather.data.model.WeatherRequest
import com.myweather.domain.model.Weather
import com.myweather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class WeatherUseCase(
    private val repository: WeatherRepository
) {
    operator fun invoke(request: WeatherRequest): Flow<Weather> {
        return repository.getWeather(request)
    }
}