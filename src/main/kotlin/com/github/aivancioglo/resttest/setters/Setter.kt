package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.Request

/**
 * Request specification setter interface.
 */
interface Setter {

    /**
     * Function for the request update.
     *
     * @param request Request that will be updated.
     */
    fun update(request: Request)
}