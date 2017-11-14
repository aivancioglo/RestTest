package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest
import io.restassured.http.ContentType

abstract class Setters {
    companion object {
        @JvmStatic
        fun <T : HTTPRequest<*>> contentType(type: ContentType): com.github.aivancioglo.resttest.setters.ContentType<T> =
                com.github.aivancioglo.resttest.setters.ContentType(type)

        @JvmStatic
        fun <T : HTTPRequest<*>> contentType(type: String): com.github.aivancioglo.resttest.setters.ContentType<T> =
                com.github.aivancioglo.resttest.setters.ContentType(type)

        @JvmStatic
        fun <T : HTTPRequest<*>> param(key: String, value: Any): Param<T> = Param(key, value)

        @JvmStatic
        fun <T : HTTPRequest<*>> queryParam(key: String, value: Any): QueryParam<T> = QueryParam(key, value)

        @JvmStatic
        fun <T : HTTPRequest<*>> formParam(key: String, value: Any): FormParam<T> = FormParam(key, value)

        @JvmStatic
        fun <T : HTTPRequest<*>> header(name: String, value: String): Header<T> = Header(name, value)

        @JvmStatic
        fun <T : HTTPRequest<*>> body(body: Any): Body<T> = Body(body)

        @JvmStatic
        fun <T : HTTPRequest<*>> protocol(protocol: String): Protocol<T> = Protocol(protocol)

        @JvmStatic
        fun <T : HTTPRequest<*>> host(host: String): Host<T> = Host(host)

        @JvmStatic
        fun <T : HTTPRequest<*>> path(path: String): Path<T> = Path(path)
    }
}