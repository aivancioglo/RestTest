package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.setters.*

/**
 * Class for OAuth 1.0 using in requests.
 */
class OAuth1 {
    var used = false
        private set

    var consumerKey: String = ""
        set(value) {
            field = value
            used = true
        }

    var consumerSecret: String = ""
        set(value) {
            field = value
            used = true
        }

    var token: String = ""
        set(value) {
            field = value
            used = true
        }

    var tokenSecret: String = ""
        set(value) {
            field = value
            used = true
        }

    companion object {

        /**
         * Setter of OAuth 1.0 consumer key.
         *
         * @return consumer key.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> consumerKey(consumerKey: String) = object : Setter<T> {
            override fun update(request: T) {
                request.oAuth1.consumerKey = consumerKey
            }
        }

        /**
         * Setter of OAuth 1.0 consumer secret.
         *
         * @return consumer key.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> consumerSecret(consumerSecret: String) = object : Setter<T> {
            override fun update(request: T) {
                request.oAuth1.consumerSecret = consumerSecret
            }
        }

        /**
         * Setter of OAuth 1.0 token.
         *
         * @return consumer key.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> token(token: String) = object : Setter<T> {
            override fun update(request: T) {
                request.oAuth1.token = token
            }
        }

        /**
         * Setter of OAuth 1.0 token secret.
         *
         * @return consumer key.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> tokenSecret(tokenSecret: String) = object : Setter<T> {
            override fun update(request: T) {
                request.oAuth1.tokenSecret = tokenSecret
            }
        }
    }
}