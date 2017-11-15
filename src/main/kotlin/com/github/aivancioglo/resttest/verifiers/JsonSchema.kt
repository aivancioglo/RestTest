package com.github.aivancioglo.resttest.verifiers

import io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import io.restassured.response.Response

/**
 * JsonSchema verifier class.
 *
 * @constructor is creating variable with schema value.
 * @param schema for your response validation.
 */
class JsonSchema(private val schema: String) : Verifier {

    /**
     * Verify response.
     *
     * @param response of your request.
     */
    override fun verify(response: Response) {
        response.then().assertThat().body(matchesJsonSchemaInClasspath(schema))
    }
}