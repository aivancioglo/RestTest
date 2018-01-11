package com.github.aivancioglo.resttest

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
        fun token(token: String) : (HTTPRequest) -> Unit = { it.oAuth2.token = token }
    }
}