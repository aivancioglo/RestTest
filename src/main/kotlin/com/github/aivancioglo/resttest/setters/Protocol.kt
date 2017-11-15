package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

/**
 * Protocol setter.
 *
 * @constructor is creating variable with request protocol value.
 * @param protocol of your request.
 */
class Protocol<in T : HTTPRequest<*>>(private val protocol: String) : Setter<T> {

    /**
     * Setting protocol of your request.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        request.protocol = protocol
    }
}