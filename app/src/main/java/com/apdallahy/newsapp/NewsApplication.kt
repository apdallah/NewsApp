package com.apdallahy.newsapp

import android.app.Application
import com.apdallahy.newsapp.modules.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewsApplication)
            modules(koinModules)
        }
    }
}