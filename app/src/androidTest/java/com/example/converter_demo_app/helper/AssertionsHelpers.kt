package com.example.converter_demo_app.helper

import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withText

/**
 * Assert view have text
 */
fun ViewInteraction.haveText(text: String): ViewAssertion =
    ViewAssertions.matches(withText(text))
