package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

/**
 * OAuth 1.0 consumer key secret.
 *
 * @constructor is creating variable with OAuth 1.0 consumer secret value.
 * @param consumerSecret of your requestSpecification.
 */
class OAuth1ConsumerSecret<in T : HTTPRequest<*>>(private val consumerSecret: String) : Setter<T> {

    /**
     * Setting OAuth 1.0 consumer secret to your requestSpecification.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        request.oAuth1.consumerSecret = consumerSecret
    }
}