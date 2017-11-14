package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

class OAuth1Token<in T : HTTPRequest<*>>(private val token: String) : Setter<T> {
    override fun update(request: T) {
        request.oAuth1.token = token
    }
}