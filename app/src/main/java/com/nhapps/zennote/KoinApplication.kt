package com.nhapps.zennote

import android.app.Application
import com.nhapps.zennote.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidContext(this@KoinApplication)
            modules(appModule)
        }
    }
}