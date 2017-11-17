package com.github.aivancioglo.resttest.verifiers

import io.restassured.response.Response
import org.junit.jupiter.api.Assertions

/**
 * IsTrue verifier class.
 */
class IsTrue @JvmOverloads constructor(
        private val condition: Boolean,
        private val message: String = ""
) : Verifier {

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