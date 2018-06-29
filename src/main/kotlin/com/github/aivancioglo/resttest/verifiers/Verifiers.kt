package com.github.aivancioglo.resttest.verifiers

import com.github.aivancioglo.resttest.http.ContentType
import com.github.aivancioglo.resttest.http.Response
import io.restassured.matcher.RestAssuredMatchers.matchesDtdInClasspath
import io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath
import io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import org.hamcrest.Matcher
import java.util.concurrent.TimeUnit

/**
 * Abstract class for response verification using static functions.
 */
abstract class Verifiers {
    companion object {

        /**
         * Verify status code of the response.
         *
         * @param statusCode Status code of your responseSpecification.
         * @return Verifier instance.
         */
        @JvmStatic
        fun statusCode(statusCode: Int) = object : Verifier {
            override fun verify(response: Response) {
                response.then().statusCode(statusCode)
            }
        }

        /**
         * Verify status code of the response.
         *
         * @param statusCode Status code of your responseSpecification.
         * @return Verifier instance.
         */
        @JvmStatic
        fun statusCode(statusCode: com.github.aivancioglo.resttest.http.StatusCode) = object : Verifier {
            override fun verify(response: Response) {
                response.then().statusCode(statusCode.code)
            }
        }

        /**
         * Verify response body using JSON schema validation.
         *
         * @param jsonSchema Json schema of expected responseSpecification body.
         * @return Verifier instance.
         */
        @JvmStatic
        @Deprecated("Method is deprecated. Use method `schema` instead.")
        fun jsonSchema(jsonSchema: String) = object : Verifier {
            override fun verify(response: Response) {
                response.then().body(matchesJsonSchemaInClasspath(jsonSchema))
            }
        }

        /**
         * Verify response body using schema validation.
         *
         * @param schema Schema of expected responseSpecification body.
         * @return Verifier instance.
         */
        @JvmStatic
        fun schema(schema: String) = when {
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
         * Verify path of the response body.
         *
         * @param key Key of your responseSpecification body.
         * @param matcher Matcher for verification.
         * @param additionalKeyMatcherPairs Additional key and matcher pairs for verification.
         * @return Verifier instance.
         */
        @JvmStatic
        fun path(key: String,
                 matcher: Matcher<*>,
                 vararg additionalKeyMatcherPairs: Any) = Path(key, matcher, *additionalKeyMatcherPairs)

        /**
         * Set path for path verifying.
         *
         * @param key of your responseSpecification body.
         * @return Verifier instance.
         */

        @JvmStatic
        fun path(key: String, vararg paths: Path) = MultiPath(key, *paths)

        /**
         * Verify response body.
         *
         * @param matcher Matcher for verification.
         * @param additionalMatchers Additional matchers for verification.
         * @return Verifier instance.
         */
        @JvmStatic
        fun body(matcher: Matcher<*>, vararg additionalMatchers: Matcher<*>) = object : Verifier {
            override fun verify(response: Response) {
                response.then().body(matcher, *additionalMatchers)
            }
        }

        /**
         * Verify content type of the response body.
         *
         * @param contentType Content type of response body.
         * @return Verifier instance.
         */
        @JvmStatic
        fun contentTypeIs(contentType: String) = object : Verifier {
            override fun verify(response: Response) {
                response.then().contentType(contentType)
            }
        }

        /**
         * Verify content type of the response body.
         *
         * @param contentType Content type of response body.
         * @return Verifier instance.
         */
        @JvmStatic
        fun contentTypeIs(contentType: ContentType) = object : Verifier {
            override fun verify(response: Response) {
                response.then().contentType(contentType.value)
            }
        }

        /**
         * Verify response cookie.
         *
         * @param name Name of cookie.
         * @param matcher Matcher of responseSpecification cookie.
         * @return Verifier instance.
         */
        @JvmStatic
        fun cookie(name: String, matcher: Matcher<*>) = object : Verifier {
            override fun verify(response: Response) {
                response.then().cookie(name, matcher)
            }
        }

        /**
         * Verify response header.
         *
         * @param name Name of header.
         * @param matcher Matcher of responseSpecification header.
         * @return Verifier instance.
         */
        @JvmStatic
        fun header(name: String, matcher: Matcher<*>) = object : Verifier {
            override fun verify(response: Response) {
                response.then().header(name, matcher)
            }
        }

        /**
         * Verify response headers.
         *
         * @param expectedHeaders Expected headers of responseSpecification.
         * @return Verifier instance.
         */
        @JvmStatic
        fun headers(expectedHeaders: Map<String, *>) = object : Verifier {
            override fun verify(response: Response) {
                response.then().headers(expectedHeaders)
            }
        }

        /**
         * Verify response headers.
         *
         * @param firstExpectedHeaderName First header name expected of responseSpecification.
         * @param firstExpectedHeaderValue First header value expected of responseSpecification.
         * @param expectedHeaders List of expected headers of responseSpecification (expected "header name" - "header value" pairs).
         * @return Verifier instance.
         */
        @JvmStatic
        fun headers(firstExpectedHeaderName: String,
                    firstExpectedHeaderValue: Any,
                    vararg expectedHeaders: Any) = object : Verifier {
            override fun verify(response: Response) {
                response.then().headers(firstExpectedHeaderName, firstExpectedHeaderValue, *expectedHeaders)
            }
        }

        /**
         * Verify the response time (in milliseconds).
         *
         * @param matcher Response time of the request.
         * @return Verifier instance.
         */
        @JvmStatic
        fun time(matcher: Matcher<Long>) = object : Verifier {
            override fun verify(response: Response) {
                response.then().time(matcher)
            }
        }

        /**
         * Verify the response time (in milliseconds).
         *
         * @param matcher Response time of the request.
         * @param timeUnit Time unit of response.
         * @return Verifier instance.
         */
        @JvmStatic
        fun time(matcher: Matcher<Long>, timeUnit: TimeUnit) = object : Verifier {
            override fun verify(response: Response) {
                response.then().time(matcher, timeUnit)
            }
        }
    }
}