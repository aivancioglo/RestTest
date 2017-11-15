package com.github.aivancioglo.resttest.verifiers

import io.restassured.response.Response
import org.hamcrest.Matcher

/**
 * Body verifier class.
 *
 * @constructor is creating variables of matchers.
 * @param matcher                   class of Hamcrest.
 * @param additionalMatchers array of matchers.
 */
class Body(private val matcher : Matcher<*>, vararg private val additionalMatchers : Matcher<*>) : Verifier {

    /**
     * Verify response.
     *
     * @param response of your request.
     */
    override fun verify(response: Response) {
        response.then().assertThat().body(matcher, *additionalMatchers)
    }
}