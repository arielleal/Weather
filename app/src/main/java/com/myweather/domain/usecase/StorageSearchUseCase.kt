package com.myweather.domain.usecase

import com.myweather.domain.repository.StoredSearchRepository

class StorageSearchUseCase(
    private val repository: StoredSearchRepository
) {
    fun get(key: String): String? = repository.get(key)
    fun set(key: String, value: String) = repository.set(key, value)
}