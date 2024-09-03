package com.myweather.domain.repository

interface StoredSearchRepository {
    fun get(): String?
    fun set(cityName: String)
}