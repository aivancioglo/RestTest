package com.github.aivancioglo.resttest.http

import io.restassured.module.jsv.JsonSchemaValidator
import io.restassured.response.Response

abstract class Model {
    internal lateinit var response: Response

    /**
     * Making response validation.
     *
     * @param verifiers for response validation.
     */
    fun assertThat(vararg verifiers: (Response) -> Unit) {
        verifiers.forEach { it(response) }
    }

    /**
     * Making response validation.
     *
     * @param code of response.
     * @param verifiers for response validation.
     */
    fun assertThat(code: Int, vararg verifiers: (Response) -> Unit) {
        response.then().statusCode(code)
        verifiers.forEach { it(response) }
    }

    /**
     * Making response validation.
     *
     * @param statusCode of response.
     * @param verifiers for response validation.
     */
    fun assertThat(statusCode: StatusCode, vararg verifiers: (Response) -> Unit) {
        response.then().statusCode(statusCode.code)
        verifiers.forEach { it(response) }
    }

    /**
     * Making response validation.
     *
     * @param code of response.
     * @param jsonSchema for response validation.
     * @param verifiers for response validation.
     */
    fun assertThat(code: Int, jsonSchema: String, vararg verifiers: (Response) -> Unit) {
        response.then().statusCode(code).body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema))
        verifiers.forEach { it(response) }
    }

    /**
     * Making response validation.
     *
     * @param statusCode of response.
     * @param jsonSchema for response validation.
     * @param verifiers for response validation.
     */
    fun assertThat(statusCode: StatusCode, jsonSchema: String, vararg verifiers: (Response) -> Unit) {
        response.then().statusCode(statusCode.code).body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema))
        verifiers.forEach { it(response) }
    }

    /**
     * Deserialize response body as your model class.
     *
     * @param cls that of your module.
     * @param T is response model.
     * @return deserialize body as your model class.
     */
    @JvmName("as")
    fun <T : Model> to(cls: Class<T>): T {
        val model =  response.`as`(cls)!!
        model.response = response
        return model
    }
}
