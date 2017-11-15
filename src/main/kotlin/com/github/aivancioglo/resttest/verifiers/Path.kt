package com.github.aivancioglo.resttest.verifiers

import io.restassured.response.Response
import org.hamcrest.Matcher

/**
 * Body verifier class.
 *
 * @constructor is creating variables of matchers.
 * @param path in response body.
 * @param matcher for path validation.
 * @param additionalKeyMatcherPairs is array of matchers.
 */
class Path(private val path: String, private val matcher: Matcher<*>, vararg private val additionalKeyMatcherPairs: Any) : Verifier {

    /**
     * Verify response.
     *
     * @param response of your request.
     */
    override fun verify(response: Response) {
        response.then().assertThat().body(path, matcher, *additionalKeyMatcherPairs)
    }
}