package com.myweather.data.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.myweather.core.CacheStorage
import com.myweather.core.CacheStorageImpl
import com.myweather.core.ResourceProvider
import com.myweather.core.ResourceProviderImpl
import com.myweather.data.mapper.WeatherMapper
import com.myweather.data.repository.StoredSearchRepositoryImpl
import com.myweather.data.repository.WeatherRepositoryImpl
import com.myweather.data.service.WeatherAPI
import com.myweather.data.source.local.StoredSearchLocalDataSource
import com.myweather.data.source.local.StoredSearchLocalDataSourceImpl
import com.myweather.data.source.remote.WeatherRemoteDataSource
import com.myweather.domain.repository.StoredSearchRepository
import com.myweather.domain.repository.WeatherRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.math.sin

private const val BASE_URL = "https://api.hgbrasil.com/"
private const val PREFERENCES = "preferences"

object DataModule {

    private fun presentationModule() : Module {
        return module {
            single<ResourceProvider> {
                ResourceProviderImpl(context = get())
            }
        }
    }

    private fun dataModule() : Module {
        return module {
            single<WeatherRepository> {
                WeatherRepositoryImpl(
                    WeatherRemoteDataSource(
                        service = get(),
                        mapper = WeatherMapper(resource = get())
                    )
                )
            }
            single<CacheStorage> {
                CacheStorageImpl(
                    get<Context>().getSharedPreferences(PREFERENCES, MODE_PRIVATE)
                )
            }
            factory<StoredSearchRepository> {
                StoredSearchRepositoryImpl(
                    storedSearchLocalDataSource = StoredSearchLocalDataSourceImpl(cache = get())
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

    private fun createService(moshi: Moshi) : WeatherAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(WeatherAPI::class.java)
    }

    fun loadModule() {
        loadKoinModules(dataModule() + serviceModule() + presentationModule())
    }
}