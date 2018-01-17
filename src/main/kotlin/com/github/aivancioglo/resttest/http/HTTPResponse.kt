package com.github.aivancioglo.resttest.http

import io.restassured.http.Header
import io.restassured.module.jsv.JsonSchemaValidator
import io.restassured.response.Response

/**
 * This class is using for HTTP/HTTPS responseSpecification validation and processing.
 *
 * @constructor is setting responseSpecification of requestSpecification.
 * @param response of your requestSpecification.
 */
class HTTPResponse(private val response: Response) {

    /**
     * Making responseSpecification validation.
     *
     * @param verifiers for responseSpecification validation.
     */
    fun assertThat(vararg verifiers: (Response) -> Unit) {
        verifiers.forEach { it(response) }
    }

    /**
     * Making responseSpecification validation.
     *
     * @param code of responseSpecification.
     * @param verifiers for responseSpecification validation.
     */
    fun assertThat(code: Int, vararg verifiers: (Response) -> Unit) {
        response.then().statusCode(code)
        verifiers.forEach { it(response) }
    }

    /**
     * Making responseSpecification validation.
     *
     * @param statusCode of responseSpecification.
     * @param verifiers for responseSpecification validation.
     */
    fun assertThat(statusCode: StatusCode, vararg verifiers: (Response) -> Unit) {
        response.then().statusCode(statusCode.code)
        verifiers.forEach { it(response) }
    }

    /**
     * Making responseSpecification validation.
     *
     * @param code of responseSpecification.
     * @param jsonSchema for responseSpecification validation.
     * @param verifiers for responseSpecification validation.
     */
    fun assertThat(code: Int, jsonSchema: String, vararg verifiers: (Response) -> Unit) {
        response.then().statusCode(code).body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema))
        verifiers.forEach { it(response) }
    }

    /**
     * Making responseSpecification validation.
     *
     * @param statusCode of responseSpecification.
     * @param jsonSchema for responseSpecification validation.
     * @param verifiers for responseSpecification validation.
     */
    fun assertThat(statusCode: StatusCode, jsonSchema: String, vararg verifiers: (Response) -> Unit) {
        response.then().statusCode(statusCode.code).body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema))
        verifiers.forEach { it(response) }
    }

    /**
     * Response logging.
     *
     * @return this class instance.
     */
    fun log() = apply {
        response.then().log().all()
        return this
    }

    /**
     * Response logging.
     *
     * @param isPretty is setting if log is pretty.
     * @return this class instance.
     */
    fun log(isPretty: Boolean) = apply {
        response.then().log().all(isPretty)
    }

    /**
     * For getting responseSpecification code of last responseSpecification.
     *
     * @return responseSpecification code.
     */
    fun getStatusCode() = response.statusCode

    /**
     * For getting body of last responseSpecification as string.
     *
     * @return body as string.
     */
    fun getBody() = response.body.asString()!!

    /**
     * Deserialize responseSpecification body as your model class.
     *
     * @param cls that of your module.
     * @param T is responseSpecification model.
     * @return deserialize body as your model class.
     */
    @JvmName("as")
    fun <T> to(cls: Class<T>): T {
        val model = response.`as`(cls)!!

        if (cls is Model) {
            (model as Model).responseSpecification = response
            (model as Model).response = this
        }

        return model
    }

    /**
     * Extract value by JSON path.
     *
     * @param path1 JSON path1.
     * @param path2 JSON path2.
     * @param T is returning type.
     * @return path value.
     */
    fun <T> path(path1: String, vararg path2: String): T = response.path(path1, *path2)

    /**
     * Get responseSpecification header by name.
     *
     * @param name of header.
     * @return header value.
     */
    fun getHeader(name: String) = response.getHeader(name)!!

    /**
     * Returns list of headers.
     *
     * @return list.
     */
    fun getHeaders(): List<Header> = response.headers.asList()

    /**
     * Check if header exist.
     *
     * @param name of header.
     * @return boolean value.
     */
    fun isHeaderExist(name: String) = response.headers.hasHeaderWithName(name)
}