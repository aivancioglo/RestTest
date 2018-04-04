package com.github.aivancioglo.resttest.logger

import io.restassured.internal.support.Prettifier
import io.restassured.parsing.Parser
import io.restassured.response.Response

class ResponseLogger(private val response: Response) {
    private val responseTime = if (response.time >= 0) "${response.time / 1000.toDouble()} sec" else "${response.time}"
    private val body: String? = if (response.body != null) Prettifier().prettify(response.body.asString(), Parser.fromContentType(response.contentType)) else null

    fun print() {
        println(response.statusLine!!)
        println("Duration: $responseTime")
        response.headers.forEach {
            println(it.name + ": " + it.value)
        }

        if (body != null && response.headers.get("Content-Length").value.toInt() > 0) {
            println("Body:")
            println()
            println(body)
            println()
            println()
        }
    }
}