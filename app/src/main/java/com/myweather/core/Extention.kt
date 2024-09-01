package com.myweather.core

private const val CELSIUS_UNICODE = "\u2103"

fun Double.toTemperature(): String {
    return this.toInt().toString().plus(CELSIUS_UNICODE)
}