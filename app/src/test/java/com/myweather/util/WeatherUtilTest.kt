package com.myweather.util

import com.myweather.domain.model.Results
import com.myweather.domain.model.Weather

fun weatherResponseStub() = Weather(
    type = "",
    weatherResults = Results(
        temp = "",
        city = "",
        humidity = "",
        windSpeedy = "",
        moonPhase = "",
        forecastList = listOf()
    )
)