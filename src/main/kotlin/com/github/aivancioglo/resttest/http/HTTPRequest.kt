package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.setters.Path
import com.github.aivancioglo.resttest.setters.Setter
import io.restassured.RestAssured
import io.restassured.config.HttpClientConfig
import io.restassured.http.ContentType
import io.restassured.http.Method

open class HTTPRequest<T : HTTPRequest<T>> {
    val send = RestAssured.given().contentType(ContentType.JSON)
    val oAuth1 = OAuth1()
    val oAuth2 = OAuth2()
    var protocol = ""
        set(value) {
            field = "$value://"
        }

    var host = ""

    companion object {
        init {
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            RestAssured.config = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig()
                    .setParam("http.connection.timeout", 20000)
                    .setParam("http.socket.timeout", 60000))
        }
    }

    fun setPath(path: String) {
        Path<T>(path).update(this as T)
    }

    protected fun pathParam(key: String, value: Any) = send.pathParam(key, value)

    protected fun send(method: Method, vararg setters: Setter<T>): HTTPResponse {
        Setter.set(this as T, *setters)

        if (oAuth1.created && oAuth2.created)
            throw RuntimeException("You can not use OAuth 1.0 and OAuth 2 in the same request!")

        if (oAuth1.created)
            send.auth().oauth(oAuth1.consumerKey, oAuth1.consumerSecret, oAuth1.token, oAuth1.tokenSecret)

        if (oAuth2.created)
            send.auth().oauth2(oAuth2.token)

        send.baseUri(protocol + host)

        return HTTPResponse(send.request(method))
    }

    protected fun send(method: Method, path: String, vararg setters: Setter<T>): HTTPResponse {
        Setter.set(this as T, *setters)

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