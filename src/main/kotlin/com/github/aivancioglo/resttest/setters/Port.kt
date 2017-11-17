package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

/**
 * Request port setter.
 *
 * @constructor is creating variable with requestSpecification body value.
 * @param port of your requestSpecification.
 */
class Port<in T : HTTPRequest<*>>(private val port: Int) : Setter<T> {

    /**
     * Setting body of your requestSpecification.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        request.requestSpecification.port(port)
    }
}