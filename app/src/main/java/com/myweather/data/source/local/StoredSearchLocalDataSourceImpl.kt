package com.myweather.data.source.local

import com.myweather.core.CacheStorage

class StoredSearchLocalDataSourceImpl(
    private val cache: CacheStorage
) : StoredSearchLocalDataSource {
    override fun get(key: String): String? = cache.get(key)
    override fun set(key: String, value: String) = cache.set(key, value)
}
