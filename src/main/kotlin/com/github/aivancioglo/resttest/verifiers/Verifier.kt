package com.github.aivancioglo.resttest.verifiers

import io.restassured.response.Response

/**
 *  Verifiers interface.
 */
interface Verifier {

    /**
     * Function for response verifying.
     *
     * @param response of your request.
     */
    fun verify(response: Response)
}