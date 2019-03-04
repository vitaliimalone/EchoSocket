package com.vitaliimalone.echosocket

import android.app.Application
import com.vitaliimalone.echosocket.di.appModule
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModule)
    }
}