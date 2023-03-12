package com.example.converter_demo_app.robots

import androidx.test.espresso.ViewInteraction
import com.example.converter_demo_app.R
import com.example.converter_demo_app.helper.defIdleTimeoutMs
import com.example.converter_demo_app.helper.haveText
import com.example.converter_demo_app.helper.idleUntilTextMatches
import com.example.converter_demo_app.helper.idleUntilVisible
import com.example.converter_demo_app.helper.isVisible
import com.example.converter_demo_app.helper.onViewWithId

fun calculatorRobot(func: CalculatorRobot.() -> Unit) = CalculatorRobot().apply { func() }

@SuppressWarnings
class CalculatorRobot {
    private val calculatorValueView: ViewInteraction = onViewWithId(R.id.calcValue)
    private val calculatorResult: ViewInteraction = onViewWithId(R.id.calcResult)

    fun verify() {
        calculatorValueView.idleUntilVisible(defIdleTimeoutMs)
        calculatorResult.isVisible()
    }

    fun verifyEnteredText(enteredText: String) = calculatorValueView.haveText(text = enteredText)

    fun verifyResult(result: String) = calculatorResult.idleUntilTextMatches(text = result)
}
