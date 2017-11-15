package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest
import io.restassured.http.ContentType

/**
 * Abstract class for using static functions to call setters.
 */
abstract class Setters {
    companion object {

        /**
         * Getting content type setter.
         *
         * @param type of content.
         * @return ContentType setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> contentType(type: ContentType): com.github.aivancioglo.resttest.setters.ContentType<T> =
                com.github.aivancioglo.resttest.setters.ContentType(type)

        /**
         * Getting content type setter.
         *
         * @param type of content.
         * @return ContentType setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> contentType(type: String): com.github.aivancioglo.resttest.setters.ContentType<T> =
                com.github.aivancioglo.resttest.setters.ContentType(type)

        /**
         * Getting param setter.
         *
         * @param key of param.
         * @param value of param.
         * @return Param setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> param(key: String, value: Any): Param<T> = Param(key, value)

        /**
         * Getting query param setter.
         *
         * @param key of query param.
         * @param value of query param.
         * @return QueryParam setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> queryParam(key: String, value: Any): QueryParam<T> = QueryParam(key, value)

        /**
         * Getting form param setter.
         *
         * @param key of form param.
         * @param value of form param.
         * @return FormParam setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> formParam(key: String, value: Any): FormParam<T> = FormParam(key, value)

        /**
         * Getting header setter.
         *
         * @param name of header.
         * @param value of header.
         * @return Header setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> header(name: String, value: String): Header<T> = Header(name, value)

        /**
         * Getting request body setter.
         *
         * @param body of request.
         * @return Body setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> body(body: Any): Body<T> = Body(body)

        /**
         * Getting request protocol setter.
         *
         * @param protocol of request.
         * @return Protocol setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> protocol(protocol: String): Protocol<T> = Protocol(protocol)

        /**
         * Getting request host setter.
         *
         * @param host of request.
         * @return Host setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> host(host: String): Host<T> = Host(host)

        /**
         * Getting request path setter.
         *
         * @param path of request.
         * @return Path setter instance.
         */
        @JvmStatic
        fun <T : HTTPRequest<*>> path(path: String): Path<T> = Path(path)
    }
}