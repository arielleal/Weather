package com.myweather.util

import com.myweather.domain.model.Results
import com.myweather.domain.model.Weather

fun weatherResponseStub() = Weather(
    type = "",
    weatherResults = Results(
        temp = 0.0,
        description = "",
        city = "",
        humidity = "",
        windSpeedy = "",
        conditionSlug = 0,
        time = "",
        forecastList = listOf()
    )
)