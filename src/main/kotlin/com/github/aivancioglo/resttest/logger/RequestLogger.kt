package com.github.aivancioglo.resttest.logger

import io.restassured.http.Header
import io.restassured.internal.RequestSpecificationImpl
import io.restassured.internal.support.Prettifier
import io.restassured.parsing.Parser
import io.restassured.specification.MultiPartSpecification
import java.text.SimpleDateFormat
import java.util.*

class RequestLogger(private var request: RequestSpecificationImpl) {
    private val headers: List<Header> = request.headers!!.asList()
    private val pathParams = arrayListOf<String>()
    private val requestParams = arrayListOf<String>()
    private val queryParams = arrayListOf<String>()
    private val formParams = arrayListOf<String>()
    private val multiPartParams: List<MultiPartSpecification> = request.multiPartParams!!
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

    fun asString(): String {
        var requestLog = "Request method: ${request.method}\n" +
                "Request URI:    ${request.uri}\n" +
                "Date:           $requestTime"

        fun addToPrint(title: String, values: List<Any>) {
            if (!values.isEmpty())
                requestLog += "\n" + title + values.joinToString("\n                ").trim()
        }

        addToPrint("Headers:        ", headers)
        addToPrint("Path params:    ", pathParams)
        addToPrint("Request params: ", requestParams)
        addToPrint("Query params:   ", queryParams)
        addToPrint("Form params:    ", formParams)
        addToPrint("Cookies:        ", cookies)
        addToPrint("Multi parts:    ", multiPartParams)

        if (body != null) {
            requestLog += "\nBody:\n\n"
            requestLog += "$body"
        }

        requestLog += "\n\n\n"

        return requestLog
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