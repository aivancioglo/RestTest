package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.setters.Setter
import io.restassured.http.Method.*
import java.net.MalformedURLException
import java.net.URL

class Endpoint : HTTPRequest<Endpoint>() {
    init {
        protocol = "http"
    }

    fun get(vararg setters: Setter<Endpoint>) = send(GET, *setters)

    fun get(url: String, vararg setters: Setter<Endpoint>): HTTPResponse {
        set(url)
        return send(GET, *setters)
    }

    fun post(vararg setters: Setter<Endpoint>) = send(POST, *setters)

    fun post(url: String, vararg setters: Setter<Endpoint>): HTTPResponse {
        set(url)
        return send(POST, *setters)
    }

    fun put(vararg setters: Setter<Endpoint>) = send(PUT, *setters)

    fun put(url: String, vararg setters: Setter<Endpoint>): HTTPResponse {
        set(url)
        return send(PUT, *setters)
    }

    fun patch(vararg setters: Setter<Endpoint>) = send(PATCH, *setters)


    fun patch(url: String, vararg setters: Setter<Endpoint>): HTTPResponse {
        set(url)
        return send(PATCH, *setters)
    }

    fun delete(vararg setters: Setter<Endpoint>) = send(DELETE, *setters)

    fun delete(url: String, vararg setters: Setter<Endpoint>): HTTPResponse {
        set(url)
        return send(DELETE, *setters)
    }

    fun options(vararg setters: Setter<Endpoint>) = send(OPTIONS, *setters)

    fun options(url: String, vararg setters: Setter<Endpoint>): HTTPResponse {
        set(url)
        return send(OPTIONS, *setters)
    }

    fun head(vararg setters: Setter<Endpoint>) = send(HEAD, *setters)

    fun head(url: String, vararg setters: Setter<Endpoint>): HTTPResponse {
        set(url)
        return send(HEAD, *setters)
    }

    fun trace(vararg setters: Setter<Endpoint>) = send(TRACE, *setters)

    fun trace(url: String, vararg setters: Setter<Endpoint>): HTTPResponse {
        set(url)
        return send(TRACE, *setters)
    }

    private fun set(endpoint: String) {
        val url: URL

        try {
            url = URL(endpoint)
        } catch (e: MalformedURLException) {
            throw RuntimeException(e)
        }

        protocol = url.protocol
        host = url.host
        setPath("${url.path}?${url.query}")
    }
}