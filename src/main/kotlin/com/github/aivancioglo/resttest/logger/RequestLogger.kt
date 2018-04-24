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
    private val cookies = arrayListOf<String>()
    private val requestTime: String by lazy {
        val format = SimpleDateFormat("EEE, dd MMM YYYY HH:mm:ss z")
        format.timeZone = TimeZone.getTimeZone("UTC")
        format.format(Date())!!
    }
    private var body: String? = if (request.getBody<Any>() is String) Prettifier().prettify(request.getBody(), Parser.fromContentType(request.contentType)) else null

    var printed = false
        private set

    init {
        for (i in request.pathParams)
            pathParams.add(i.toString())

        for (i in request.requestParams)
            requestParams.add(i.toString())

        for (i in request.queryParams)
            queryParams.add(i.toString())

        for (i in request.formParams)
            formParams.add(i.toString())

        for (i in request.cookies.asList())
            cookies.add(i.toString())
    }

    fun print() {
        var requestLog = "Request method: ${request.method}\n" +
                "Request URI:    ${request.uri}\n" +
                "Date:           $requestTime\n"

        if (request.headers.exist()) {
            requestLog += "Headers:        ${headers.asList()[0]}\n"

            if (request.headers.size() > 1)
                for (i in 1 until headers.size())
                    requestLog += "                ${headers.asList()[i]}\n"

        }

        fun addToPrint(title: String, values: List<String>) {
            if (!values.isEmpty())
                requestLog += "\n" + title + values.joinToString("\n                ").trim()

        }

        addToPrint("Path params:    ", pathParams)
        addToPrint("Request params: ", requestParams)
        addToPrint("Query params:   ", queryParams)
        addToPrint("Form params:    ", formParams)
        addToPrint("Cookies:        ", cookies)

        if (!request.multiPartParams.isEmpty()) {
            requestLog += "Multi parts:    ${multiPartParams[0]}\n"

            if (request.multiPartParams.size > 1)
                for (i in 1 until multiPartParams.size)
                    requestLog += "                ${multiPartParams[i]}\n"
        }

        if (body != null) {
            requestLog += "Body:\n\n"
            requestLog += "$body\n\n\n"
        } else {
            requestLog += "\n\n"
        }

        print(requestLog)
        printed = true
    }

    fun printIfNotPrinted() {
        if (!printed)
            print()
    }
}