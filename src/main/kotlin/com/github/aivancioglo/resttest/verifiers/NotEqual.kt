package com.github.aivancioglo.resttest.verifiers

import io.restassured.response.Response
import org.junit.jupiter.api.Assertions

class NotEqual : Verifier {
    private var expected: Any? = null
    private var actual: Any? = null
    private var message: String = ""

    constructor(expected: Any, actual: Any) {
        this.expected = expected
        this.actual = actual
    }

    constructor(expected: Any, actual: Any, message: String) {
        this.expected = expected
        this.actual = actual
        this.message = message
    }

    override fun verify(response: Response) {
        if (message == "")
            Assertions.assertNotEquals(expected, actual)
        else
            Assertions.assertNotEquals(expected, actual, message)
    }
}