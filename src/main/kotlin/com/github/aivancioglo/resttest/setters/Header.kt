package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

class Header<in T : HTTPRequest<*>>(private val name: String, val value: String) : Setter<T> {
    override fun update(request: T) {
        request.send.header(name, value)
    }
}