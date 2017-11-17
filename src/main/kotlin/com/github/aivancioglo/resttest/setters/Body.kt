package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

/**
 * Request body setter.
 *
 * @constructor is creating variable with requestSpecification body value.
 * @param body of your requestSpecification.
 */
class Body<in T : HTTPRequest<*>>(private val body: Any) : Setter<T> {

    /**
     * Setting body of your requestSpecification.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        request.requestSpecification.body(body)
    }
}