package com.github.aivancioglo.resttest.verifiers

import io.restassured.response.Response
import org.junit.jupiter.api.Assertions

/**
 * NotEquals verifier class.
 */
class NotEqual : Verifier {
    private var expected: Any? = null
    private var actual: Any? = null
    private var message: String = ""

    /**
     * @constructor is creating variables of unexpected and actual values.
     * @param unexpected value.
     * @param actual array of matchers.
     */
    constructor(unexpected: Any, actual: Any) {
        this.expected = unexpected
        this.actual = actual
    }

    /**
     * @constructor is creating variables of unexpected and actual values.
     * @param unexpected value.
     * @param actual array of matchers.
     * @param message of fail.
     */
    constructor(unexpected: Any, actual: Any, message: String) {
        this.expected = unexpected
        this.actual = actual
        this.message = message
    }

    /**
     * Verify response.
     *
     * @param response of your request.
     */
    override fun verify(response: Response) {
        if (message == "")
            Assertions.assertNotEquals(expected, actual)
        else
            Assertions.assertNotEquals(expected, actual, message)
    }
}