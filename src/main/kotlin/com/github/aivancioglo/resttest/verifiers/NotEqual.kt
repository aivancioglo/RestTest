package com.github.aivancioglo.resttest.verifiers

import io.restassured.response.Response
import org.junit.jupiter.api.Assertions

/**
 * NotEquals verifier class.
 */
class NotEqual @JvmOverloads constructor(
        private var expected: Any?,
        private var actual: Any?,
        private var message: String = ""
) : Verifier {

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