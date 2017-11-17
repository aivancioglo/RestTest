package com.github.aivancioglo.resttest.verifiers

import io.restassured.response.Response

/**
 * StatusCode verifier class.
 *
 * @constructor is creating variable of status code value.
 * @param statusCode of response.
 */
class StatusCode(private val statusCode: Int) : Verifier {

    /**
     * Verify response.
     *
     * @param response of your request.
     */
    override fun verify(response: Response) {
        response.then().statusCode(statusCode)
    }
}