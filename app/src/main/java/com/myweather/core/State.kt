package com.myweather.core

import com.myweather.domain.model.Weather

sealed class State {

    data class Loading(val visibility: Boolean) : State()

    data class Success(val result: Weather) : State()

    data class Error(val error: Throwable) : State()
}