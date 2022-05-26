package com.apdallahy.newsapp

import android.app.Application
import com.apdallahy.newsapp.modules.koinModules
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@HiltAndroidApp
class NewsApplication:Application() {
    override fun onCreate() {
        super.onCreate()
//        startKoin {
//            androidContext(this@NewsApplication)
//            modules(koinModules)
//        }
    }
}