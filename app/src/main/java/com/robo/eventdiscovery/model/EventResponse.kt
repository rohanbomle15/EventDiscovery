package com.robo.eventdiscovery.model

data class EventsResponse(
    var _embedded: Events
)

data class Events(
    var events: List<Event>
)