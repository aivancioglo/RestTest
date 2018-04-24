package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.setters.Setter

/**
 * Class for OAuth 2 using in requests.
 */
class OAuth2 {
    var used = false

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
        fun token(token: String) = object : Setter {
            override fun update(request: Request) {
                request.oAuth2.token = token
            }
        }
    }
}
