package com.example.converter_demo_app.robots

import androidx.annotation.StringRes
import com.example.converter_demo_app.R
import com.example.converter_demo_app.helper.clickOnSpinnerItem
import com.example.converter_demo_app.helper.clickView
import com.example.converter_demo_app.helper.defIdleTimeoutMs
import com.example.converter_demo_app.helper.haveText
import com.example.converter_demo_app.helper.idleUntilTextMatches
import com.example.converter_demo_app.helper.idleUntilVisible
import com.example.converter_demo_app.helper.isVisible
import com.example.converter_demo_app.helper.onViewWithId
import com.example.converter_demo_app.robots.navigation.keyboardRobot

fun converterRobot(func: ConverterRobot.() -> Unit) = ConverterRobot().apply { func() }

class ConverterRobot {
    private val calculatorButton = onViewWithId(R.id.calculator_button)
    private val viewPagerView = onViewWithId(R.id.viewPager)

    private val valueView = onViewWithId(R.id.valueEdit)
    private val resultView = onViewWithId(R.id.resultView)

    private val spinnerValue = onViewWithId(R.id.spinner_value)
    private val spinnerResult = onViewWithId(R.id.spinner_result)

    private val valueUnit = onViewWithId(R.id.valueUnit)
    private val resultUnit = onViewWithId(R.id.resultUnit)

    fun verify() {
        viewPagerView.idleUntilVisible(defIdleTimeoutMs)
        calculatorButton.isVisible()
        valueView.isVisible()
        resultView.isVisible()
        spinnerValue.isVisible()
        spinnerResult.isVisible()
        valueUnit.isVisible()
        resultUnit.isVisible()

        keyboardRobot {
            verifyKeyboardElements(isConverterKeyboard = true)
            assertPlusMinusButtonIsDisabled()
        }
    }

    fun verifyDefaultDistanceUnitsWereLoaded() {
        valueUnitWithText(value = "mm")
        resultUnitWithText(result = "mm")
    }

    fun verifyDefaultWeightUnitsWereLoaded() {
        valueUnitWithText(value = "mg")
        resultUnitWithText(result = "mg")
    }

    fun valueUnitWithText(value: String) =
        valueUnit.haveText(text = value)

    fun resultUnitWithText(result: String) =
        resultUnit.haveText(text = result)

    fun setBasicValueForConvert(@StringRes item: String) {
        spinnerValue.clickView()
        clickOnSpinnerItem(itemText = item)
    }

    fun setTargetValueForConvert(@StringRes item: String) {
        spinnerResult.clickView()
        clickOnSpinnerItem(itemText = item)
    }

    fun assertResult(expectedResult: String) {
        resultView.idleUntilTextMatches(text = expectedResult)
    }
}