package com.robo.eventdiscovery.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.robo.eventdiscovery.model.Event

@Dao
interface EventDAO {

    @Insert
    suspend fun insertEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    @Query("delete from Events")
    suspend fun deleteAllEvents()

    @Query("select * from Events")
    suspend fun getEvents() : List<Event>

    @Query("select * from Events where name like '%' || :searchText || '%'")
    fun searchEvents(searchText : String) : List<Event>
}