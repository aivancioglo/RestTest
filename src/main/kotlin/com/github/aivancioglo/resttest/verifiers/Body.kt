package com.github.aivancioglo.resttest.verifiers

import io.restassured.response.Response
import org.hamcrest.Matcher

class Body(private val matcher : Matcher<*>, vararg private val additionalMatchers : Matcher<*>) : Verifier {
    override fun verify(response: Response) {
        response.then().assertThat().body(matcher, *additionalMatchers)
    }
}