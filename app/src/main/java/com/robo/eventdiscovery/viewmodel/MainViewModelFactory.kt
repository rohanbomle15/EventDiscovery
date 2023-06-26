package com.robo.eventdiscovery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robo.eventdiscovery.database.EventDatabase

class MainViewModelFactory(val database: EventDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(database) as T
    }
}