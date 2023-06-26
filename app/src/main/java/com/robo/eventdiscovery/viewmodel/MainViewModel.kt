package com.robo.eventdiscovery.viewmodel

import androidx.lifecycle.*
import com.robo.eventdiscovery.database.EventDatabase
import com.robo.eventdiscovery.model.Event
import com.robo.eventdiscovery.model.Resource
import com.robo.eventdiscovery.network.EventsRetrofitBuilder
import com.robo.eventdiscovery.network.EventsServiceHelper
import com.robo.eventdiscovery.network.EventServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val database: EventDatabase): ViewModel() {

    private var eventServiceRepository: EventServiceRepository? = null
    private val _searchedEvents = MutableLiveData<List<Event>>()
    val filteredEventsLiveData: LiveData<List<Event>> get() = _searchedEvents

    fun getEventDetails() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            var localEvents  = database.eventDOA().getEvents()
            if(localEvents.isNullOrEmpty()) {
                eventServiceRepository = EventServiceRepository(EventsServiceHelper(EventsRetrofitBuilder.apiService))
                localEvents = eventServiceRepository?.getEvents()!!
                localEvents.forEach {
                    database.eventDOA().insertEvent(it)
                }
            }
            _searchedEvents.postValue(localEvents)
            emit(Resource.success(localEvents))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, exception.message ?: "Something went wrong! try again later.."))
        }
    }

    fun getEvents(searchText: String) {
        viewModelScope.launch {
            val searchedResult = mutableListOf<Event>()
            withContext(Dispatchers.IO) {
                if(searchText.isNullOrEmpty()) {
                    searchedResult.addAll(database.eventDOA().getEvents())
                } else {
                    searchedResult.addAll(database.eventDOA().searchEvents(searchText))
                }
            }
            _searchedEvents.value = searchedResult
        }
    }
}