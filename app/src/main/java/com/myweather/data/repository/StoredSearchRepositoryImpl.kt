package com.myweather.data.repository

import com.myweather.data.source.local.StoredSearchLocalDataSource
import com.myweather.domain.repository.StoredSearchRepository

class StoredSearchRepositoryImpl(
    private val storedSearchLocalDataSource: StoredSearchLocalDataSource
) : StoredSearchRepository {
    override fun get(key: String): String? = storedSearchLocalDataSource.get(key)
    override fun set(key: String, value: String) = storedSearchLocalDataSource.set(key, value)
}