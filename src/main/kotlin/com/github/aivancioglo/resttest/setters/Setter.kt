package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

/**
 * Request specification setter interface.
 */
interface Setter {

    /**
     * Update your request.
     *
     * @param request that will be updated.
     */
    fun update(request: HTTPRequest)
}