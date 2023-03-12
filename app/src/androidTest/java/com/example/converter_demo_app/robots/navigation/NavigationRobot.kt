package com.example.converter_demo_app.robots.navigation

import com.example.converter_demo_app.R
import com.example.converter_demo_app.helper.clickView
import com.example.converter_demo_app.helper.hasSelected
import com.example.converter_demo_app.helper.idleUntilSelected
import com.example.converter_demo_app.helper.onViewWithId
import com.example.converter_demo_app.helper.onViewWithText
import com.example.converter_demo_app.helper.swipePagerLeft
import com.example.converter_demo_app.helper.swipePagerRight
import org.junit.Assert

fun navigationRobot(func: NavigationRobot.() -> Unit) = NavigationRobot().apply { func() }

class NavigationRobot {
    private val viewPagerView = onViewWithId(R.id.viewPager)
    private val distanceTab = onViewWithText(itemText = R.string.distance_button)
    private val temperatureTab = onViewWithText(itemText = R.string.temperature_button)
    private val weightTab = onViewWithText(itemText = R.string.mass_button)

    private val calculatorButton = onViewWithId(R.id.calculator_button)

    fun swipePagerToNextCategory() {
        viewPagerView.swipePagerLeft()
    }

    fun swipePagerToPrevCategory() {
        viewPagerView.swipePagerRight()
    }

    fun assertWeightTabHasBeenSelected() {
        weightTab.idleUntilSelected(timeoutMs = 1000)

        Assert.assertTrue(
            "Weight tab wasn't selected",
            weightTab.hasSelected()
        )
    }

    fun assertDistanceTabHasBeenSelected() {
        distanceTab.idleUntilSelected(timeoutMs = 1000)

        Assert.assertTrue(
            "Distance tab wasn't selected",
            distanceTab.hasSelected()
        )
    }

    fun selectTemperatureTab() {
        repeat(5) {
            Thread.sleep(1000)
            if (temperatureTab.hasSelected()) {
                return@repeat
            }
            viewPagerView.swipePagerLeft()
        }
    }

    fun accessCalculator() {
        calculatorButton.clickView()
    }
}
