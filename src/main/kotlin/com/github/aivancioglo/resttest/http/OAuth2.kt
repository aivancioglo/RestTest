package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.setters.OAuth2Token

class OAuth2 {
    var created = false
    private set(value) {}

    var token: String = ""
        set(value) {
            created = true
        }

    companion object {
        @JvmStatic
        fun <T : HTTPRequest<*>> token(token: String): OAuth2Token<T> {
            return OAuth2Token(token)
        }
    }
}