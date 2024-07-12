package com.myweather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myweather.core.State
import com.myweather.data.model.WeatherRequest
import com.myweather.domain.model.Weather
import com.myweather.domain.usecase.WeatherUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val useCase: WeatherUseCase
) : ViewModel() {

    private val stateViewModel = MutableLiveData<State>()
    val state: LiveData<State>
        get() = stateViewModel

    private fun getWeather(request: WeatherRequest) {
        viewModelScope.launch {
            useCase(request)
                .onStart { showLoading() }
                .catch { handleError(it) }
                .collect { handleSuccess(it) }
        }
    }

    private fun showLoading() = stateViewModel.postValue(State.Loading(visibility = true))

    private fun handleSuccess(result: Weather) {
        stateViewModel.postValue(State.Loading(visibility = false))
        stateViewModel.postValue(State.Success(result))
    }

    private fun handleError(error: Throwable) {
        stateViewModel.postValue(State.Loading(visibility = false))
        stateViewModel.postValue(State.Error(error))
    }
}