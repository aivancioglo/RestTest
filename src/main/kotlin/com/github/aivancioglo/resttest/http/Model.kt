package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.logger.Logger

abstract class Model : Response() {

    /**
     * This method can be used for setting of all required variables of default response.
     */
    fun set(logger: Logger, response: io.restassured.response.Response) {
        super.response = response
        super.logger = logger
    }
}
