package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.setters.OAuth1ConsumerKey
import com.github.aivancioglo.resttest.setters.OAuth1ConsumerSecret
import com.github.aivancioglo.resttest.setters.OAuth1Token
import com.github.aivancioglo.resttest.setters.OAuth1TokenSecret

/**
 * Class for OAuth 1.0 using in requests.
 */
class OAuth1 {
    var created = false
        private set

    var consumerKey: String = ""
        set(value) {
            field = value
            created = true
        }

    var consumerSecret: String = ""
        set(value) {
            field = value
            created = true
        }

    var token: String = ""
        set(value) {
            field = value
            created = true
        }

    var tokenSecret: String = ""
        set(value) {
            field = value
            created = true
        }

    companion object {

        /**
         * Setter of OAuth 1.0 consumer key.
         *
         * @return consumer key.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> consumerKey(consumerKey: String) = OAuth1ConsumerKey<T>(consumerKey)

        /**
         * Setter of OAuth 1.0 consumer secret.
         *
         * @return consumer key.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> consumerSecret(consumerSecret: String) = OAuth1ConsumerSecret<T>(consumerSecret)

        /**
         * Setter of OAuth 1.0 token.
         *
         * @return consumer key.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> token(token: String) = OAuth1Token<T>(token)

        /**
         * Setter of OAuth 1.0 token secret.
         *
         * @return consumer key.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> tokenSecret(tokenSecret: String) = OAuth1TokenSecret<T>(tokenSecret)
    }
}