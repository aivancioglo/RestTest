package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

/**
 * Host setter.
 *
 * @constructor is creating variable with request host value.
 * @param host of your request.
 */
class Host<in T : HTTPRequest<*>>(private val host: String) : Setter<T> {

    /**
     * Setting host of your request.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        request.host = host
    }
}