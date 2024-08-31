package com.myweather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myweather.core.State
import com.myweather.domain.model.Weather

abstract class WeatherState : ViewModel() {

    private val stateViewModel = MutableLiveData<State>()
    val state: LiveData<State>
        get() = stateViewModel

    protected fun setSuccess(result: Weather) {
        stateViewModel.postValue(State.Loading(visibility = false))
        stateViewModel.postValue(State.Success(result))
    }

    protected fun setError(error: Throwable) {
        stateViewModel.postValue(State.Loading(visibility = false))
        stateViewModel.postValue(State.Error(error))
    }

    protected fun showLoading() = stateViewModel.postValue(State.Loading(visibility = true))
}