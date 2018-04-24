package com.github.aivancioglo.resttest.http

import java.util.*

/**
 * Request repeating session.
 */
@Deprecated("This old Repeater class is deprecated.", ReplaceWith("Repeater class."))
class Session {
    private var loopsCount = 0
    private var timeoutMls = 0
    private var delayMls = 0

    /**
     * Set maximum loops.
     *
     * @param loopsCount of your requests.
     */
    @Deprecated("This is method of deprecated Repeater class.")
    fun tries(loopsCount: Int) = apply {
        this.loopsCount = loopsCount
    }

    /**
     * Set your timeout.
     *
     * @param sec to finish your request
     * @param mls to finish your request
     */
    @Deprecated("This is method of deprecated Repeater class.")
    @JvmOverloads
    fun timeout(sec: Int, mls: Int = 0) = apply {
        if (sec <= 0 && mls <= 0)
            throw Exception("Timeout should be more than 0!")

        timeoutMls = mls + sec * 1000
    }

    /**
     * Set your request delay.
     *
     * @param sec that should wait after request.
     * @param mls that should wait after request.
     */
    @Deprecated("This is method of deprecated Repeater class.")
    @JvmOverloads
    fun every(sec: Int, mls: Int = 0) = apply {
        if (sec < 0)
            throw Exception("Seconds should not be less than 0!")

        if (mls < 0)
            throw Exception("Milliseconds should not be less than 0!")

        delayMls = mls + sec * 1000
    }

    /**
     * This function is executing your request until passing or timeout.
     *
     * @param request to execute.
     */
    @Deprecated("This is method of deprecated Repeater class.")
    fun until(request: Runnable) {
        var loop = loopsCount
        val currentTime = Date().time

        while (true) {
            try {
                request.run()
                return
            } catch (e: AssertionError) {
                if (loopsCount > 0 && --loop == 0) {
                    throw AssertionError(e)
                }

                if (timeoutMls > 0 && Date().time - currentTime > timeoutMls) {
                    throw AssertionError("Timeout! ${e.message}")
                }

                if (delayMls > 0)
                    try {
                        Thread.sleep(delayMls.toLong())
                    } catch (e1: Exception) {
                    }
            }
        }
    }
}