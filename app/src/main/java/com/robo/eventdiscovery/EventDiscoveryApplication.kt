package com.robo.eventdiscovery

import android.app.Application
import com.robo.eventdiscovery.di.ApplicationComponent
import com.robo.eventdiscovery.di.DaggerApplicationComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EventDiscoveryApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}