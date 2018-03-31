package com.github.aivancioglo.resttest.logger

import com.github.aivancioglo.resttest.logger.LogType.*
import io.restassured.internal.RequestSpecificationImpl
import io.restassured.response.Response

class Logger(request: RequestSpecificationImpl, response: Response) {
    private var requestLogger = RequestLogger(request)
    private var responseLogger = ResponseLogger(response)
    private var logged = false

    @JvmOverloads
    fun log(logType: LogType = ALL) {
        if (!logged) {
            when (logType) {
                ALL -> {
                    requestLogger.print()
                    println()
                    println()
                    responseLogger.print()
                }
                REQUEST -> requestLogger.print()
                RESPONSE -> responseLogger.print()
            }

            logged = true
        }
    }
}