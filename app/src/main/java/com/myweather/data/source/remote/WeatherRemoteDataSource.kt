package com.myweather.data.source.remote

import com.myweather.data.mapper.WeatherMapper
import com.myweather.data.model.WeatherRequest
import com.myweather.data.service.WeatherAPI
import com.myweather.domain.model.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map



class WeatherRemoteDataSource(
    private val service: WeatherAPI,
    private val mapper: WeatherMapper
) {
    operator fun invoke(request: WeatherRequest): Flow<Weather> {
        return flow {
            emit(service.getWeather(
                key = request.key,
                cityName = request.cityName,
                latitude = request.latitude,
                longitude = request.longitude
            ))
        }.map(mapper::map)
    }
}