package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

/**
 * Header setter.
 *
 * @constructor is creating variables with request header name and value.
 * @param key of your form param.
 * @param value of your form param.
 */
class Header<in T : HTTPRequest<*>>(private val name: String, private val value: String) : Setter<T> {

    /**
     * Setting header to your request.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        request.send.header(name, value)
    }
}