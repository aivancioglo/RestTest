package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.ContentType
import com.github.aivancioglo.resttest.http.Request
import io.restassured.specification.MultiPartSpecification
import java.io.File
import java.io.InputStream
import java.io.Serializable
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
        @JvmStatic
        fun contentType(type: ContentType) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.contentType(type.type)
            }
        }

        /**
         * Getting content type setter.
         *
         * @param type of content.
         * @return Setter instance.
         */
        @JvmStatic
        fun contentType(type: String) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.contentType(type)
            }
        }

        /**
         * Getting param setter.
         *
         * @param key of param.
         * @param value of param.
         * @return Setter instance.
         */
        @JvmStatic
        fun param(key: String, value: Any) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.param(key, value)
            }
        }

        /**
         * Getting query param setter.
         *
         * @param key of query param.
         * @param value of query param.
         * @return Setter instance.
         */
        @JvmStatic
        fun queryParam(key: String, value: Any) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.queryParam(key, value)
            }
        }

        /**
         * Getting form param setter.
         *
         * @param key of form param.
         * @param value of form param.
         * @return Setter instance.
         */
        @JvmStatic
        fun formParam(key: String, value: Any) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.formParam(key, value)
            }
        }

        /**
         * Getting cookie setter.
         *
         * @param name of header.
         * @param value of header.
         * @return Setter instance.
         */
        @JvmStatic
        fun cookie(name: String, value: String, vararg additionalValues: String) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.cookie(name, value, *additionalValues)
            }
        }

        /**
         * Getting cookie setter.
         *
         * @param name of header.
         * @param value of header.
         * @return Setter instance.
         */
        @JvmStatic
        fun cookie(name: String, value: String) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.cookie(name, value)
            }
        }

        /**
         * Getting cookie setter.
         *
         * @param name of header.
         * @return Setter instance.
         */
        @JvmStatic
        fun cookie(name: String) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.cookie(name)
            }
        }

        /**
         * Getting header setter.
         *
         * @param name of header.
         * @param value of header.
         * @return Setter instance.
         */
        @JvmStatic
        fun header(name: String, value: String) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.header(name, value)
            }
        }

        /**
         * Getting requestSpecification body setter.
         *
         * @param body of requestSpecification.
         * @return Setter instance.
         */
        @JvmStatic
        fun body(body: String) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.body(body)
            }
        }

        /**
         * Getting requestSpecification body setter.
         *
         * @param body of requestSpecification.
         * @return Setter instance.
         */
        @JvmStatic
        fun body(body: Serializable) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.body(body)
            }
        }

        /**
         * Getting requestSpecification protocol setter.
         *
         * @param protocol of requestSpecification.
         * @return Setter instance.
         */
        @JvmStatic
        fun protocol(protocol: String) = object : Setter {
            override fun update(request: Request) {
                request.protocol = protocol
            }
        }

        /**
         * Getting requestSpecification host setter.
         *
         * @param host of requestSpecification.
         * @return Setter instance.
         */
        @JvmStatic
        fun host(host: String) = object : Setter {
            override fun update(request: Request) {
                request.host = host
            }
        }

        /**
         * Getting requestSpecification path setter.
         *
         * @param path of requestSpecification.
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
         * Getting requestSpecification port setter.
         *
         * @param port of requestSpecification.
         * @return Setter instance.
         */
        @JvmStatic
        fun port(port: Int) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.port(port)
            }
        }

        /**
         * Getting path param setter.
         *
         * @param key of path param.
         * @param value of path param.
         * @return Setter instance.
         */
        @JvmStatic
        fun pathParam(key: String, value: Any) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.pathParam(key, value)
            }
        }

        /**
         * Getting multi part setter.
         *
         * @param controlName of the body part. In HTML this is the attribute name of the input tag.
         * @param fileName of the content you're uploading.
         * @param stream you want to requestSpecification.
         * @return Setter instance.
         */
        @JvmStatic
        fun multiPart(controlName: String, fileName: String, stream: InputStream) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.multiPart(controlName, fileName, stream)
            }
        }

        /**
         * Getting multi part setter.
         *
         * @param controlName of the body part. In HTML this is the attribute name of the input tag.
         * @param fileName of the content you're uploading.
         * @param stream you want to requestSpecification.
         * @param mimeType The mime-type
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
         * Getting multi part setter.
         *
         * @param file to upload
         * @return Setter instance.
         */
        @JvmStatic
        fun multiPart(file: File) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.multiPart(file)
            }
        }

        /**
         * Getting multi part setter.
         *
         * @param file to upload
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
         * Getting multi part setter.
         *
         * @param multiPartSpecification of yur request.
         * @return Setter instance.
         */
        @JvmStatic
        fun multiPart(multiPartSpecification: MultiPartSpecification) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.multiPart(multiPartSpecification)
            }
        }

        /**
         * Getting redirect setter.
         *
         * @param max count of redirects.
         * @return Setter instance.
         */
        @JvmStatic
        fun redirects(max: Int) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.redirects().max(max)
            }
        }

        /**
         * Getting redirect setter.
         *
         * @param follow redirects.
         * @return Setter instance.
         */
        @JvmStatic
        fun redirects(follow: Boolean) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.redirects().follow(follow)
            }
        }

        /**
         * Getting media type setter.
         *
         * @param mediaType of request.
         * @return Setter instance.
         */
        @JvmStatic
        fun accept(mediaType: String) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.accept(mediaType)
            }
        }

        /**
         * Getting media type setter.
         *
         * @param contentType of request.
         * @return Setter instance.
         */
        @JvmStatic
        fun accept(contentType: ContentType) = object : Setter {
            override fun update(request: Request) {
                request.requestSpecification.accept(contentType.type)
            }
        }

        /**
         * Specifies if RestTest should url encode the URL automatically. Usually this is a recommended but in some cases
         * e.g. the query parameters are already be encoded before you provide them to RestTest then it's useful to disable
         * URL encoding.
         *
         * @param isEnabled URL encoding or disabled.
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
         * @param userName of your account.
         * @param password of your account.
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