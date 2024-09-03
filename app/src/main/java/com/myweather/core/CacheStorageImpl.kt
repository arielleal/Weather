package com.myweather.core

import android.content.SharedPreferences

private const val CITY_KEY = "city"

class CacheStorageImpl(
    private val sharedPreferences: SharedPreferences
) : CacheStorage {
    override fun get(): String? =
        sharedPreferences.getString(CITY_KEY, null)

    override fun set(cityName: String) =
        sharedPreferences.edit().putString(CITY_KEY, cityName).apply()

}