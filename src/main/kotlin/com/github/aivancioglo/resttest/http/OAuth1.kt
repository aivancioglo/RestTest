package com.github.aivancioglo.resttest.http

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
        fun consumerKey(consumerKey: String): (HTTPRequest) -> Unit = { it.oAuth1.consumerKey = consumerKey }

        /**
         * Setter of OAuth 1.0 consumer secret.
         *
         * @return consumer key.
         */
        fun consumerSecret(consumerSecret: String): (HTTPRequest) -> Unit = { it.oAuth1.consumerSecret = consumerSecret }

        /**
         * Setter of OAuth 1.0 token.
         *
         * @return consumer key.
         */
        fun token(token: String): (HTTPRequest) -> Unit = { it.oAuth1.token = token }

        /**
         * Setter of OAuth 1.0 token secret.
         *
         * @return consumer key.
         */
        fun tokenSecret(tokenSecret: String): (HTTPRequest) -> Unit = { it.oAuth1.tokenSecret = tokenSecret }
    }
}