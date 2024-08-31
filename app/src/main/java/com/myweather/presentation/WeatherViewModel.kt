package com.myweather.presentation

import androidx.lifecycle.viewModelScope
import com.myweather.data.model.WeatherRequest
import com.myweather.domain.usecase.WeatherUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val useCase: WeatherUseCase
) : WeatherState() {

    private fun getWeather(request: WeatherRequest) {
        viewModelScope.launch {
            useCase(request)
                .onStart { showLoading() }
                .catch {
                    setError(it)
                }
                .collect {
                    setSuccess(it)
                }
        }
    }

    fun getWeather() {
        getWeather(WeatherRequest(
            cityName = "Campinas",
            latitude = null,
            longitude = null
        ))
    }
}