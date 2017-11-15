package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.setters.Setter
import io.restassured.http.Method.*
import java.net.MalformedURLException
import java.net.URL

/**
 * Universal class for any HTTP request making.
 */
class Endpoint : HTTPRequest<Endpoint>() {
    /**
     * Making GET request.
     *
     * @param setters are setting up request specification.
     * @return HTTPResponse instance.
     */
    fun get(vararg setters: Setter<HTTPRequest<Endpoint>>) = send(GET, *setters)

    /**
     * Making GET request.
     *
     * @param url of your request.
     * @param setters are setting up request specification.
     * @return HTTPResponse instance.
     */
    fun get(url: String, vararg setters: Setter<HTTPRequest<Endpoint>>): HTTPResponse {
        set(url)
        return send(GET, *setters)
    }

    /**
     * Making POST request.
     *
     * @param setters are setting up request specification.
     * @return HTTPResponse instance.
     */
    fun post(vararg setters: Setter<HTTPRequest<Endpoint>>) = send(POST, *setters)

    /**
     * Making POST request.
     *
     * @param url of your request.
     * @param setters are setting up request specification.
     * @return HTTPResponse instance.
     */
    fun post(url: String, vararg setters: Setter<HTTPRequest<Endpoint>>): HTTPResponse {
        set(url)
        return send(POST, *setters)
    }

    /**
     * Making PUT request.
     *
     * @param setters are setting up request specification.
     * @return HTTPResponse instance.
     */
    fun put(vararg setters: Setter<HTTPRequest<Endpoint>>) = send(PUT, *setters)

    /**
     * Making PUT request.
     *
     * @param url of your request.
     * @param setters are setting up request specification.
     * @return HTTPResponse instance.
     */
    fun put(url: String, vararg setters: Setter<HTTPRequest<Endpoint>>): HTTPResponse {
        set(url)
        return send(PUT, *setters)
    }

    /**
     * Making PATCH request.
     *
     * @param setters are setting up request specification.
     * @return HTTPResponse instance.
     */
    fun patch(vararg setters: Setter<HTTPRequest<Endpoint>>) = send(PATCH, *setters)

    /**
     * Making PATCH request.
     *
     * @param url of your request.
     * @param setters are setting up request specification.
     * @return HTTPResponse instance.
     */
    fun patch(url: String, vararg setters: Setter<HTTPRequest<Endpoint>>): HTTPResponse {
        set(url)
        return send(PATCH, *setters)
    }

    /**
     * Making DELETE request.
     *
     * @param setters are setting up request specification.
     * @return HTTPResponse instance.
     */
    fun delete(vararg setters: Setter<HTTPRequest<Endpoint>>) = send(DELETE, *setters)

    /**
     * Making DELETE request.
     *
     * @param url of your request.
     * @param setters are setting up request specification.
     * @return HTTPResponse instance.
     */
    fun delete(url: String, vararg setters: Setter<HTTPRequest<Endpoint>>): HTTPResponse {
        set(url)
        return send(DELETE, *setters)
    }

    /**
     * Making OPTIONS request.
     *
     * @param setters are setting up request specification.
     * @return HTTPResponse instance.
     */
    fun options(vararg setters: Setter<HTTPRequest<Endpoint>>) = send(OPTIONS, *setters)

    /**
     * Making OPTIONS request.
     *
     * @param url of your request.
     * @param setters are setting up request specification.
     * @return HTTPResponse instance.
     */
    fun options(url: String, vararg setters: Setter<HTTPRequest<Endpoint>>): HTTPResponse {
        set(url)
        return send(OPTIONS, *setters)
    }

    /**
     * Making HEAD request.
     *
     * @param setters are setting up request specification.
     * @return HTTPResponse instance.
     */
    fun head(vararg setters: Setter<HTTPRequest<Endpoint>>) = send(HEAD, *setters)

    /**
     * Making HEAD request.
     *
     * @param url of your request.
     * @param setters are setting up request specification.
     * @return HTTPResponse instance.
     */
    fun head(url: String, vararg setters: Setter<HTTPRequest<Endpoint>>): HTTPResponse {
        set(url)
        return send(HEAD, *setters)
    }

    /**
     * Making TRACE request.
     *
     * @param setters are setting up request specification.
     * @return HTTPResponse instance.
     */
    fun trace(vararg setters: Setter<HTTPRequest<Endpoint>>) = send(TRACE, *setters)

    /**
     * Making TRACE request.
     *
     * @param url of your request.
     * @param setters are setting up request specification.
     * @return HTTPResponse instance.
     */
    fun trace(url: String, vararg setters: Setter<HTTPRequest<Endpoint>>): HTTPResponse {
        set(url)
        return send(TRACE, *setters)
    }

    /**
     * Setting url to URL to request specification.
     *
     * @param endpoint of request.
     */
    private fun set(endpoint: String) {
        val url = URL(endpoint)

        protocol = url.protocol
        host = url.host
        setPath("${url.path}?${url.query}")
    }
}