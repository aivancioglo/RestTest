package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

class OAuth2Token<in T : HTTPRequest<*>>(val token: String) : Setter<T> {

    /**
     * Setting OAuth 2 token to your request.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        request.oAuth2.token = token
    }
}