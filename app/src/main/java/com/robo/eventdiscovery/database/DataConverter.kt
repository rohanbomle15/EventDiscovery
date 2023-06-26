package com.robo.eventdiscovery.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.robo.eventdiscovery.model.EventDate
import com.robo.eventdiscovery.model.ImageSpec
import com.robo.eventdiscovery.model.PriceRange
import java.util.*

class DataConverter {
    @TypeConverter
    fun fromImageSpecList(value: List<ImageSpec>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ImageSpec>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toImageSpecList(value: String): List<ImageSpec> {
        val gson = Gson()
        val type = object : TypeToken<List<ImageSpec>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromEventDates(value: EventDate): String {
        val gson = Gson()
        val type = object : TypeToken<EventDate>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toEventDates(value: String): EventDate {
        val gson = Gson()
        val type = object : TypeToken<EventDate>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromPriceRangeList(value: List<PriceRange>): String {
        val gson = Gson()
        val type = object : TypeToken<List<PriceRange>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toPriceRangeList(value: String): List<PriceRange> {
        val gson = Gson()
        val type = object : TypeToken<List<PriceRange>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromDateToLong(value: Date): Long {
        return value.time
    }

    @TypeConverter
    fun fromLongToDate(vale: Long): Date {
        return Date(vale)
    }
}