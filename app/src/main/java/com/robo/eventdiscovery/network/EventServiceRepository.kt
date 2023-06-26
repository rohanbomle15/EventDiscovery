package com.robo.eventdiscovery.network

class EventServiceRepository(private val eventsServiceHelper: EventsServiceHelper) {
    suspend fun getEventResponse() = eventsServiceHelper.getEventResponse()
    suspend fun getEvents() = eventsServiceHelper.getEvents()
}