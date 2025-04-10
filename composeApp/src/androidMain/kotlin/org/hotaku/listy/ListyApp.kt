package org.hotaku.listy

import android.app.Application
import org.hotaku.listy.di.initKoin
import org.koin.android.ext.koin.androidContext

class ListyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ListyApp)
        }
    }
}