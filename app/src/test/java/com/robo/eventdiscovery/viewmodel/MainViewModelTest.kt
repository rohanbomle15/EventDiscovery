package com.robo.eventdiscovery.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.robo.eventdiscovery.model.*
import okhttp3.internal.http.HTTP_OK
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.*


@RunWith(MockitoJUnitRunner::class)
internal class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mockWebServer: MockWebServer

    @Mock
    private lateinit var database: EventDatabase

    @Mock
    private lateinit var filteredEventsObserver: Observer<List<Event>>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mainViewModel = MainViewModel(database = database)
        mainViewModel.filteredEventsLiveData.observeForever(filteredEventsObserver)
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @Test
    fun getEventDetails() {
        val response = MockResponse()
            .setResponseCode(HTTP_OK)
            .setBody(localEvents.toString())
        mockWebServer.enqueue(response)

        mainViewModel.getEventDetails()
        mockWebServer.takeRequest()

        val captor = ArgumentCaptor.forClass(Event::class.java)
        captor.run {
            verify(filteredEventsObserver, times(1)).onChanged(listOf(capture()))
        }
    }

    fun tearDown() {
        database.close()
        mockWebServer.shutdown()
    }

}

val localEvents = listOf(
    Event(
        "Shania Twain: Queen Of Me Tour",
        "event",
        "vvG1fZ949qhf4C",
        false,
        "https://concerts.livenation.com/shania-twain-queen-of-me-tour-noblesville-indiana-07-15-2023/event/05005D55DD6D454E",
        "en-us",
        listOf(ImageSpec("3_2","https://s1.ticketm.net/dam/a/1d1/47cc9b10-4904-4dec-b1d6-539e44a521d1_1825531_RETINA_PORTRAIT_3_2.jpg",640,427,false)),
        pleaseNote = "General parking is included in the final purchase price. Premier Parking is also available.",
        EventDate(Start("","", Date())),
        listOf(PriceRange("","",25.55, 50.10))
    )
)