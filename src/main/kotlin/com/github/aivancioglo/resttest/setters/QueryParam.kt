package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

/**
 * Query Param setter.
 *
 * @constructor is creating variables with request query param key and value.
 * @param key of your query param.
 * @param value of your query param.
 */
class QueryParam<in T : HTTPRequest<*>>(private val key: String, val value: Any) : Setter<T> {

    /**
     * Setting query param to your request.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        request.send.queryParam(key, value)
    }
}