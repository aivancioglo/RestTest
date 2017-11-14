package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

class Param<in T : HTTPRequest<*>>(private val key: String, val value: Any) : Setter<T> {
    override fun update(request: T) {
        request.send.param(key, value)
    }
}