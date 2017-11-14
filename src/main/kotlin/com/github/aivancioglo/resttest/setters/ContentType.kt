package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest
import io.restassured.http.ContentType

class ContentType<in T : HTTPRequest<*>> : Setter<T> {
    private var isString = false

    private var rType: ContentType = ContentType.JSON
    private var sType: String = "application/json"

    constructor(type: String) {
        sType = type
        isString = true
    }

    constructor(type: io.restassured.http.ContentType) {
        rType = type
    }

    override fun update(request: T) {
        if (isString)
            request.send.contentType(sType)
        else
            request.send.contentType(rType)
    }
}