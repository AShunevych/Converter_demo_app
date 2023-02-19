package com.example.converter_demo_app

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class AssessmentTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ActivityMain::class.java)

    /**
     * Further code contains multiple error and mistakes
     * Requirements: Code must be stable and run with minimum idlers as possible - Thread.sleep() e.t.c.
     * You are free to choose either to rewrite code  first and fix or vice versa
     */
    // TODO fix and refactor code using Robot Pattern
    @Test
    fun test_create_simple_calculation() {
        Espresso.onView(ViewMatchers.withId(R.id.viewPager))
            .check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        Espresso.onView(ViewMatchers.withId(R.id.calculator_button))
            .check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.viewPager))
            .check(matches(isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.calcValue))
            .check(matches(isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.calcResult))
            .check(matches(isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.button_eight))
            .check(matches(isDisplayed()))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.but_plus))
            .check(matches(isDisplayed()))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.but_one))
            .check(matches(isDisplayed()))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.but_one))
            .check(matches(isDisplayed()))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.calcResult))
            .check(matches(isDisplayed()))
            .check(matches(ViewMatchers.withText("8+11")))

        Espresso.onView(ViewMatchers.withId(R.id.but_solve))
            .check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
            .perform(ViewActions.click())


        // Result that you should have.
        Espresso.onView(ViewMatchers.withId(R.id.calcResult))
            .check(matches(isDisplayed()))
            .check(matches(ViewMatchers.withText("19")))
    }

    // TODO write test according to test case
}