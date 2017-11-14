package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.setters.OAuth1ConsumerKey
import com.github.aivancioglo.resttest.setters.OAuth1ConsumerSecret
import com.github.aivancioglo.resttest.setters.OAuth1Token
import com.github.aivancioglo.resttest.setters.OAuth1TokenSecret

class OAuth1 {
    var created = false
        private set(value) {}

    var consumerKey: String = ""
        set(value) {
            created = true
        }

    var consumerSecret: String = ""
        set(value) {
            created = true
        }

    var token: String = ""
        set(value) {
            created = true
        }

    var tokenSecret: String = ""
        set(value) {
            created = true
        }

    companion object {
        @JvmStatic
        fun <T : HTTPRequest<*>> consumerKey(consumerKey: String) = OAuth1ConsumerKey<T>(consumerKey)

        @JvmStatic
        fun <T : HTTPRequest<*>> consumerSecret(consumerSecret: String) = OAuth1ConsumerSecret<T>(consumerSecret)

        @JvmStatic
        fun <T : HTTPRequest<*>> token(token: String) = OAuth1Token<T>(token)

        @JvmStatic
        fun <T : HTTPRequest<*>> tokenSecret(tokenSecret: String) = OAuth1TokenSecret<T>(tokenSecret)
    }
}