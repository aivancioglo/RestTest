package com.github.aivancioglo.resttest.logger

import io.restassured.internal.support.Prettifier
import io.restassured.parsing.Parser
import io.restassured.response.Response

class ResponseLogger(private val response: Response) {
    private val statusLine = response.statusLine!!
    private val contentType = response.contentType!!
    private val responseTime = if (response.time >= 0) "${response.time / 100} sec" else "${response.time}"
    private val body: String? = Prettifier().prettify(response.body.asString(), Parser.fromContentType(contentType))

    fun print() {
        println(statusLine)
        println("Content-Type:   $contentType")
        println("Duration:       $responseTime")

        if (body != null) {
            println("Body:")
            println()
            println(body)
            println()
            println()
        }
    }
}