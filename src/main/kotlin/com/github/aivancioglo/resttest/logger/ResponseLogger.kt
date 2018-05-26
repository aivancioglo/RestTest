package com.github.aivancioglo.resttest.logger

import io.restassured.internal.support.Prettifier
import io.restassured.parsing.Parser
import io.restassured.response.Response

class ResponseLogger(private val response: Response) {
    private val responseTime = if (response.time >= 0) "${response.time / 1000.toDouble()} sec" else "${response.time}"
    private val body: String? = if (response.body != null) Prettifier().prettify(response.body.asString(), Parser.fromContentType(response.contentType)) else null

    var printed = false
        private set

    fun asString(): String {
        var responseLog = "${response.statusLine}\n" +
                "Duration: $responseTime\n" +
                response.headers.joinToString("\n") { it.name + ": " + it.value }

        if (body != null && body.trim().isNotEmpty()) {
            responseLog += "\nBody:\n\n"
            responseLog += body
        }

        responseLog += "\n\n\n"

        return responseLog
    }

    fun print() {
        print(asString())
        printed = true
    }

    fun printIfNotPrinted() {
        if (!printed)
            print()
    }
}