package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.verifiers.Verifier
import io.restassured.http.Header
import io.restassured.module.jsv.JsonSchemaValidator
import io.restassured.response.Response

class HTTPResponse(private val response: Response) {

    fun assertThat(vararg verifiers: Verifier) {
        Verifier.verify(response, *verifiers)
    }

    fun assertThat(code: Int, vararg verifiers: Verifier) {
        response.then().assertThat().statusCode(code)
        Verifier.verify(response, *verifiers)
    }

    fun assertThat(statusCode: StatusCode, vararg verifiers: Verifier) {
        response.then().assertThat().statusCode(statusCode.code)
        Verifier.verify(response, *verifiers)
    }

    fun assertThat(code: Int, jsonSchema: String, vararg verifiers: Verifier) {
        response.then().assertThat().statusCode(code)
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema))
        Verifier.verify(response, *verifiers)
    }

    fun assertThat(statusCode: StatusCode, jsonSchema: String, vararg verifiers: Verifier) {
        response.then().assertThat().statusCode(statusCode.code)
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema))
        Verifier.verify(response, *verifiers)
    }

    fun log(): HTTPResponse {
        response.then().log().all()
        return this
    }

    fun log(isPretty: Boolean): HTTPResponse {
        response.then().log().all(isPretty)
        return this
    }

    fun getStatusCode(): Int {
        return response.statusCode
    }

    fun getBody(): String {
        return response.body.asString()
    }

    fun <T> `as`(cls: Class<T>): T {
        return response.`as`(cls)
    }

    fun <T> path(path1: String, vararg path2: String): T {
        return response.path(path1, *path2)
    }

    fun getHeader(name: String): String {
        return response.getHeader(name)
    }

    fun getHeaders(): List<Header> {
        return response.headers.asList()
    }

    fun isHeaderExist(name: String): Boolean {
        return response.headers.hasHeaderWithName(name)
    }
}