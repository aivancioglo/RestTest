package com.github.aivancioglo.resttest.verifiers

import io.restassured.response.Response
import org.junit.jupiter.api.Assertions.assertEquals

/**
 * Equals verifier class.
 */
class Equals @JvmOverloads constructor(
        private var expected: Any,
        private var actual: Any,
        private var message: String = ""
) : Verifier {

    /**
     * Verify response.
     *
     * @param response of your requestSpecification.
     */
    override fun verify(response: Response) {
        if (message == "")
            assertEquals(expected, actual)
        else
            assertEquals(expected, actual, message)
    }
}