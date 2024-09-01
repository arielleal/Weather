package com.myweather.core

import android.content.Context

private const val DRAWABLE = "drawable"

class ResourceProviderImpl(private val context: Context) : ResourceProvider {
    override fun getIdentifier(name: String): Int {
        return  context.resources.getIdentifier(name, DRAWABLE, context.packageName)
    }
}