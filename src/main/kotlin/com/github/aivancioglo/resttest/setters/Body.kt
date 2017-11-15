package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

/**
 * Request body setter.
 *
 * @constructor is creating variable with request body value.
 * @param body of your request.
 */
class Body<in T : HTTPRequest<*>>(private val body: Any) : Setter<T> {

    /**
     * Setting body of your request.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        request.send.body(body)
    }
}