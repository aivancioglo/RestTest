package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.ContentType
import com.github.aivancioglo.resttest.http.Request
import io.restassured.http.Method.*
import io.restassured.mapper.ObjectMapperType.JACKSON_2
import io.restassured.specification.MultiPartSpecification
import java.io.File
import java.io.InputStream
import java.io.Serializable
import java.net.URLDecoder
import java.util.regex.Pattern

/**
 * Abstract class for calling setters using static functions.
 */
abstract class Setters {
    companion object {

        /**
         * Get content type setter.
         *
         * @param type Content type of the request.
         * @return Setter instance.
         */
        @JvmStatic
        fun contentType(type: ContentType) = object : Setter {
            override fun update(request: Request) {
                request.contentType = type.value
                request.requestSpecification.contentType(type.value)
            }
        }

        /**
         * Get content type setter.
         *
         * @param type Content type of the request.
         * @return Setter instance.
         */
        @JvmStatic
        fun contentType(type: String) = object : Setter {
            override fun update(request: Request) {
                request.contentType = type
                request.requestSpecification.contentType(type)
            }
        }

        /**
         * Get param setter.
         *
         * @param key Key of param.
         * @param value Value of param.
         * @return Setter instance.
         */
        @JvmStatic
        fun param(key: String, value: Any) = object : Setter {
            override fun update(request: Request) {
                if (request.contentType.contains("json", true) &&
                        (request.method == POST || request.method == PUT || request.method == PATCH)) {
                    request.body[key] = value
                    request.requestSpecification.body(request.body, JACKSON_2)
                } else request.requestSpecification.params(mapOf(key to value))
            }
        }

        /**
         * Get query param setter.
         *
         * @param key Key of query param.
         * @param value Value of query param.
         * @return Setter instance.
         */
        @JvmStatic
        fun queryParam(key: String, value: Any) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.queryParams(mapOf(key to value))
            }
        }

        /**
         * Get form param setter.
         *
         * @param key Key of form param.
         * @param value Value of form param.
         * @return Setter instance.
         */
        @JvmStatic
        fun formParam(key: String, value: Any) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.formParams(mapOf(key to value))
            }
        }

        /**
         * Get json param setter.
         *
         * @param key Key of json param.
         * @param value Value of json param.
         * @return Setter instance.
         */
        @JvmStatic
        fun jsonParam(key: String, value: Any) = object : Setter {
            override fun update(request: Request) {
                if (request.contentType.contains("json"))
                    if (request.method == POST || request.method == PUT || request.method == PATCH) {
                        request.body[key] = value
                        request.requestSpecification.body(request.body, JACKSON_2)
                    } else
                        throw RuntimeException("You can use \"jsonParam\" setter only for POST, PUT or PATCH requests.")
                else
                    throw RuntimeException("You can not use \"jsonParam\" setter when content type is not JSON.")
            }
        }

        /**
         * Get cookie setter.
         *
         * @param name Name of cookie.
         * @param value Value of cookie.
         * @param additionalValues Additional values of cookie.
         * @return Setter instance.
         */
        @JvmStatic
        fun cookie(name: String, value: String, vararg additionalValues: String) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.cookie(name, value, *additionalValues)
            }
        }

        /**
         * Get cookie setter.
         *
         * @param name Name of cookie.
         * @param value Value of cookie.
         * @return Setter instance.
         */
        @JvmStatic
        fun cookie(name: String, value: String) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.cookie(name, value)
            }
        }

        /**
         * Get cookie setter.
         *
         * @param name Name of cookie.
         * @return Setter instance.
         */
        @JvmStatic
        fun cookie(name: String) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.cookie(name)
            }
        }

        /**
         * Get header setter.
         *
         * @param name Name of header.
         * @param value Value of header.
         * @return Setter instance.
         */
        @JvmStatic
        fun header(name: String, value: String) = object : Setter {
            override fun update(request: Request) {
                if (name.equals("content-type", true))
                    request.contentType = value

                request.requestSpecification.header(name, value)
            }
        }

        /**
         * Get requestSpecification body setter.
         *
         * @param body Body of requestSpecification.
         * @return Setter instance.
         */
        @JvmStatic
        fun body(body: String) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.body(body)
            }
        }

        /**
         * Get requestSpecification body setter.
         *
         * @param body Body of requestSpecification.
         * @return Setter instance.
         */
        @JvmStatic
        fun body(body: Serializable) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.body(body)
            }
        }

        /**
         * Get requestSpecification protocol setter.
         *
         * @param protocol Protocol of requestSpecification.
         * @return Setter instance.
         */
        @JvmStatic
        fun protocol(protocol: String) = object : Setter {
            override fun update(request: Request) {
                request.protocol = protocol
            }
        }

        /**
         * Get requestSpecification host setter.
         *
         * @param host Host of requestSpecification.
         * @return Setter instance.
         */
        @JvmStatic
        fun host(host: String) = object : Setter {
            override fun update(request: Request) {
                request.host = host
            }
        }

        /**
         * Get requestSpecification path setter.
         *
         * @param path Path of requestSpecification.
         * @return Setter instance.
         */
        @JvmStatic
        fun path(path: String) = object : Setter {
            override fun update(request: Request) {
                val m = Pattern.compile("[?&]([^&=]+)=([^&=]+)").matcher(path)

                while (m.find())
                    request.requestSpecification
                            .queryParam(m.group(1), URLDecoder.decode(m.group(2), "UTF-8"))

                request.requestSpecification.basePath(path)
            }
        }

        /**
         * Get requestSpecification port setter.
         *
         * @param port Port of requestSpecification.
         * @return Setter instance.
         */
        @JvmStatic
        fun port(port: Int) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.port(port)
            }
        }

        /**
         * Get path param setter.
         *
         * @param key Key of path param.
         * @param value Value of path param.
         * @return Setter instance.
         */
        @JvmStatic
        fun pathParam(key: String, value: Any) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.pathParam(key, value)
            }
        }

        /**
         * Get multi part setter.
         *
         * @param controlName Control name of the body part. In HTML this is the attribute name of the input tag.
         * @param fileName File name of the content you're uploading.
         * @param stream Stream you want to requestSpecification.
         * @return Setter instance.
         */
        @JvmStatic
        fun multiPart(controlName: String, fileName: String, stream: InputStream) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.multiPart(controlName, fileName, stream)
            }
        }

        /**
         * Get multi part setter.
         *
         * @param controlName Control name of the body part. In HTML this is the attribute name of the input tag.
         * @param fileName File name of the content you're uploading.
         * @param stream Stream you want to requestSpecification.
         * @param mimeType The mime-type.
         * @return Setter instance.
         */
        @JvmStatic
        fun multiPart(controlName: String,
                      fileName: String,
                      stream: InputStream,
                      mimeType: String) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.multiPart(controlName, fileName, stream, mimeType)
            }
        }

        /**
         * Get multi part setter.
         *
         * @param file File to upload.
         * @return Setter instance.
         */
        @JvmStatic
        fun multiPart(file: File) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.multiPart(file)
            }
        }

        /**
         * Get multi part setter.
         *
         * @param file File to upload.
         * @param controlName Defines the control name of the body part. In HTML this is the attribute name of the input tag.
         * @return Setter instance.
         */
        @JvmStatic
        fun multiPart(controlName: String, file: File) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.multiPart(controlName, file)
            }
        }

        /**
         * Get multi part setter.
         *
         * @param multiPartSpecification Multi part specification of your request.
         * @return Setter instance.
         */
        @JvmStatic
        fun multiPart(multiPartSpecification: MultiPartSpecification) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.multiPart(multiPartSpecification)
            }
        }

        /**
         * Get redirect setter.
         *
         * @param max Maximum count of redirects.
         * @return Setter instance.
         */
        @JvmStatic
        fun redirects(max: Int) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.redirects().max(max)
            }
        }

        /**
         * Get redirect setter.
         *
         * @param follow Follow redirects.
         * @return Setter instance.
         */
        @JvmStatic
        fun redirects(follow: Boolean) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.redirects().follow(follow)
            }
        }

        /**
         * Get accept setter.
         *
         * @param mediaType Media type of request.
         * @return Setter instance.
         */
        @JvmStatic
        fun accept(mediaType: String) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.accept(mediaType)
            }
        }

        /**
         * Getting accept setter.
         *
         * @param contentType Content type of request.
         * @return Setter instance.
         */
        @JvmStatic
        fun accept(contentType: ContentType) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.accept(contentType.value)
            }
        }

        /**
         * Specifies if RestTest should url encode the URL automatically. Usually, this is recommended but in some cases
         * (e.g. the query parameters are already be encoded before you provide them to RestTest) then it's useful to disable
         * URL encoding.
         *
         * @param isEnabled Is URL encoding enabled or disabled.
         * @return Setter instance.
         */
        @JvmStatic
        fun urlEncodingEnabled(isEnabled: Boolean = true) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.urlEncodingEnabled(isEnabled)
            }
        }

        /**
         * Use http basic authentication.
         *
         * @param userName User name of your account.
         * @param password Password of your account.
         * @return Setter instance.
         */
        @JvmStatic
        fun basicAuth(userName: String, password: String) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.auth().basic(userName, password)
            }
        }

        /**
         * Set all OAuth 1.0 authentication settings.
         *
         * @return Setter instance.
         */
        @JvmStatic
        fun oauth1(consumerKey: String, consumerSecret: String, token: String, tokenSecret: String) = object : Setter {
            override fun update(request: Request) {
                request.oAuth1.used = true
                request.oAuth1.consumerKey = consumerKey
                request.oAuth1.consumerSecret = consumerSecret
                request.oAuth1.token = token
                request.oAuth1.tokenSecret = tokenSecret
            }
        }

        /**
         * Set all OAuth 2.0 authentication settings.
         *
         * @return Setter instance.
         */
        @JvmStatic
        fun oauth2(token: String) = object : Setter {
            override fun update(request: Request) {
                request.oAuth2.used = true
                request.oAuth2.token = token
            }
        }

        /**
         * Clear all authentication settings.
         *
         * @return Setter instance.
         */
        @JvmStatic
        fun noAuth() = object : Setter {
            override fun update(request: Request) {
                request.oAuth1.used = false
                request.oAuth2.used = false
                request.requestSpecification.auth().none()
            }
        }
    }
}