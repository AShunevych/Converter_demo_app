package com.example.converter_demo_app.helper

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry

private val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

/**
 * Get string from the resources by id
 */
fun getResourceString(id: Int): String {
    return targetContext.resources.getString(id)
}