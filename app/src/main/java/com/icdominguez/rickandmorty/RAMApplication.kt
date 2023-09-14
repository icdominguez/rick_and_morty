package com.icdominguez.rickandmorty

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RAMApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
