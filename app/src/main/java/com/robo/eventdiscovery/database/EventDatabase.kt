package com.robo.eventdiscovery.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.robo.eventdiscovery.model.Event

@Database(entities = [Event::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class EventDatabase: RoomDatabase() {

    abstract fun eventDOA() : EventDAO

    companion object {
        @Volatile
        private var INSTANCE : EventDatabase? = null

        fun getDatabaseInstance(context: Context): EventDatabase {
            if(INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context,
                        EventDatabase::class.java, "EventsDB").build()
                }
            }
            return INSTANCE!!
        }
    }
}