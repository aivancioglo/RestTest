package com.github.aivancioglo.resttest.verifiers

import io.restassured.response.Response
import org.junit.jupiter.api.Assertions

/**
 * IsTrue verifier class.
 */
class IsTrue : Verifier {
    private var condition: Boolean = false
    private var message: String = ""

    /**
     * @constructor is creating variable of condition values.
     * @param condition value.
     */
    constructor(condition: Boolean) {
        this.condition = condition
    }

    /**
     * @constructor is creating variable of condition values.
     * @param condition value.
     * @param message of fail.
     */
    constructor(condition: Boolean, message: String) {
        this.condition = condition
        this.message = message
    }

    /**
     * Verify response.
     *
     * @param response of your request.
     */
    override fun verify(response: Response) {
        if (message == "")
            Assertions.assertTrue(condition)
        else
            Assertions.assertTrue(condition, message)
    }
}