package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.logger.Logger
import io.restassured.internal.RequestSpecificationImpl

abstract class Model : Response() {
    fun set(request: RequestSpecificationImpl, response: io.restassured.response.Response) {
        super.request = request
        super.response = response
        super.logger = Logger(request, response)
    }
}
