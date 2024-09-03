package com.myweather.data.source.local

interface StoredSearchLocalDataSource {
    fun get(): String?
    fun set(cityName: String)
}