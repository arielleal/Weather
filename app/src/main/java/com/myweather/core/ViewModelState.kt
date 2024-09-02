package com.myweather.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class ViewModelState<S: WeatherViewState>(initialState: S) : ViewModel() {

    private val stateViewModel = MutableLiveData(initialState)
    val state: LiveData<S> = stateViewModel

    protected fun setState(state: (S) -> S) {
        stateViewModel.value = state(stateViewModel.value!!)
    }
}

interface WeatherViewState