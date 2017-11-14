package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

class Protocol<in T : HTTPRequest<*>>(private val protocol: String) : Setter<T> {
    override fun update(request: T) {
        request.protocol = protocol
    }
}