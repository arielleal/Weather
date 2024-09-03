package com.myweather.presentation

import androidx.lifecycle.viewModelScope
import com.myweather.core.ViewModelState
import com.myweather.data.model.WeatherRequest
import com.myweather.domain.model.Weather
import com.myweather.domain.usecase.StorageSearchUseCase
import com.myweather.domain.usecase.WeatherUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherUseCase: WeatherUseCase,
    private val storageUseCase: StorageSearchUseCase,
) : ViewModelState<WeatherState>(WeatherState()) {

    private var _cityName: String? = null

    fun getWeather() {
        if (_cityName.isNullOrEmpty().not()) {
            getWeather(
                WeatherRequest(
                    cityName = _cityName
                )
            )
        }
    }

    private fun getWeather(request: WeatherRequest) {
        viewModelScope.launch {
            weatherUseCase(request)
                .onStart {
                    setState { state -> state.showLoading()}
                }
                .catch {
                    setState { state -> state.setError() }
                }
                .collect(::handleSuccess)
        }
    }

    private fun handleSuccess(result: Weather) {
        updateStorage(result.weatherResults.city)
        setState { state -> state.setSuccess(result) }
    }

    fun updateCityName(cityName: String?) {
        _cityName = cityName
    }

    fun getStorage() {
        _cityName = storageUseCase.get()
        getWeather()
    }

    private fun updateStorage(cityName: String) {
        storageUseCase.set(cityName)
    }

    fun setLocation(longitude: Double, latitude: Double) {
        getWeather(WeatherRequest(
            latitude = latitude.toFloat(),
            longitude = longitude.toFloat()
        ))
    }
}