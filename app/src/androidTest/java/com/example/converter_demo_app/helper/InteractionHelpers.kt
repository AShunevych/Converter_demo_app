package com.example.converter_demo_app.helper

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf

fun ViewInteraction.clickView(): ViewInteraction = perform(click())

fun onViewWithId(viewId: Int): ViewInteraction = onView(ViewMatchers.withId(viewId))

fun onViewWithText(@IdRes itemText: Int): ViewInteraction = onView(ViewMatchers.withText(itemText))

fun ViewInteraction.swipePagerLeft(): ViewInteraction = perform(ViewActions.swipeLeft())

fun ViewInteraction.swipePagerRight(): ViewInteraction = perform(ViewActions.swipeRight())

fun clickOnSpinnerItem(@IdRes itemText: String): ViewInteraction = Espresso.onData(allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`(itemText))).perform(click())
