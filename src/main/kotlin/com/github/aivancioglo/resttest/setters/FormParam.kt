package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

/**
 * Form Param setter.
 *
 * @constructor is creating variables with requestSpecification form param key and value.
 * @param key of your form param.
 * @param value of your form param.
 */
class FormParam<in T : HTTPRequest<*>>(private val key: String, val value: Any) : Setter<T> {

    /**
     * Setting form param to your requestSpecification.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        request.requestSpecification.formParam(key, value)
    }
}