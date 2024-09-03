package com.myweather.data.source.local

import com.myweather.core.CacheStorage

class StoredSearchLocalDataSourceImpl(
    private val cache: CacheStorage
) : StoredSearchLocalDataSource {
    override fun get(): String? = cache.get()
    override fun set(cityName: String) = cache.set(cityName)
}
