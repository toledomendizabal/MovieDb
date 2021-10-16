package com.test.moviedb

import android.app.Application
import com.test.moviedb.di.apiModule
import com.test.moviedb.di.mainModule
import com.test.moviedb.di.movieModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    private val modules = listOf(apiModule, movieModule, mainModule)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(modules)
        }
    }
}