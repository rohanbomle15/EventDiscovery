package com.robo.eventdiscovery

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EventDiscoveryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}