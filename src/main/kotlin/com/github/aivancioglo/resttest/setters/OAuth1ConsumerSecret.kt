package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

class OAuth1ConsumerSecret<in T : HTTPRequest<*>>(private val consumerSecret: String) : Setter<T> {
    override fun update(request: T) {
        request.oAuth1.consumerSecret = consumerSecret
    }
}