package com.github.aivancioglo.resttest.verifiers

import io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import io.restassured.response.Response

class JsonSchema(private val schema: String) : Verifier {
    override fun verify(response: Response) {
        response.then().assertThat().body(matchesJsonSchemaInClasspath(schema))
    }
}