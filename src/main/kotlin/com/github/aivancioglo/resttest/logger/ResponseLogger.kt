package com.github.aivancioglo.resttest.logger

import io.restassured.internal.support.Prettifier
import io.restassured.parsing.Parser
import io.restassured.response.Response

class ResponseLogger(private val response: Response) {
    private val responseTime = if (response.time >= 0) "${response.time / 1000.toDouble()} sec" else "${response.time}"
    private val body: String? = Prettifier().prettify(response.body.asString(), Parser.fromContentType(response.contentType))

    fun print() {
        println(response.statusLine!!)
        println("Duration: $responseTime")
        response.headers.forEach {
            println(it.name + ": " + it.value)
        }

        if (body != null) {
            println("Body:")
            println()
            println(body)
            println()
            println()
        }
    }
}