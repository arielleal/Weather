package com.myweather.presentation

import com.myweather.core.WeatherViewState
import com.myweather.domain.model.Weather

data class WeatherState(
    val loading: Boolean = false,
    val result: Weather? = null,
    val error: Throwable? = null
) : WeatherViewState {
    fun showLoading() = copy(loading = true)
    fun setSuccess(result: Weather) = copy(result = result, loading = false)
    fun setError() = copy(result = null, loading = false)
}