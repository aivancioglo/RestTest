package com.github.aivancioglo.resttest.verifiers

import io.restassured.response.Response

class StatusCode(private val statusCode: Int) : Verifier {
    override fun verify(response: Response) {
        response.then().assertThat().statusCode(statusCode)
    }
}