package com.myweather.domain.usecase

import com.myweather.domain.repository.StoredSearchRepository

class StorageSearchUseCase(
    private val repository: StoredSearchRepository
) {
    fun get(): String? = repository.get()
    fun set(cityName: String) = repository.set(cityName)
}