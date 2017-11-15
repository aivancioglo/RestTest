package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

/**
 * OAuth 1.0 token setter.
 *
 * @constructor is creating variable with OAuth 1.0 token value.
 * @param token of your request.
 */
class OAuth1Token<in T : HTTPRequest<*>>(private val token: String) : Setter<T> {

    /**
     * Setting OAuth 1.0 token to your request.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        request.oAuth1.token = token
    }
}