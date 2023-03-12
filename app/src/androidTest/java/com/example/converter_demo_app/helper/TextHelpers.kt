package com.example.converter_demo_app.helper

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withText

fun ViewInteraction.idleUntilTextMatches(
   text:String, timeoutMs: Long = defIdleTimeoutMs
) {
    idleUntil(
        ViewAssertions.matches(withText(text)),
        timeoutMs
    )
}