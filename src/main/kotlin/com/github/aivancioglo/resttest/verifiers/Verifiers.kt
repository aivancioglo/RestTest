package com.github.aivancioglo.resttest.verifiers

import org.hamcrest.Matcher

/**
 * Abstract class for using static functions to verify response.
 */
abstract class Verifiers {
    companion object {

        /**
         * Verify response status code.
         *
         * @param statusCode of your response.
         * @return StatusCode verifier instance.
         */
        @JvmStatic
        fun statusCode(statusCode: Int) = StatusCode(statusCode)

        /**
         * Verify response status code.
         *
         * @param statusCode of your response.
         * @return StatusCode verifier instance.
         */
        @JvmStatic
        fun statusCode(statusCode: com.github.aivancioglo.resttest.http.StatusCode) = StatusCode(statusCode.code)

        /**
         * Verify response body.
         *
         * @param jsonSchema of expected response body.
         * @return JsonSchema verifier instance.
         */
        @JvmStatic
        fun jsonSchema(jsonSchema: String) = JsonSchema(jsonSchema)

        /**
         * Verify response body path.
         *
         * @param path of your response body.
         * @param matcher for verifying.
         * @param additionalKeyMatcherPairs for verifying.
         * @return Path verifier instance.
         */
        @JvmStatic
        fun path(path: String, matcher: Matcher<*>, vararg additionalKeyMatcherPairs: Any) = Path(path, matcher, *additionalKeyMatcherPairs)

        /**
         * Verify response body.
         *
         * @param matcher for verifying.
         * @param additionalMatchers for verifying.
         * @return Body verifier instance.
         */
        @JvmStatic
        fun body(matcher: Matcher<*>, vararg additionalMatchers: Matcher<*>) = Body(matcher, *additionalMatchers)

        /**
         * Verify if equals conditions.
         *
         * @param expected condition.
         * @param actual condition.
         * @return Equals verifier instance.
         */
        @JvmStatic
        fun equals(expected: Any, actual: Any) = Equals(expected, actual)

        /**
         * Verify if equals conditions.
         *
         * @param expected condition.
         * @param actual condition.
         * @param message of fail.
         * @return Equals verifier instance.
         */
        @JvmStatic
        fun equals(expected: Any, actual: Any, message: String) = Equals(expected, actual, message)

        /**
         * Verify if not equals conditions.
         *
         * @param unexpected condition.
         * @param actual condition.
         * @return NotEquals verifier instance.
         */
        @JvmStatic
        fun notEquals(unexpected: Any, actual: Any) = NotEqual(unexpected, actual)

        /**
         * Verify if not equals conditions.
         *
         * @param unexpected condition.
         * @param actual condition.
         * @param message of fail.
         * @return NotEquals verifier instance.
         */
        @JvmStatic
        fun notEquals(unexpected: Any, actual: Any, message: String) = NotEqual(unexpected, actual, message)

        /**
         * Verify if condition is true.
         *
         * @param condition to verify.
         * @return IsTrue verifier instance.
         */
        @JvmStatic
        fun isTrue(condition: Boolean) = IsTrue(condition)

        /**
         * Verify if condition is true.
         *
         * @param condition to verify.
         * @param message of fail.
         * @return IsTrue verifier instance.
         */
        @JvmStatic
        fun isTrue(condition: Boolean, message: String) = IsTrue(condition, message)
    }
}