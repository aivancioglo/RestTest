package com.github.aivancioglo.resttest.verifiers

import io.restassured.response.Response
import org.hamcrest.Matcher

class Path(val path: String, private val matcher: Matcher<*>, vararg private val additionalKeyMatcherPairs: Any) : Verifier {
    override fun verify(response: Response) {
        response.then().assertThat().body(path, matcher, *additionalKeyMatcherPairs)
    }
}