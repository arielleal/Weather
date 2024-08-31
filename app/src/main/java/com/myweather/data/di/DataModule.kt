package com.myweather.data.di

import com.myweather.data.mapper.WeatherMapper
import com.myweather.data.repository.WeatherRepositoryImpl
import com.myweather.data.service.WeatherAPI
import com.myweather.data.source.remote.WeatherRemoteDataSource
import com.myweather.domain.repository.WeatherRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.hgbrasil.com/"

object DataModule {

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

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
            single { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }
            single { createService(moshi = get()) }
        }
    }

    private inline fun createService(moshi: Moshi) : WeatherAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(WeatherAPI::class.java)
    }

    fun loadModule() {
        loadKoinModules(dataModule() + serviceModule())
    }
}