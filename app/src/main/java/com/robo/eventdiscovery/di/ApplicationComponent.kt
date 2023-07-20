package com.robo.eventdiscovery.di

import android.content.Context
import com.robo.eventdiscovery.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }
}