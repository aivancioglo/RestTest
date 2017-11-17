package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest
import java.net.URLDecoder
import java.util.regex.Pattern

/**
 * Path setter.
 *
 * @constructor is creating variable with requestSpecification path value.
 * @param path of your requestSpecification.
 */
class Path<in T : HTTPRequest<*>>(private val path: String) : Setter<T> {

    /**
     * Setting path of your requestSpecification.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        val m = Pattern.compile("[?&]([^&=]+)=([^&=]+)").matcher(path)

        while (m.find())
            request.requestSpecification.queryParam(m.group(1), URLDecoder.decode(m.group(2), "UTF-8"))

        request.requestSpecification.basePath(path)
    }
}