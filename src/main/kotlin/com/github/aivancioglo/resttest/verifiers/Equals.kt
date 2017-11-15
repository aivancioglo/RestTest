package com.github.aivancioglo.resttest.verifiers

import io.restassured.response.Response
import org.junit.jupiter.api.Assertions.assertEquals

/**
 * Equals verifier class.
 */
class Equals : Verifier {
    private var expected: Any? = null
    private var actual: Any? = null
    private var message: String = ""

    /**
     * @constructor is creating variables of expected and actual values.
     * @param expected value.
     * @param actual array of matchers.
     */
    constructor(expected: Any, actual: Any) {
        this.expected = expected
        this.actual = actual
    }

    /**
     * @constructor is creating variables of expected and actual values.
     * @param expected value.
     * @param actual array of matchers.
     * @param message of fail.
     */
    constructor(expected: Any, actual: Any, message: String) {
        this.expected = expected
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
            assertEquals(expected, actual)
        else
            assertEquals(expected, actual, message)
    }
}