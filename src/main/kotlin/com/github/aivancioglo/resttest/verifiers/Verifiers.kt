package com.github.aivancioglo.resttest.verifiers

import com.github.aivancioglo.resttest.http.ContentType
import io.restassured.matcher.RestAssuredMatchers.matchesDtdInClasspath
import io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath
import io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import io.restassured.response.Response
import org.hamcrest.Matcher
import java.util.concurrent.TimeUnit

/**
 * Abstract class for using static functions to verify response.
 */
abstract class Verifiers {
    companion object {

        /**
         * Verify response status code.
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
         * Verify response status code.
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
         * Verify response body using JSON schema validation.
         *
         * @param jsonSchema of expected responseSpecification body.
         * @return Verifier instance.
         */
        @JvmStatic
        @Deprecated("Method is deprecated. Use method `schema` instead.")
        fun jsonSchema(jsonSchema: String): Verifier = object : Verifier {
            override fun verify(response: Response) {
                response.then().body(matchesJsonSchemaInClasspath(jsonSchema))
            }
        }

        /**
         * Verify response body using schema validation.
         *
         * @param schema of expected responseSpecification body.
         * @return Verifier instance.
         */
        @JvmStatic
        fun schema(schema: String): Verifier = when {
            schema.endsWith(".json") -> object : Verifier {
                override fun verify(response: Response) {
                    response.then().body(matchesJsonSchemaInClasspath(schema))
                }
            }

            schema.endsWith(".xsd") -> object : Verifier {
                override fun verify(response: Response) {
                    response.then().body(matchesXsdInClasspath(schema))
                }
            }

            schema.endsWith(".dtd") -> object : Verifier {
                override fun verify(response: Response) {
                    response.then().body(matchesDtdInClasspath(schema))
                }
            }

            else -> throw RuntimeException("Invalid schema file extension.")
        }

        /**
         * Verify response body path.
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
         * Verify response body content type.
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
         * Verify response body content type.
         *
         * @param contentType of response body content type.
         * @return Verifier instance.
         */
        @JvmStatic
        fun contentTypeIs(contentType: ContentType): Verifier = object : Verifier {
            override fun verify(response: Response) {
                response.then().contentType(contentType.value)
            }
        }

        /**
         * Verify response cookie.
         *
         * @param name of cookie.
         * @param matcher of responseSpecification cookie.
         * @return Verifier instance.
         */
        @JvmStatic
        fun cookie(name: String, matcher: Matcher<*>): Verifier = object : Verifier {
            override fun verify(response: Response) {
                response.then().cookie(name, matcher)
            }
        }

        /**
         * Verify response header.
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
         * Verify response headers.
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
         * Verify response headers.
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
         * Verify the response time (in milliseconds).
         *
         * @param matcher request response time.
         * @return Verifier instance.
         */
        @JvmStatic
        fun time(matcher: Matcher<Long>): Verifier = object : Verifier {
            override fun verify(response: Response) {
                response.then().time(matcher)
            }
        }

        /**
         * Verify the response time (in milliseconds).
         *
         * @param matcher request response time.
         * @param timeUnit of response.
         * @return Verifier instance.
         */
        @JvmStatic
        fun time(matcher: Matcher<Long>, timeUnit: TimeUnit): Verifier = object : Verifier {
            override fun verify(response: Response) {
                response.then().time(matcher, timeUnit)
            }
        }
    }
}