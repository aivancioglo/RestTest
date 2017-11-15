package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.setters.Path
import com.github.aivancioglo.resttest.setters.Setter
import io.restassured.RestAssured
import io.restassured.config.HttpClientConfig
import io.restassured.http.ContentType
import io.restassured.http.Method
import java.io.InputStream

/**
 * Class for creating request. You can extend it using your own endpoint class.
 */
open class HTTPRequest<out T> {
    val send = RestAssured.given().contentType(ContentType.JSON)
    val oAuth1 = OAuth1()
    val oAuth2 = OAuth2()
    var protocol = "http://"
        set(value) {
            field = "$value://"
        }

    var host = ""

    companion object {

        /**
         * Default Rest Assured settings.
         */
        init {
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            RestAssured.config = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig()
                    .setParam("http.connection.timeout", 20000)
                    .setParam("http.socket.timeout", 60000))
        }
    }

    /**
     * Setting your path to request specification.
     *
     * @param path of your request.
     */
    fun setPath(path: String) {
        Path<HTTPRequest<T>>(path).update(this)
    }

    /**
     * Setting your path param for path building.
     *
     * @param key of your path param.
     * @param value of your path param.
     * @return RequestSpecification class instance.
     */
    protected fun pathParam(key: String, value: Any) = send.pathParam(key, value)

    /**
     * Specify an inputstream to upload to the server using multi-part form data.
     *
     * @param controlName of the body part. In HTML this is the attribute name of the input tag.
     * @param fileName of the content you're uploading.
     * @param stream you want to send.
     * @return RequestSpecification class instance.
     */
    protected fun multiPart(controlName : String, fileName : String, stream : InputStream) = send.multiPart(controlName, fileName, stream)

    /**
     * Sending your request.
     *
     * @param method of your request.
     * @param setters are setting up request specification.
     * @return HTTPResponse class instance.
     */
    protected fun send(method: Method, vararg setters: Setter<HTTPRequest<T>>): HTTPResponse {
        for (setter in setters)
            setter.update(this)

        if (oAuth1.created && oAuth2.created)
            throw RuntimeException("You can not use OAuth 1.0 and OAuth 2 in the same request!")

        if (oAuth1.created)
            send.auth().oauth(oAuth1.consumerKey, oAuth1.consumerSecret, oAuth1.token, oAuth1.tokenSecret)

        if (oAuth2.created)
            send.auth().oauth2(oAuth2.token)

        send.baseUri(protocol + host)

        return HTTPResponse(send.request(method))
    }

    /**
     * Sending your request.
     *
     * @param method of your request.
     * @param path is a path param.
     * @param setters are setting up request specification.
     * @return HTTPResponse class instance.
     */
    protected fun send(method: Method, path: String, vararg setters: Setter<HTTPRequest<T>>): HTTPResponse {
        for (setter in setters)
            setter.update(this)

        if (oAuth1.created && oAuth2.created)
            throw RuntimeException("You can not use OAuth 1.0 and OAuth 2 in the same request!")

        if (oAuth1.created)
            send.auth().oauth(oAuth1.consumerKey, oAuth1.consumerSecret, oAuth1.token, oAuth1.tokenSecret)

        if (oAuth2.created)
            send.auth().oauth2(oAuth2.token)

        send.baseUri(protocol + host)

        return HTTPResponse(send.request(method, path))
    }
}