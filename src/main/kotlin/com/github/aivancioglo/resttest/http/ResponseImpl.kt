package com.github.aivancioglo.resttest.http

import io.restassured.internal.RequestSpecificationImpl
import io.restassured.response.Response

class ResponseImpl(request: RequestSpecificationImpl, response: Response) : com.github.aivancioglo.resttest.http.Response(request, response)