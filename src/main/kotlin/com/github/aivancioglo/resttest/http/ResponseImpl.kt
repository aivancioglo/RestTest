package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.logger.RequestLogger
import io.restassured.response.Response

class ResponseImpl(request: Request, response: Response, requestLogger: RequestLogger) : com.github.aivancioglo.resttest.http.Response(request, response, requestLogger)