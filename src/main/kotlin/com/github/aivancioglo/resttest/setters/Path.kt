package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest
import java.net.URLDecoder
import java.util.regex.Pattern

/**
 * Path setter.
 *
 * @constructor is creating variable with request path value.
 * @param path of your request.
 */
class Path<in T : HTTPRequest<*>>(private val path: String) : Setter<T> {

    /**
     * Setting path of your request.
     *
     * @param request that will be updated.
     */
    override fun update(request: T) {
        val m = Pattern.compile("[?&]([^&=]+)=([^&=]+)").matcher(path)

        while (m.find())
            request.send.queryParam(m.group(1), URLDecoder.decode(m.group(2), "UTF-8"))

        request.send.basePath(path)
    }
}