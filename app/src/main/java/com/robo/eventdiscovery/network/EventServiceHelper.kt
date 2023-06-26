package com.robo.eventdiscovery.network

class EventsServiceHelper(private val eventsService: EventsService) {
    suspend fun getEventResponse() = eventsService.getEventResponse()
    suspend fun getEvents() = eventsService.getEventResponse()._embedded.events
}