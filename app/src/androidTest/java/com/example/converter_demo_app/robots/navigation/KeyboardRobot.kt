package com.example.converter_demo_app.robots.navigation

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.converter_demo_app.R
import com.example.converter_demo_app.helper.clickView
import com.example.converter_demo_app.helper.isOnDisplay
import com.example.converter_demo_app.helper.onViewWithId
import org.junit.Assert

fun keyboardRobot(func: KeyboardRobot.() -> Unit) = KeyboardRobot().apply { func() }

class KeyboardRobot {

    private val buttonOne: ViewInteraction = onViewWithId(R.id.but_one)
    private val buttonTwo: ViewInteraction = onViewWithId(R.id.but_two)
    private val buttonThree: ViewInteraction = onViewWithId(R.id.but_three)
    private val buttonFour: ViewInteraction = onViewWithId(R.id.but_four)
    private val buttonFive: ViewInteraction = onViewWithId(R.id.but_five)
    private val buttonSix: ViewInteraction = onViewWithId(R.id.but_six)
    private val buttonSeven: ViewInteraction = onViewWithId(R.id.but_seven)
    private val buttonEight: ViewInteraction = onViewWithId(R.id.button_eight)
    private val buttonNine: ViewInteraction = onViewWithId(R.id.but_nine)
    private val buttonZero: ViewInteraction = onViewWithId(R.id.button_zero)
    private val buttonDZero: ViewInteraction = onViewWithId(R.id.button_dzero)
    private val buttonDecimal: ViewInteraction = onViewWithId(R.id.button_decimal)
    private val buttonClear: ViewInteraction = onViewWithId(R.id.but_clearView)
    private val buttonPercent: ViewInteraction = onViewWithId(R.id.but_percent)
    private val buttonDivide: ViewInteraction = onViewWithId(R.id.but_divide)
    private val buttonMultiply: ViewInteraction = onViewWithId(R.id.but_multiply)
    private val buttonMinus: ViewInteraction = onViewWithId(R.id.but_minus)
    private val buttonSolve: ViewInteraction = onViewWithId(R.id.but_solve)
    private val buttonPlus: ViewInteraction = onViewWithId(R.id.but_plus)
    private val buttonBracers: ViewInteraction = onViewWithId(R.id.but_duzhky)
    private val plusMinusButton = onViewWithId(R.id.button_plus_minus)

    val buttonList = listOf(
        buttonEight, buttonPlus, buttonOne, buttonOne
    )

    fun verifyKeyboardElements(isCalculatorsKeyboard: Boolean = false, isConverterKeyboard: Boolean = false) {
        // this views id's are shared between keyboards.
        buttonOne.isOnDisplay()
        buttonTwo.isOnDisplay()
        buttonThree.isOnDisplay()
        buttonFour.isOnDisplay()
        buttonFive.isOnDisplay()
        buttonSix.isOnDisplay()
        buttonSeven.isOnDisplay()
        buttonEight.isOnDisplay()
        buttonNine.isOnDisplay()
        buttonZero.isOnDisplay()
        buttonDecimal.isOnDisplay()

        // Calculator keyboard have dozens specific button
        if (isCalculatorsKeyboard) {
            buttonDZero.isOnDisplay()
            buttonClear.isOnDisplay()
            buttonPercent.isOnDisplay()
            buttonDivide.isOnDisplay()
            buttonMultiply.isOnDisplay()
            buttonMinus.isOnDisplay()
            buttonSolve.isOnDisplay()
            buttonBracers.isOnDisplay()
            buttonPlus.isOnDisplay()
        }
        // Converter keyboard have one specific button
        if (isConverterKeyboard) {
            plusMinusButton.isOnDisplay()
        }
    }

    fun solveResult() {
        buttonSolve.clickView()
    }

    fun clickButtonsInList(list: List<ViewInteraction>) {
        for (calcButton in list) {
            calcButton.clickView()
            Thread.sleep(500)
        }
    }

    fun clickButtonOne() {
        buttonOne.clickView()
    }

    fun clickButtonFive() {
        buttonFive.clickView()
    }

    private fun plusMinusIsEnabled(): Boolean {
        try {
            plusMinusButton.check(ViewAssertions.matches(ViewMatchers.isEnabled()))
            return true
        } catch (_: Exception) {
        }

        return false
    }

    private fun plusMinusIsDisabled(): Boolean {
        try {
            plusMinusButton.check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
            return true
        } catch (_: Exception) {
        }

        return false
    }

    fun assertPlusMinusButtonIsEnabled() {
        Assert.assertTrue(
            "Plus minus button should be enabled",
            plusMinusIsEnabled()
        )
    }

    fun assertPlusMinusButtonIsDisabled() {
        Assert.assertTrue(
            "Plus minus button should be disabled",
            plusMinusIsDisabled()
        )
    }
}
