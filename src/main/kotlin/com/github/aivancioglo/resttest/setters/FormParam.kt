package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

/**
 * Form Param setter.
 *
 * @constructor is creating variables with request form param key and value.
 * @param key of your form param.
 * @param value of your form param.
 */
class FormParam<in T : HTTPRequest<*>>(private val key: String, val value: Any) : Setter<T> {

    /**
     * Setting form param to your request.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        request.send.formParam(key, value)
    }
}