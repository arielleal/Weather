package com.myweather.core

import android.content.SharedPreferences

class CacheStorageImpl(
    private val sharedPreferences: SharedPreferences
) : CacheStorage {
    override fun get(key: String): String? =
        sharedPreferences.getString(key, null)

    override fun set(key: String, value: String) =
        sharedPreferences.edit().putString(key, value).apply()

}