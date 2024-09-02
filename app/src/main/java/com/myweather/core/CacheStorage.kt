package com.myweather.core

interface CacheStorage {
    fun get(key: String): String?
    fun set(key: String, value: String)
}