package com.robo.eventdiscovery

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun testEventRecyclerView_showsEventsList() {
        onView(withId(R.id.rvEvents)).perform(click())
    }

    @Test
    fun testSearchEvent_showsEventsList() {
        onView(withId(R.id.etSearchEvent)).perform(click())
        onView(withId(R.id.etSearchEvent)).perform(typeText("Hamilton"))
    }
}