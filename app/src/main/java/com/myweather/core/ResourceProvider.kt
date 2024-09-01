package com.myweather.core

interface ResourceProvider {
    fun getIdentifier(name: String): Int
}