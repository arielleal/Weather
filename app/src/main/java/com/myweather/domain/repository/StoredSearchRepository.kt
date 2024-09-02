package com.myweather.domain.repository

interface StoredSearchRepository {
    fun get(key: String): String?
    fun set(key: String, value: String)
}