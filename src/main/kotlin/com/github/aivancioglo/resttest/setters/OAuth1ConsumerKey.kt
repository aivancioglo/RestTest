package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

class OAuth1ConsumerKey<in T : HTTPRequest<*>>(private val consumerKey: String) : Setter<T> {
    override fun update(request: T) {
        request.oAuth1.consumerKey = consumerKey
    }
}