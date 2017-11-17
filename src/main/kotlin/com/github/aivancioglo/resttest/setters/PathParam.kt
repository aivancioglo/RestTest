package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

/**
 * Request path param setter.
 *
 * @constructor is creating variable with requestSpecification body value.
 * @param name of your path param.
 * @param value of your path param.
 */
class PathParam<in T : HTTPRequest<*>>(private val name: String, private val value: String) : Setter<T> {

    /**
     * Setting body of your requestSpecification.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        request.requestSpecification.pathParam(name, value)
    }
}