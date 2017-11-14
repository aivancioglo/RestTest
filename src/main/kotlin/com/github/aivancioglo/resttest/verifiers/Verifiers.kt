package com.github.aivancioglo.resttest.verifiers

import org.hamcrest.Matcher

abstract class Verifiers {
    companion object {
        @JvmStatic
        fun statusCode(statusCode: Int) = StatusCode(statusCode)

        @JvmStatic
        fun statusCode(statusCode: com.github.aivancioglo.resttest.http.StatusCode) = StatusCode(statusCode.code)

        @JvmStatic
        fun jsonSchema(jsonSchema: String) = JsonSchema(jsonSchema)

        @JvmStatic
        fun path(path: String, matcher: Matcher<*>, vararg additionalKeyMatcherPairs: Any) = Path(path, matcher, *additionalKeyMatcherPairs)

        @JvmStatic
        fun body(matcher: Matcher<*>, vararg additionalMatchers: Matcher<*>) = Body(matcher, *additionalMatchers)

        @JvmStatic
        fun equal(expected: Any, actual: Any) = Equal(expected, actual)

        @JvmStatic
        fun equal(expected: Any, actual: Any, message: String) = Equal(expected, actual, message)

        @JvmStatic
        fun notEqual(expected: Any, actual: Any) = NotEqual(expected, actual)

        @JvmStatic
        fun notEqual(expected: Any, actual: Any, message: String) = NotEqual(expected, actual, message)
    }
}