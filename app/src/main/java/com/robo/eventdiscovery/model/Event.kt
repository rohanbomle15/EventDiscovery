package com.robo.eventdiscovery.model

/*
* Event model class
* */
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Events")
data class Event(
    var name: String,
    var type: String,
    @PrimaryKey
    var id: String,
    var test: Boolean,
    var url: String,
    var locale: String,
    var images: List<ImageSpec>,
    var pleaseNote: String?,
    var dates: EventDate?,
    var priceRanges: List<PriceRange>?
)

data class ImageSpec(
    var ratio: String,
    var url: String,
    var width: Int,
    var height: Int,
    var fallback: Boolean
)

data class Start(
    var localDate: String,
    var localTime: String,
    var dateTime: Date,
)

data class EventDate(
    var start: Start
)

data class PriceRange(
    var type: String,
    var currency: String,
    var min: Double,
    var max: Double
)

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