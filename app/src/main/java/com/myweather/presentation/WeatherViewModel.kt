package com.myweather.presentation

import androidx.lifecycle.viewModelScope
import com.myweather.core.ViewModelState
import com.myweather.data.model.WeatherRequest
import com.myweather.domain.usecase.StorageSearchUseCase
import com.myweather.domain.usecase.WeatherUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

private const val CITY_KEY = "city"

class WeatherViewModel(
    private val weatherUseCase: WeatherUseCase,
    private val storageUseCase: StorageSearchUseCase,
) : ViewModelState<WeatherState>(WeatherState()) {

    private var _cityName: String? = null

    private fun getWeather(request: WeatherRequest) {
        viewModelScope.launch {
            weatherUseCase(request)
                .onStart {
                    setState { state -> state.showLoading()}
                }
                .catch {
                    setState { state -> state.setError() }
                }
                .collect {
                    setState { state -> state.setSuccess(it) }
                }
        }
    }

    fun updateCityName(cityName: String?) {
        _cityName = cityName
    }

    fun getWeather() {
        if (_cityName.isNullOrEmpty().not()) {
            updateStorage(_cityName!!)
            getWeather(
                WeatherRequest(
                    cityName = _cityName
                )
            )
        }
    }

    fun getStorage() {
        _cityName = storageUseCase.get(CITY_KEY)
        getWeather()
    }

    private fun updateStorage(cityName: String) {
        storageUseCase.set(CITY_KEY, cityName)
    }

    fun setLocation(longitude: Double, latitude: Double) {
        getWeather(WeatherRequest(
            latitude = latitude.toFloat(),
            longitude = longitude.toFloat()
        ))
    }
}