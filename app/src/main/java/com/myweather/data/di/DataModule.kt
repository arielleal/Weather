package com.myweather.data.di

import com.myweather.data.mapper.WeatherMapper
import com.myweather.data.repository.WeatherRepositoryImpl
import com.myweather.data.service.WeatherAPI
import com.myweather.data.source.remote.WeatherRemoteDataSource
import com.myweather.domain.repository.WeatherRepository
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.hgbrasil.com/weather"

object DataModule {

    private fun dataModule() : Module {
        return module {
            single<WeatherRepository> {
                WeatherRepositoryImpl(
                    WeatherRemoteDataSource(
                        service = get(),
                        mapper = WeatherMapper()
                    )
                )
            }
        }
    }

    private fun serviceModule() : Module {
        return module {
            single { createService() }
        }
    }

    private fun createService() : WeatherAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }

    fun loadModule() {
        loadKoinModules(dataModule() + serviceModule())
    }
}