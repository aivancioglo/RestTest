package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.util.regex.Pattern

class Path<in T : HTTPRequest<*>>(private val path: String) : Setter<T> {
    override fun update(request: T) {
        val m = Pattern.compile("[?&]([^&=]+)=([^&=]+)").matcher(path)

        while (m.find()) {
            try {
                request.send.queryParam(m.group(1), URLDecoder.decode(m.group(2), "UTF-8"))
            } catch (e: UnsupportedEncodingException) {
                throw RuntimeException(e)
            }
        }

        request.send.basePath(path)
    }
}