package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

/**
 * Param setter.
 *
 * @constructor is creating variables with requestSpecification param key and value.
 * @param key of your param.
 * @param value of your param.
 */
class Param<in T : HTTPRequest<*>>(private val key: String, private val value: Any) : Setter<T> {

    /**
     * Setting param to your requestSpecification.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        request.requestSpecification.param(key, value)
    }
}