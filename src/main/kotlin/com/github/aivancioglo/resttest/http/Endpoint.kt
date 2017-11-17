package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.setters.Setter
import com.github.aivancioglo.resttest.setters.Setters
import com.github.aivancioglo.resttest.setters.Setters.Companion.host
import com.github.aivancioglo.resttest.setters.Setters.Companion.path
import com.github.aivancioglo.resttest.setters.Setters.Companion.protocol
import io.restassured.http.Method.*
import java.net.MalformedURLException
import java.net.URL

/**
 * Universal class for any HTTP requestSpecification making.
 */
class Endpoint : HTTPRequest<Endpoint>() {
    /**
     * Making GET requestSpecification.
     *
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse instance.
     */
    fun get(vararg setters: Setter<HTTPRequest<Endpoint>>) = send(GET, *setters)

    /**
     * Making GET requestSpecification.
     *
     * @param url of your requestSpecification.
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse instance.
     */
    fun get(url: String, vararg setters: Setter<HTTPRequest<Endpoint>>): HTTPResponse {
        set(url)
        return send(GET, *setters)
    }

    /**
     * Making POST requestSpecification.
     *
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse instance.
     */
    fun post(vararg setters: Setter<HTTPRequest<Endpoint>>) = send(POST, *setters)

    /**
     * Making POST requestSpecification.
     *
     * @param url of your requestSpecification.
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse instance.
     */
    fun post(url: String, vararg setters: Setter<HTTPRequest<Endpoint>>): HTTPResponse {
        set(url)
        return send(POST, *setters)
    }

    /**
     * Making PUT requestSpecification.
     *
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse instance.
     */
    fun put(vararg setters: Setter<HTTPRequest<Endpoint>>) = send(PUT, *setters)

    /**
     * Making PUT requestSpecification.
     *
     * @param url of your requestSpecification.
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse instance.
     */
    fun put(url: String, vararg setters: Setter<HTTPRequest<Endpoint>>): HTTPResponse {
        set(url)
        return send(PUT, *setters)
    }

    /**
     * Making PATCH requestSpecification.
     *
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse instance.
     */
    fun patch(vararg setters: Setter<HTTPRequest<Endpoint>>) = send(PATCH, *setters)

    /**
     * Making PATCH requestSpecification.
     *
     * @param url of your requestSpecification.
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse instance.
     */
    fun patch(url: String, vararg setters: Setter<HTTPRequest<Endpoint>>): HTTPResponse {
        set(url)
        return send(PATCH, *setters)
    }

    /**
     * Making DELETE requestSpecification.
     *
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse instance.
     */
    fun delete(vararg setters: Setter<HTTPRequest<Endpoint>>) = send(DELETE, *setters)

    /**
     * Making DELETE requestSpecification.
     *
     * @param url of your requestSpecification.
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse instance.
     */
    fun delete(url: String, vararg setters: Setter<HTTPRequest<Endpoint>>): HTTPResponse {
        set(url)
        return send(DELETE, *setters)
    }

    /**
     * Making OPTIONS requestSpecification.
     *
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse instance.
     */
    fun options(vararg setters: Setter<HTTPRequest<Endpoint>>) = send(OPTIONS, *setters)

    /**
     * Making OPTIONS requestSpecification.
     *
     * @param url of your requestSpecification.
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse instance.
     */
    fun options(url: String, vararg setters: Setter<HTTPRequest<Endpoint>>): HTTPResponse {
        set(url)
        return send(OPTIONS, *setters)
    }

    /**
     * Making HEAD requestSpecification.
     *
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse instance.
     */
    fun head(vararg setters: Setter<HTTPRequest<Endpoint>>) = send(HEAD, *setters)

    /**
     * Making HEAD requestSpecification.
     *
     * @param url of your requestSpecification.
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse instance.
     */
    fun head(url: String, vararg setters: Setter<HTTPRequest<Endpoint>>): HTTPResponse {
        set(url)
        return send(HEAD, *setters)
    }

    /**
     * Making TRACE requestSpecification.
     *
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse instance.
     */
    fun trace(vararg setters: Setter<HTTPRequest<Endpoint>>) = send(TRACE, *setters)

    /**
     * Making TRACE requestSpecification.
     *
     * @param url of your requestSpecification.
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse instance.
     */
    fun trace(url: String, vararg setters: Setter<HTTPRequest<Endpoint>>): HTTPResponse {
        set(url)
        return send(TRACE, *setters)
    }

    /**
     * Setting url to URL to requestSpecification specification.
     *
     * @param endpoint of requestSpecification.
     */
    private fun set(endpoint: String) {
        val url = URL(endpoint)

        set(protocol(url.protocol), host(url.host), path("${url.path}?${url.query}"))
    }
}