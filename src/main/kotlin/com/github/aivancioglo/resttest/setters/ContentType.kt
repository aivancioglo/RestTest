package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest
import io.restassured.http.ContentType

/**
 * Content type setter.
 */
class ContentType<in T : HTTPRequest<*>> : Setter<T> {
    private var isString = false

    private var rType: ContentType = ContentType.JSON
    private var sType: String = "application/json"

    /**
     * Is creating variable with content type value.
     *
     * @param type of content.
     */
    constructor(type: String) {
        sType = type
        isString = true
    }

    /**
     * Is creating variable with content type value.
     *
     * @param type of content.
     */
    constructor(type: io.restassured.http.ContentType) {
        rType = type
    }

    /**
     * Setting content type of your requestSpecification.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        if (isString)
            request.requestSpecification.contentType(sType)
        else
            request.requestSpecification.contentType(rType.acceptHeader)
    }
}