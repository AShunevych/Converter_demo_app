package com.example.converter_demo_app

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.converter_demo_app.helper.getResourceString
import com.example.converter_demo_app.robots.calculatorRobot
import com.example.converter_demo_app.robots.converterRobot
import com.example.converter_demo_app.robots.navigation.keyboardRobot
import com.example.converter_demo_app.robots.navigation.navigationRobot
import org.junit.Rule
import org.junit.Test

class AssessmentTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ActivityMain::class.java)

    // TODO fix and refactor code using Robot Pattern
    /**
     * Further code contains multiple error and mistakes
     * You are free to choose either to rewrite code  first and fix or vice versa
     * Requirements: Code must be stable and run with minimum idlers as possible - Thread.sleep() e.t.c.
     */
    @Test
    fun test_create_simple_calculation() {
        converterRobot {
            verify()
        }
        navigationRobot {
            accessCalculator()
        }
        calculatorRobot {
            verify()
        }
        keyboardRobot {
            verifyKeyboardElements(isCalculatorsKeyboard = true)

            clickButtonsInList(list = buttonList)
            solveResult()
        }
        calculatorRobot {
            verifyEnteredText(enteredText = "8+11")
            verifyResult(result = "19.0")
        }
    }

    /*
        onView(withId(R.id.viewPager)).check(matches(isDisplayed()))

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
            .check(matches(ViewMatchers.withText("19")))*/

    @Test
    fun test_task() {
        converterRobot {
            verify()
        }
        // verifying changing category -> units has been changed
        navigationRobot {
            // User is performing swipe right to change category
            swipePagerToNextCategory()
            assertDistanceTabHasBeenSelected()
            converterRobot {
                verifyDefaultDistanceUnitsWereLoaded()
            }
        }
        // User is performing swipe left to change category
        navigationRobot {
            swipePagerToPrevCategory()
            assertWeightTabHasBeenSelected()
            converterRobot {
                verifyDefaultWeightUnitsWereLoaded()
            }
        }
        // User scrolls to “Temperature” category
        navigationRobot {
            selectTemperatureTab()
        }
        converterRobot {
            valueUnitWithText(value = getResourceString(R.string.unit_c))
            resultUnitWithText(result = getResourceString(R.string.unit_c))
        }
        keyboardRobot {
            assertPlusMinusButtonIsEnabled()
        }
        converterRobot {
            // User selects basic unit to convert
            setBasicValueForConvert(item = "Celsius")
            valueUnitWithText(value = getResourceString(R.string.unit_c))
            // User changes basic unit to another one
            setTargetValueForConvert(item = "Kelvin")
            resultUnitWithText(result = getResourceString(R.string.unit_k))

            // User enters value using keyboard and validate converted result
            keyboardRobot {
                clickButtonOne()
                clickButtonFive()
            }

            assertResult(expectedResult = "288.15")
            // User changes target unit to another one
            setBasicValueForConvert(item = "Kelvin")
            valueUnitWithText(value = getResourceString(R.string.unit_k))
            // User changes basic unit to another one
            setTargetValueForConvert(item = "Celsius")
            valueUnitWithText(value = getResourceString(R.string.unit_c))
            // Users see converted data
            assertResult(expectedResult = "-258.15")
        }
    }
}
