package com.example.converter_demo_app.helper

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import java.util.concurrent.TimeUnit

val defIdleTimeoutMs = TimeUnit.SECONDS.toMillis(30)
const val defPollingMs = 500L

/**
 * Idle until timeout gone or the condition is met
 */
fun idleUntil(
    timeoutMs: Long = defIdleTimeoutMs,
    pollingDelayMs: Long = defPollingMs,
    generatedDescription: () -> String,
    predicate: () -> Boolean
) {
    runBlocking {
        try {
            withTimeout(timeoutMs) {
                while (true) {
                    if (predicate()) {
                        break
                    }

                    delay(pollingDelayMs)
                }
            }
        } catch (e: TimeoutCancellationException) {
            throw  RuntimeException(
                "Yours Idling condition is timed out $generatedDescription"
            )
        }
    }
}