package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

class OAuth2Token<in T : HTTPRequest<*>>(val token: String) : Setter<T> {
    override fun update(request: T) {
        request.oAuth2.token = token
    }
}