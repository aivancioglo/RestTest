package com.github.aivancioglo.resttest.http

class Repeater {
    private var loopsCount = 0
    private var timeoutMls = 0L
    private var delayMls = 0L

    /**
     * Set maximum loops.
     *
     * @param loopsCount of your requests.
     */
    fun tries(loopsCount: Int) = apply {
        if (loopsCount < 0)
            throw Exception("Tries should not be less than 0!")

        this.loopsCount = loopsCount
    }

    /**
     * Set your timeout.
     *
     * @param sec to finish your request
     */
    fun timeout(sec: Int) = apply {
        if (sec < 0)
            throw Exception("Timeout seconds should not be less than 0!")

        timeoutMls = (sec * 1000).toLong()
    }

    /**
     * Set your timeout.
     *
     * @param sec to finish your request
     */
    fun timeout(sec: Double) = apply {
        if (sec < 0)
            throw Exception("Timeout seconds should not be less than 0!")
        timeoutMls = (sec * 1000).toLong()
    }

    /**
     * Set your request delay.
     *
     * @param sec that should wait after request.
     * @param mls that should wait after request.
     */
    @JvmOverloads
    fun every(sec: Int, mls: Int = 0) = apply {
        if (sec < 0)
            throw Exception("Delay seconds should not be less than 0!")

        if (mls < 0)
            throw Exception("Delay milliseconds should not be less than 0!")

        delayMls = mls + sec.toLong() * 1000
    }

    /**
     * Set your request delay.
     *
     * @param sec that should wait after request.
     */
    fun every(sec: Double) = apply {
        if (sec < 0)
            throw Exception("Delay seconds should not be less than 0!")

        delayMls = (sec * 1000).toLong()
    }

    /**
     * This function is executing your request until passing or timeout.
     *
     * @param request to execute.
     */
    fun until(request: Runnable) {
        var message = ""
        var error: AssertionError? = null
        var loop = loopsCount
        val startTime = System.currentTimeMillis()

        class Cycle : Thread() {
            override fun run() {
                while (true) {
                    try {
                        request.run()
                        return
                    } catch (e: AssertionError) {
                        error = e

                        if (loopsCount > 0 && --loop == 0 && message == "") {
                            message = "Failed after $loopsCount try(ies)." + e.message!!.replace(Regex("java.lang.AssertionError: 1 expectation failed."), "")
                            return
                        }

                        if (timeoutMls > 0 && System.currentTimeMillis() - startTime > timeoutMls && message == "") {
                            message = "Repeater timeout!" + e.message!!.replace(Regex("java.lang.AssertionError: 1 expectation failed."), "")
                            return
                        }

                        if (delayMls > 0)
                            try {
                                Thread.sleep(delayMls)
                            } catch (e1: Exception) {
                            }
                    }
                }
            }
        }

        val cycle = Cycle()
        cycle.start()

        if (timeoutMls > 0) {
            while (System.currentTimeMillis() - startTime < timeoutMls && cycle.isAlive) {
            }

            if (cycle.isAlive) {
                message = if (error != null)
                    "Repeater timeout!" + error!!.message!!.replace(Regex("java.lang.AssertionError: 1 expectation failed."), "")
                else
                    "Repeater timeout!"

                cycle.interrupt()
            }
        } else
            cycle.join()

        if (message != "")
                throw AssertionError(message)
    }
}
