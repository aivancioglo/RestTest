package com.github.aivancioglo.resttest.logger

import io.restassured.internal.RequestSpecificationImpl
import io.restassured.internal.support.Prettifier
import io.restassured.parsing.Parser
import java.text.SimpleDateFormat
import java.util.*

class RequestLogger(private var request: RequestSpecificationImpl) {
    private val headers = request.headers!!
    private val pathParams = arrayListOf<String>()
    private val requestParams = arrayListOf<String>()
    private val queryParams = arrayListOf<String>()
    private val formParams = arrayListOf<String>()
    private val multiPartParams = request.multiPartParams!!
    private val requestTime: String by lazy {
        val format = SimpleDateFormat("EEE, dd MMM YYYY HH:mm:ss z")
        format.timeZone = TimeZone.getTimeZone("UTC")
        format.format(Date())!!
    }
    private var body: String? = if (request.getBody<Any>() is String) Prettifier().prettify(request.getBody(), Parser.fromContentType(request.contentType)) else null

    init {
        for (i in request.pathParams)
            pathParams.add(i.toString())

        for (i in request.requestParams)
            requestParams.add(i.toString())

        for (i in request.queryParams)
            queryParams.add(i.toString())

        for (i in request.formParams)
            formParams.add(i.toString())
    }

    fun print() {
        println("Request method: ${request.method}")
        println("Request URI:    ${request.uri}")
        println("Date:           $requestTime")

        if (request.headers.exist()) {
            println("Headers:        ${headers.asList()[0]}")

            if (request.headers.size() > 1)
                for (i in 1 until headers.size())
                    println("                ${headers.asList()[i]}")

        }

        if (!request.pathParams.isEmpty()) {
            println("Path params:    ${pathParams[0]}")

            if (pathParams.size > 1)
                for (i in 1 until pathParams.size)
                    println("                ${pathParams[i]}")
        }

        if (!request.requestParams.isEmpty()) {
            println("Request params: ${requestParams[0]}")

            if (requestParams.size > 1)
                for (i in 1 until requestParams.size)
                    println("                ${requestParams[i]}")
        }

        if (!request.queryParams.isEmpty()) {
            println("Query params:   ${queryParams[0]}")

            if (request.queryParams.size > 1)
                for (i in 1 until queryParams.size)
                    println("                ${queryParams[i]}")
        }

        if (!request.formParams.isEmpty()) {
            println("Form params:    ${formParams[0]}")

            if (request.queryParams.size > 1)
                for (i in 1 until formParams.size)
                    println("                ${formParams[i]}")
        }

        if (!request.multiPartParams.isEmpty()) {
            println("Multi parts:    ${multiPartParams[0]}")

            if (request.multiPartParams.size > 1)
                for (i in 1 until multiPartParams.size)
                    println("                ${multiPartParams[i]}")
        }

        if (body != null) {
            println("Body:")
            println()
            println(body)
        }
    }
}