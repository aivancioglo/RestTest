package com.github.aivancioglo.resttest.logger

import com.github.aivancioglo.resttest.logger.LogType.*
import io.restassured.internal.RequestSpecificationImpl
import io.restassured.response.Response

class Logger() {
    private var requestLogger: RequestLogger? = null
    private var responseLogger: ResponseLogger? = null

    constructor(request: RequestSpecificationImpl, response: Response) : this() {
        requestLogger = RequestLogger(request)
        responseLogger = ResponseLogger(response)
    }

    constructor(requestLogger: RequestLogger, responseLogger: ResponseLogger) : this() {
        this.requestLogger = requestLogger
        this.responseLogger = responseLogger
    }

    fun setRequest(request: RequestSpecificationImpl) {
        if (requestLogger == null)
            requestLogger = RequestLogger(request)
    }

    fun setResponse(response: Response) {
        if (responseLogger == null)
            responseLogger = ResponseLogger(response)
    }

    @JvmOverloads
    fun log(logType: LogType = ALL) {
        when (logType) {
            ALL -> {
                requestLogger!!.printIfNotPrinted()
                responseLogger!!.printIfNotPrinted()
            }
            REQUEST -> requestLogger!!.printIfNotPrinted()
            RESPONSE -> responseLogger!!.printIfNotPrinted()
        }
    }

    fun asString() = requestLogger!!.asString() + responseLogger!!.asString()
}