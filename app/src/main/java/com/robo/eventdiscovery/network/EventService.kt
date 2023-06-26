package com.robo.eventdiscovery.network

import com.robo.eventdiscovery.model.EventsResponse
import retrofit2.http.GET

interface EventsService {

    @GET("events.json?countryCode=US&apikey=DW0E98NrxUIfDDtNN7ijruVSm60ryFLX")
    suspend fun getEventResponse(): EventsResponse
}