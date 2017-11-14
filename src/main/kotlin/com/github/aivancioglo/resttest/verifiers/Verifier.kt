package com.github.aivancioglo.resttest.verifiers

import io.restassured.response.Response

interface Verifier {
    fun verify(response: Response)

    companion object {
        fun verify(response: Response, vararg verifiers: Verifier) {
            verifiers.forEach { it.verify(response) }
        }
    }
}