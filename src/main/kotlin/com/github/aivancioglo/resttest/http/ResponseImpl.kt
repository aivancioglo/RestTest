package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.logger.RequestLogger
import io.restassured.response.Response

class ResponseImpl(requestLogger: RequestLogger, response: Response) : com.github.aivancioglo.resttest.http.Response(requestLogger, response)