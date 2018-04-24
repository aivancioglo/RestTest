package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.setters.Setter

/**
 * Class for OAuth 1.0 using in requests.
 */
class OAuth1 {
    var used = false

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
        fun consumerKey(consumerKey: String) = object : Setter {
            override fun update(request: Request) {
                request.oAuth1.consumerKey = consumerKey
            }
        }

        /**
         * Setter of OAuth 1.0 consumer secret.
         *
         * @return consumer key.
         */
        @JvmStatic
        fun consumerSecret(consumerSecret: String) = object : Setter {
            override fun update(request: Request) {
                request.oAuth1.consumerSecret = consumerSecret
            }
        }

        /**
         * Setter of OAuth 1.0 token.
         *
         * @return consumer key.
         */
        @JvmStatic
        fun token(token: String) = object : Setter {
            override fun update(request: Request) {
                request.oAuth1.token = token
            }
        }

        /**
         * Setter of OAuth 1.0 token secret.
         *
         * @return consumer key.
         */
        @JvmStatic
        fun tokenSecret(tokenSecret: String) = object : Setter {
            override fun update(request: Request) {
                request.oAuth1.tokenSecret = tokenSecret
            }
        }
    }
}