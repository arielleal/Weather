package com.myweather.data.repository

import com.myweather.data.source.local.StoredSearchLocalDataSource
import com.myweather.domain.repository.StoredSearchRepository

class StoredSearchRepositoryImpl(
    private val storedSearchLocalDataSource: StoredSearchLocalDataSource
) : StoredSearchRepository {
    override fun get(): String? = storedSearchLocalDataSource.get()
    override fun set(cityName: String) = storedSearchLocalDataSource.set(cityName)
}