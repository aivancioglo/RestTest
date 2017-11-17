package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.verifiers.*
import io.restassured.http.Header
import io.restassured.module.jsv.JsonSchemaValidator
import io.restassured.response.Response
import org.hamcrest.Matcher

/**
 * This class is using for HTTP/HTTPS response validation and processing.
 *
 * @constructor is setting response of requestSpecification.
 * @param response of your requestSpecification.
 */
class HTTPResponse(private val response: Response) {

    /**
     * Making response validation.
     *
     * @return LocalVerifier instance.
     */
    fun assertThat() = LocalVerifier(response)

    /**
     * Making response validation.
     *
     * @param verifiers for response validation.
     */
    fun assertThat(vararg verifiers: Verifier) {
        verifiers.forEach { it.verify(response) }
    }

    /**
     * Making response validation.
     *
     * @param code of response.
     * @param verifiers for response validation.
     */
    fun assertThat(code: Int, vararg verifiers: Verifier) {
        response.then().statusCode(code)
        verifiers.forEach { it.verify(response) }
    }

    /**
     * Making response validation.
     *
     * @param statusCode of response.
     * @param verifiers for response validation.
     */
    fun assertThat(statusCode: StatusCode, vararg verifiers: Verifier) {
        response.then().statusCode(statusCode.code)
        verifiers.forEach { it.verify(response) }
    }

    /**
     * Making response validation.
     *
     * @param code of response.
     * @param jsonSchema for response validation.
     * @param verifiers for response validation.
     */
    fun assertThat(code: Int, jsonSchema: String, vararg verifiers: Verifier) {
        response.then().statusCode(code).body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema))
        verifiers.forEach { it.verify(response) }
    }

    /**
     * Making response validation.
     *
     * @param statusCode of response.
     * @param jsonSchema for response validation.
     * @param verifiers for response validation.
     */
    fun assertThat(statusCode: StatusCode, jsonSchema: String, vararg verifiers: Verifier) {
        response.then().statusCode(statusCode.code).body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema))
        verifiers.forEach { it.verify(response) }
    }

    /**
     * Response logging.
     *
     * @return this class instance.
     */
    fun log(): HTTPResponse {
        response.then().log().all()
        return this
    }

    /**
     * Response logging.
     *
     * @param isPretty is setting if log is pretty.
     * @return this class instance.
     */
    fun log(isPretty: Boolean): HTTPResponse {
        response.then().log().all(isPretty)
        return this
    }

    /**
     * For getting response code of last response.
     *
     * @return response code.
     */
    fun getStatusCode() = response.statusCode

    /**
     * For getting body of last response as string.
     *
     * @return body as string.
     */
    fun getBody() = response.body.asString()

    /**
     * Deserialize response body as your model class.
     *
     * @param cls that of your module.
     * @param T is response model.
     * @return deserialized body as your model class.
     */
    fun <T> `as`(cls: Class<T>) = response.`as`(cls)

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
     * Get response header by name.
     *
     * @param name of header.
     * @return header value.
     */
    fun getHeader(name: String) = response.getHeader(name)

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

    /**
     * Class for fluent response verifying.
     */
    class LocalVerifier(private val response: Response) {

        /**
         * Verify response status code.
         *
         * @param statusCode of your response.
         * @return this class instance.
         */
        fun statusCode(statusCode: Int) = apply {
            com.github.aivancioglo.resttest.verifiers.StatusCode(statusCode)
        }

        /**
         * Verify response status code.
         *
         * @param statusCode of your response.
         * @return this class instance.
         */
        fun statusCode(statusCode: StatusCode) = apply {
            com.github.aivancioglo.resttest.verifiers.StatusCode(statusCode.code)
        }

        /**
         * Verify response body.
         *
         * @param jsonSchema of expected response body.
         * @return this class instance.
         */
        fun jsonSchema(jsonSchema: String) = apply {
            JsonSchema(jsonSchema)
        }

        /**
         * Verify response body path.
         *
         * @param path of your response body.
         * @param matcher for verifying.
         * @param additionalKeyMatcherPairs for verifying.
         * @return this class instance.
         */
        fun path(path: String, matcher: Matcher<*>, vararg additionalKeyMatcherPairs: Any) = apply {
            Path(path, matcher, additionalKeyMatcherPairs).verify(response)
        }

        /**
         * Verify response body.
         *
         * @param matcher for verifying.
         * @param additionalMatchers for verifying.
         * @return this class instance.
         */
        fun body(matcher: Matcher<*>, vararg additionalMatchers: Matcher<*>) = apply {
            Body(matcher, *additionalMatchers).verify(response)
        }

        /**
         * Verify if equals conditions.
         *
         * @param expected condition.
         * @param actual condition.
         * @return this class instance.
         */
        fun equals(expected: Any, actual: Any) = apply {
            Equals(expected, actual)
        }

        /**
         * Verify if equals conditions.
         *
         * @param expected condition.
         * @param actual condition.
         * @param message of fail.
         * @return this class instance.
         */
        fun equals(expected: Any, actual: Any, message: String) = apply {
            Equals(expected, actual, message)
        }

        /**
         * Verify if not equals conditions.
         *
         * @param unexpected condition.
         * @param actual condition.
         * @return this class instance.
         */
        fun notEquals(unexpected: Any, actual: Any) = apply {
            NotEqual(unexpected, actual)
        }

        /**
         * Verify if not equals conditions.
         *
         * @param unexpected condition.
         * @param actual condition.
         * @param message of fail.
         * @return this class instance.
         */
        fun notEquals(unexpected: Any, actual: Any, message: String) = apply {
            NotEqual(unexpected, actual, message)
        }

        /**
         * Verify if condition is true.
         *
         * @param condition to verify.
         * @return this class instance.
         */
        fun isTrue(condition: Boolean) = apply {
            IsTrue(condition)
        }

        /**
         * Verify if condition is true.
         *
         * @param condition to verify.
         * @param message of fail.
         * @return this class instance.
         */
        fun isTrue(condition: Boolean, message: String) = apply {
            IsTrue(condition, message)
        }

        /**
         * Verify body content type.
         *
         * @param contentType of response body.
         * @return ContentType verifier instance.
         */
        fun contentType(contentType: io.restassured.http.ContentType) = apply {
            ContentType(contentType.toString()).verify(response)
        }

        /**
         * Verify body content type.
         *
         * @param contentType of response body.
         * @return ContentType verifier instance.
         */
        fun contentType(contentType: String) = apply {
            ContentType(contentType).verify(response)
        }
    }
}