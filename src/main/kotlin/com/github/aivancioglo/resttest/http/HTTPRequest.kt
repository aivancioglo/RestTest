package com.github.aivancioglo.resttest.http

import io.restassured.RestAssured
import io.restassured.config.HttpClientConfig
import io.restassured.http.ContentType
import io.restassured.http.Method

/**
 * Class for creating requestSpecification. You can extend it using your own endpoint class.
 */
open class HTTPRequest {
    val requestSpecification = RestAssured.given().contentType(ContentType.JSON)!!
    val oAuth1 = OAuth1()
    val oAuth2 = OAuth2()
    var protocol = "http://"
        set(value) {
            field = "$value://"
        }
    var host = ""

    companion object {

        /**
         * Default request settings.
         */
        init {
            RestAssured.useRelaxedHTTPSValidation()
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
            RestAssured.config = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig()
                    .setParam("http.connection.timeout", 20000)
                    .setParam("http.socket.timeout", 60000))
        }
    }

    /**
     * Set your own setters.
     *
     * @param setters for your request.
     */
    protected fun set(vararg setters: (HTTPRequest) -> Unit) {
        for (setter in setters)
            setter(this)
    }

    /**
     * Sending your requestSpecification.
     *
     * @param method of your requestSpecification.
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse class instance.
     */
    protected fun send(method: Method, vararg setters: (HTTPRequest) -> Unit): HTTPResponse {
        for (setter in setters)
            setter(this)

        if (oAuth1.used && oAuth2.used)
            throw RuntimeException("You can not use OAuth 1.0 and OAuth 2 in the same requestSpecification!")

        if (oAuth1.used)
            requestSpecification
                    .auth()
                    .oauth(oAuth1.consumerKey, oAuth1.consumerSecret, oAuth1.token, oAuth1.tokenSecret)

        if (oAuth2.used)
            requestSpecification.auth().oauth2(oAuth2.token)

        requestSpecification.baseUri(protocol + host)

        return HTTPResponse(requestSpecification.request(method))
    }

    /**
     * Sending your requestSpecification.
     *
     * @param method of your requestSpecification.
     * @param path is a path param.
     * @param setters are setting up requestSpecification specification.
     * @return HTTPResponse class instance.
     */
    protected fun send(method: Method, path: String, vararg setters: (HTTPRequest) -> Unit): HTTPResponse {
        for (setter in setters)
            setter(this)

        if (oAuth1.used && oAuth2.used)
            throw RuntimeException("You can not use OAuth 1.0 and OAuth 2 in the same requestSpecification!")

        if (oAuth1.used)
            requestSpecification
                    .auth()
                    .oauth(oAuth1.consumerKey, oAuth1.consumerSecret, oAuth1.token, oAuth1.tokenSecret)

        if (oAuth2.used)
            requestSpecification.auth().oauth2(oAuth2.token)

        requestSpecification.baseUri(protocol + host)

        return HTTPResponse(requestSpecification.request(method, path))
    }
}