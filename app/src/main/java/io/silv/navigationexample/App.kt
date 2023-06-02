package io.silv.navigationexample

import android.app.Application
import io.silv.navigationexample.nav.navModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App.applicationContext)
            androidLogger()
            modules(appModule, navModule)
        }
    }
}