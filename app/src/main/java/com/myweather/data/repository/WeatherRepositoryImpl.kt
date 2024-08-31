package com.myweather.data.repository

import com.myweather.data.model.WeatherRequest
import com.myweather.data.source.remote.WeatherRemoteDataSource
import com.myweather.domain.model.Weather
import com.myweather.domain.repository.WeatherRepository
import com.myweather.core.RemoteException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : WeatherRepository {
    override fun getWeather(request: WeatherRequest): Flow<Weather> =
        remoteDataSource(request).flowOn(dispatcher)
}