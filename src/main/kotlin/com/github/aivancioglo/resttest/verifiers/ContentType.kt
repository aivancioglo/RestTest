package com.github.aivancioglo.resttest.verifiers

import io.restassured.response.Response

/**
 * Content type verifier class.
 *
 * @constructor is creating variable of body content type.
 * @param contentType of response body.
 */
class ContentType(private val contentType: String) : Verifier {

    /**
     * Verify response.
     *
     * @param response of your requestSpecification.
     */
    override fun verify(response: Response) {
        response.then().contentType(contentType)
    }
}