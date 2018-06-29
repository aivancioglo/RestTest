package com.github.aivancioglo.resttest.verifiers

import com.github.aivancioglo.resttest.http.Response

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