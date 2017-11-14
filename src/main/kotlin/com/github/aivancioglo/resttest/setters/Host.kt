package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

class Host<in T : HTTPRequest<*>>(private val host: String) : Setter<T> {
    override fun update(request: T) {
        request.host = host
    }
}