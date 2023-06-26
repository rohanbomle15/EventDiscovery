package com.robo.eventdiscovery

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.robo.eventdiscovery.database.EventDAO
import com.robo.eventdiscovery.database.EventDatabase
import com.robo.eventdiscovery.model.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*


class EventDOATest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var eventDatabase: EventDatabase
    lateinit var eventDAO: EventDAO

    @Before
    fun setUp() {
        eventDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            EventDatabase::class.java
        ).allowMainThreadQueries().build()
        eventDAO = eventDatabase.eventDOA()
    }

    @Test
    fun insertEvent_updatesEventDatabase() = runBlocking {
        val event = createEvent()
        eventDAO.insertEvent(event)

        val result = eventDAO.getEvents()

        Assert.assertEquals(1, result.size)
    }

    @Test
    fun deleteEvent_updatesEventDatabase() = runBlocking {
        val event = createEvent()
        eventDAO.insertEvent(event)
        eventDAO.deleteEvent(event)

        val result = eventDAO.getEvents()

        Assert.assertEquals(0, result.size)
    }

    @Test
    fun searchEvent_returnMatchingEvents() = runBlocking {
        eventDAO.insertEvent(event = createEvent())

        val result = eventDAO.searchEvents("Twain")

        Assert.assertEquals(1, result.size)
        Assert.assertEquals( "Shania Twain: Queen Of Me Tour", result[0].name)
    }

    @After
    fun tearDown() {
        eventDatabase.close()
    }

    private fun createEvent(): Event {
        return Event(
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
    }
}
