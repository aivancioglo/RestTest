package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

/**
 * OAuth 1.0 consumer key setter.
 *
 * @constructor is creating variable with OAuth 1.0 consumer key value.
 * @param consumerKey of your request.
 */
class OAuth1ConsumerKey<in T : HTTPRequest<*>>(private val consumerKey: String) : Setter<T> {

    /**
     * Setting OAuth 1.0 consumer key to your request.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        request.oAuth1.consumerKey = consumerKey
    }
}