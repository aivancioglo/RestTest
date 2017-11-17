package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.setters.OAuth2Token

/**
 * Class for OAuth 2 using in requests.
 */
class OAuth2 {
    var used = false
        private set

    var token: String = ""
        set(value) {
            field = value
            used = true
        }

    companion object {

        /**
         * Setter of OAuth 2 token.
         *
         * @return consumer key.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> token(token: String): OAuth2Token<T> = OAuth2Token(token)
    }
}