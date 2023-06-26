package com.robo.eventdiscovery.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object EventsRetrofitBuilder {
    private const val BASE_URL = "https://app.ticketmaster.com/discovery/v2/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val apiService: EventsService = getRetrofit().create(EventsService::class.java)
}