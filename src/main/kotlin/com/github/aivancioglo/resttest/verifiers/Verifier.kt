package com.github.aivancioglo.resttest.verifiers

import com.github.aivancioglo.resttest.http.Response

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