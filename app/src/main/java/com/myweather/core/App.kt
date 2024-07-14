package com.myweather.core

import android.app.Application
import com.myweather.data.di.DataModule
import com.myweather.domain.di.DomainModule
import com.myweather.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        PresentationModule.loadModule()
        DataModule.loadModule()
        DomainModule.loadModule()
    }
}