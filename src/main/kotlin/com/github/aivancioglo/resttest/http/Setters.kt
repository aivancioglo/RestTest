package com.github.aivancioglo.resttest.http

import io.restassured.http.ContentType
import java.io.File
import java.io.InputStream
import java.net.URLDecoder
import java.util.regex.Pattern

/**
 * Abstract class for using static functions to call setters.
 */
abstract class Setters {
    companion object {

        /**
         * Getting content type setter.
         *
         * @param type of content.
         * @return Setter instance.
         */
        fun contentType(type: ContentType): (HTTPRequest) -> Unit = { it.requestSpecification.contentType(type) }

        /**
         * Getting content type setter.
         *
         * @param type of content.
         * @return Setter instance.
         */
        fun contentType(type: String): (HTTPRequest) -> Unit = { it.requestSpecification.contentType(type) }

        /**
         * Getting param setter.
         *
         * @param key of param.
         * @param value of param.
         * @return Setter instance.
         */
        fun param(key: String, value: Any): (HTTPRequest) -> Unit = { it.requestSpecification.param(key, value) }


        /**
         * Getting query param setter.
         *
         * @param key of query param.
         * @param value of query param.
         * @return Setter instance.
         */
        fun queryParam(key: String, value: Any): (HTTPRequest) -> Unit = { it.requestSpecification.queryParam(key, value) }

        /**
         * Getting form param setter.
         *
         * @param key of form param.
         * @param value of form param.
         * @return Setter instance.
         */
        fun formParam(key: String, value: Any): (HTTPRequest) -> Unit = { it.requestSpecification.formParam(key, value) }

        /**
         * Getting header setter.
         *
         * @param name of header.
         * @param value of header.
         * @return Setter instance.
         */
        fun header(name: String, value: String): (HTTPRequest) -> Unit = { it.requestSpecification.header(name, value) }

        /**
         * Getting requestSpecification body setter.
         *
         * @param body of requestSpecification.
         * @return Setter instance.
         */
        fun body(body: String): (HTTPRequest) -> Unit = { it.requestSpecification.body(body) }

        /**
         * Getting requestSpecification body setter.
         *
         * @param body of requestSpecification.
         * @return Setter instance.
         */
        fun body(body: Any): (HTTPRequest) -> Unit = { it.requestSpecification.body(body) }

        /**
         * Getting requestSpecification protocol setter.
         *
         * @param protocol of requestSpecification.
         * @return Setter instance.
         */
        fun protocol(protocol: String): (HTTPRequest) -> Unit = { it.protocol = protocol }

        /**
         * Getting requestSpecification host setter.
         *
         * @param host of requestSpecification.
         * @return Setter instance.
         */
        fun host(host: String): (HTTPRequest) -> Unit = { it.host = host }

        /**
         * Getting requestSpecification path setter.
         *
         * @param path of requestSpecification.
         * @return Setter instance.
         */
        fun path(path: String): (HTTPRequest) -> Unit = {
            val m = Pattern.compile("[?&]([^&=]+)=([^&=]+)").matcher(path)

            while (m.find())
                it.requestSpecification.queryParam(m.group(1), URLDecoder.decode(m.group(2), "UTF-8"))

            it.requestSpecification.basePath(path)
        }

        /**
         * Getting requestSpecification port setter.
         *
         * @param port of requestSpecification.
         * @return Setter instance.
         */
        fun port(port: Int): (HTTPRequest) -> Unit = { it.requestSpecification.port(port) }

        /**
         * Getting path param setter.
         *
         * @param key of path param.
         * @param value of path param.
         * @return Setter instance.
         */
        fun pathParam(key: String, value: String): (HTTPRequest) -> Unit = { it.requestSpecification.pathParam(key, value) }

        /**
         * Getting multi part setter.
         *
         * @param controlName of the body part. In HTML this is the attribute name of the input tag.
         * @param fileName of the content you're uploading.
         * @param stream you want to requestSpecification.
         * @return Setter instance.
         */
        fun multiPart(controlName: String, fileName: String, stream: InputStream): (HTTPRequest) -> Unit = {
            it.requestSpecification.multiPart(controlName, fileName, stream)
        }

        /**
         * Getting multi part setter.
         *
         * @param controlName of the body part. In HTML this is the attribute name of the input tag.
         * @param fileName of the content you're uploading.
         * @param stream you want to requestSpecification.
         * @param mimeType    The mime-type
         * @return Setter instance.
         */
        fun multiPart(controlName: String, fileName: String, stream: InputStream, mimeType: String): (HTTPRequest) -> Unit = {
            it.requestSpecification.multiPart(controlName, fileName, stream, mimeType)
        }

        /**
         * Getting multi part setter.
         *
         * @param file The file to upload
         * @return Setter instance.
         */
        fun multiPart(file: File): (HTTPRequest) -> Unit = { it.requestSpecification.multiPart(file) }

        /**
         * Getting multi part setter.
         *
         * @param file The file to upload
         * @param controlName Defines the control name of the body part. In HTML this is the attribute name of the input tag.
         * @return Setter instance.
         */
        fun multiPart(controlName: String, file: File): (HTTPRequest) -> Unit = { it.requestSpecification.multiPart(controlName, file) }

        /**
         * Getting redirect setter.
         *
         * @param max count of redirects.
         * @return Setter instance.
         */
        fun redirects(max: Int): (HTTPRequest) -> Unit = { it.requestSpecification.redirects().max(max) }

        /**
         * Getting redirect setter.
         *
         * @param follow redirects.
         * @return Setter instance.
         */
        fun redirects(follow: Boolean): (HTTPRequest) -> Unit = { it.requestSpecification.redirects().follow(follow) }

        /**
         * Getting media type setter.
         *
         * @param mediaType of request.
         * @return Setter instance.
         */
        fun accept(mediaType: String): (HTTPRequest) -> Unit = { it.requestSpecification.accept(mediaType) }

        /**
         * Getting media type setter.
         *
         * @param contentType of request.
         * @return Setter instance.
         */
        fun accept(contentType: ContentType): (HTTPRequest) -> Unit = { it.requestSpecification.accept(contentType) }
    }
}