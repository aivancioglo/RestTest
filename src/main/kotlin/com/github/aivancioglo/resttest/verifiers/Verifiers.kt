package com.github.aivancioglo.resttest.verifiers

import io.restassured.module.jsv.JsonSchemaValidator
import io.restassured.response.Response
import org.hamcrest.Matcher
import org.junit.jupiter.api.Assertions.*

/**
 * Abstract class for using static functions to verify response.
 */
abstract class Verifiers {
    companion object {

        /**
         * Verify response status code.
         *
         * @param statusCode of your response.
         * @return Verifier instance.
         */
        @JvmStatic
        fun statusCode(statusCode: Int): (Response) -> Unit = { it.then().statusCode(statusCode) }

        /**
         * Verify response status code.
         *
         * @param statusCode of your response.
         * @return Verifier instance.
         */
        @JvmStatic
        fun statusCode(statusCode: com.github.aivancioglo.resttest.http.StatusCode): (Response) -> Unit = { it.then().statusCode(statusCode.code) }

        /**
         * Verify response body.
         *
         * @param jsonSchema of expected response body.
         * @return Verifier instance.
         */
        @JvmStatic
        fun jsonSchema(jsonSchema: String): (Response) -> Unit = { it.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema)) }

        /**
         * Verify response body path.
         *
         * @param path of your response body.
         * @param matcher for verifying.
         * @param additionalKeyMatcherPairs for verifying.
         * @return Verifier instance.
         */
        @JvmStatic
        fun path(path: String, matcher: Matcher<*>, vararg additionalKeyMatcherPairs: Any): (Response) -> Unit = { it.then().body(path, matcher, *additionalKeyMatcherPairs) }

        /**
         * Verify response body.
         *
         * @param matcher for verifying.
         * @param additionalMatchers for verifying.
         * @return Verifier instance.
         */
        @JvmStatic
        fun body(matcher: Matcher<*>, vararg additionalMatchers: Matcher<*>): (Response) -> Unit = { it.then().body(matcher, *additionalMatchers) }

        /**
         * Verify if equals conditions.
         *
         * @param expected condition.
         * @param actual condition.
         * @return Verifier instance.
         */
        @JvmStatic
        fun equals(expected: Any, actual: Any): (Response) -> Unit = { assertEquals(expected, actual) }

        /**
         * Verify if equals conditions.
         *
         * @param expected condition.
         * @param actual condition.
         * @param message of fail.
         * @return Verifier instance.
         */
        @JvmStatic
        fun equals(expected: Any, actual: Any, message: String): (Response) -> Unit = { assertEquals(expected, actual, message) }

        /**
         * Verify if not equals conditions.
         *
         * @param unexpected condition.
         * @param actual condition.
         * @return Verifier instance.
         */
        @JvmStatic
        fun notEquals(unexpected: Any, actual: Any): (Response) -> Unit = { assertNotEquals(unexpected, actual) }

        /**
         * Verify if not equals conditions.
         *
         * @param unexpected condition.
         * @param actual condition.
         * @param message of fail.
         * @return Verifier instance.
         */
        @JvmStatic
        fun notEquals(unexpected: Any, actual: Any, message: String): (Response) -> Unit = { assertNotEquals(unexpected, actual, message) }

        /**
         * Verify if condition is true.
         *
         * @param condition to verify.
         * @return Verifier instance.
         */
        @JvmStatic
        fun isTrue(condition: Boolean): (Response) -> Unit = { assertTrue(condition) }

        /**
         * Verify if condition is true.
         *
         * @param condition to verify.
         * @param message of fail.
         * @return Verifier instance.
         */
        @JvmStatic
        fun isTrue(condition: Boolean, message: String): (Response) -> Unit = { assertTrue(condition, message) }

        /**
         * Verify body content type.
         *
         * @param matcher of response body content type.
         * @return Verifier instance.
         */
        @JvmStatic
        fun contentType(matcher: Matcher<String>): (Response) -> Unit = { it.then().contentType(matcher) }

        /**
         * Verify response header.
         *
         * @param name of header.
         * @param matcher of response header.
         * @return Verifier instance.
         */
        @JvmStatic
        fun header(name: String, matcher: Matcher<*>) : (Response) -> Unit = { it.then().header(name, matcher) }

        /**
         * Verify response header.
         *
         * @param name of header.
         * @param expectedValue of response header.
         * @return Verifier instance.
         */
        @JvmStatic
        fun header(name: String, expectedValue: String) : (Response) -> Unit = { it.then().header(name, expectedValue) }

        /**
         * Verify response headers.
         *
         * @param expectedHeaders of response.
         * @return Verifier instance.
         */
        @JvmStatic
        fun headers(expectedHeaders: Map<String, *>) : (Response) -> Unit = { it.then().headers(expectedHeaders) }

        /**
         * Verify response headers.
         *
         * @param firstExpectedHeaderName of response.
         * @param firstExpectedHeaderValue of response.
         * @param expectedHeaders list of response (expected "header name" - "header value" pairs).
         * @return Verifier instance.
         */
        @JvmStatic
        fun headers(firstExpectedHeaderName: String, firstExpectedHeaderValue: Any, vararg expectedHeaders: Any) : (Response) -> Unit = {
            it.then().headers(firstExpectedHeaderName, firstExpectedHeaderValue, *expectedHeaders)
        }
    }
}