package com.example.converter_demo_app.helper

import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import com.example.converter_demo_app.helper.BaseConstants.defaultTimeoutTime
import junit.framework.AssertionFailedError

fun ViewInteraction.idleUntil(
    assertions: ViewAssertion,
    timeoutMs: Long = defaultTimeoutTime
) {
    lateinit var lastFail: Throwable
    val generatedDescription = { lastFail.message!! }

    idleUntil(generatedDescription = generatedDescription, timeoutMs = timeoutMs) {
        try {
            check(assertions)
            true
        } catch (e: Throwable) {
            lastFail = e
            false
        }
    }
}

/**
 * Idle until timeout gone or view visibility condition is met
 */
fun ViewInteraction.idleUntilVisibility(
    visibility: Visibility,
    timeoutMs: Long
) {
    idleUntil(
        matches(withEffectiveVisibility(visibility)),
        timeoutMs
    )
}

/**
 * Idle until timeout gone or view selection condition is met
 */
fun ViewInteraction.idleUntilSelected(
    timeoutMs: Long
) {
    idleUntil(
        matches(isSelected()),
        timeoutMs
    )
}

/**
 * Verify view is effectively : Visible
 */
fun ViewInteraction.isVisible(): Boolean {
    try {
        assertViewVisibility(visibility = Visibility.VISIBLE)
        return true
    } catch (_: AssertionFailedError) {
    } catch (_: Exception) {
    }
    return false
}

fun ViewInteraction.isOnDisplay(): Boolean {
    try {
        check(matches(isDisplayed()))
        return true
    } catch (_: AssertionFailedError) {
    } catch (_: Exception) {
    }
    return false
}

fun ViewInteraction.hasSelected(): Boolean {
    try {
        check(matches(isSelected()))
        return true
    } catch (_: AssertionFailedError) {
    } catch (_: Exception) {
    }
    return false
}
/**
 * Give a chance to view to become visible
 */
fun ViewInteraction.idleUntilVisible(timeoutMs: Long = defIdleTimeoutMs) =
    idleUntilVisibility(Visibility.VISIBLE, timeoutMs)

private fun assertViewVisibility(visibility: Visibility): ViewAssertion? {
    return matches(withEffectiveVisibility(visibility))
}



