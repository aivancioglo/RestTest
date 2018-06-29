package com.github.aivancioglo.resttest.verifiers

import com.github.aivancioglo.resttest.http.Response
import org.hamcrest.Matcher

open class Path() : Verifier {
    open var key = ""
        protected set
    private lateinit var matcher: Matcher<*>
    private lateinit var additionalKeyMatcherPairs: Array<out Any>

    constructor(key: String, matcher: Matcher<*>, vararg additionalKeyMatcherPairs: Any) : this() {
        this.key = key
        this.matcher = matcher
        this.additionalKeyMatcherPairs = additionalKeyMatcherPairs
    }

    override fun verify(response: Response) {
        if (response.rootPath == "")
            response.then().body(key, matcher, *additionalKeyMatcherPairs)
        else {
            response.appendRoot(".$key")
            response.then().body(response.rootPath, matcher, *additionalKeyMatcherPairs)
            response.detachRoot(".$key")
        }
    }
}