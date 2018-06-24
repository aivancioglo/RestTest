package com.github.aivancioglo.resttest.verifiers

import io.restassured.response.Response

/**
 *  Verifiers interface.
 */
interface Verifier {

    /**
     * Function for response verification.
     *
     * @param response Response of your request.
     */
    fun verify(response: Response)
}