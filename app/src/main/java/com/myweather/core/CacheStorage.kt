package com.myweather.core

interface CacheStorage {
    fun get(): String?
    fun set(cityName: String)
}