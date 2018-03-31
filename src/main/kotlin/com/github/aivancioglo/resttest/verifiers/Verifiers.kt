package com.github.aivancioglo.resttest.verifiers

import io.restassured.http.ContentType
import io.restassured.module.jsv.JsonSchemaValidator
import io.restassured.response.Response
import org.hamcrest.Matcher
import java.util.concurrent.TimeUnit

/**
 * Abstract class for using static functions to verify response.
 */
abstract class Verifiers {
    companion object {

        /**
         * Verify responseSpecification status code.
         *
         * @param statusCode of your responseSpecification.
         * @return Verifier instance.
         */
        @JvmStatic
        fun statusCode(statusCode: Int): Verifier = object : Verifier {
            override fun verify(response: Response) {
                response.then().statusCode(statusCode)
            }
        }

        /**
         * Verify responseSpecification status code.
         *
         * @param statusCode of your responseSpecification.
         * @return Verifier instance.
         */
        @JvmStatic
        fun statusCode(statusCode: com.github.aivancioglo.resttest.http.StatusCode): Verifier = object : Verifier {
            override fun verify(response: Response) {
                response.then().statusCode(statusCode.code)
            }
        }

        /**
         * Verify responseSpecification body.
         *
         * @param jsonSchema of expected responseSpecification body.
         * @return Verifier instance.
         */
        @JvmStatic
        fun jsonSchema(jsonSchema: String): Verifier = object : Verifier {
            override fun verify(response: Response) {
                response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema))
            }
        }

        /**
         * Verify responseSpecification body path.
         *
         * @param key of your responseSpecification body.
         * @param matcher for verifying.
         * @param additionalKeyMatcherPairs for verifying.
         * @return Verifier instance.
         */
        @JvmStatic
        fun path(key: String,
                 matcher: Matcher<*>,
                 vararg additionalKeyMatcherPairs: Any): Verifier = object : Verifier {
            override fun verify(response: Response) {
                response.then().body(key, matcher, *additionalKeyMatcherPairs)
            }
        }

        /**
         * Verify response body.
         *
         * @param matcher for verifying.
         * @param additionalMatchers for verifying.
         * @return Verifier instance.
         */
        @JvmStatic
        fun body(matcher: Matcher<*>, vararg additionalMatchers: Matcher<*>): Verifier = object : Verifier {
            override fun verify(response: Response) {
                response.then().body(matcher, *additionalMatchers)
            }
        }

        /**
         * Verify body content type.
         *
         * @param contentType of response body content type.
         * @return Verifier instance.
         */
        @JvmStatic
        fun contentTypeIs(contentType: String): Verifier = object : Verifier {
            override fun verify(response: Response) {
                response.then().contentType(contentType)
            }
        }

        /**
         * Verify body content type.
         *
         * @param contentType of response body content type.
         * @return Verifier instance.
         */
        @JvmStatic
        fun contentTypeIs(contentType: ContentType): Verifier = object : Verifier {
            override fun verify(response: Response) {
                response.then().contentType(contentType)
            }
        }

        /**
         * Verify responseSpecification header.
         *
         * @param name of header.
         * @param matcher of responseSpecification header.
         * @return Verifier instance.
         */
        @JvmStatic
        fun header(name: String, matcher: Matcher<*>): Verifier = object : Verifier {
            override fun verify(response: Response) {
                response.then().header(name, matcher)
            }
        }

        /**
         * Verify responseSpecification headers.
         *
         * @param expectedHeaders of responseSpecification.
         * @return Verifier instance.
         */
        @JvmStatic
        fun headers(expectedHeaders: Map<String, *>): Verifier = object : Verifier {
            override fun verify(response: Response) {
                response.then().headers(expectedHeaders)
            }
        }

        /**
         * Verify responseSpecification headers.
         *
         * @param firstExpectedHeaderName of responseSpecification.
         * @param firstExpectedHeaderValue of responseSpecification.
         * @param expectedHeaders list of responseSpecification (expected "header name" - "header value" pairs).
         * @return Verifier instance.
         */
        @JvmStatic
        fun headers(firstExpectedHeaderName: String,
                    firstExpectedHeaderValue: Any,
                    vararg expectedHeaders: Any): Verifier = object : Verifier {
            override fun verify(response: Response) {
                response.then().headers(firstExpectedHeaderName, firstExpectedHeaderValue, *expectedHeaders)
            }
        }

        /**
         * Verify responseSpecification headers.
         *
         * @param matcher request response time.
         * @return Verifier instance.
         */
        @JvmStatic
        fun time(matcher: Matcher<Long>): Verifier = object : Verifier{
            override fun verify(response: Response) {
                response.then().time(matcher)
            }
        }

        /**
         * Verify responseSpecification headers.
         *
         * @param matcher request response time.
         * @param timeUnit of response.
         * @return Verifier instance.
         */
        @JvmStatic
        fun time(matcher: Matcher<Long>, timeUnit: TimeUnit): Verifier = object : Verifier{
            override fun verify(response: Response) {
                response.then().time(matcher, timeUnit)
            }
        }
    }
}