package com.myweather.data.source.local

interface StoredSearchLocalDataSource {
    fun get(key: String): String?
    fun set(key: String, value: String)
}