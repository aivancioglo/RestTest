package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

class Body<in T : HTTPRequest<*>>(private val body: Any) : Setter<T> {
    override fun update(request: T) {
        request.send.body(body)
    }
}