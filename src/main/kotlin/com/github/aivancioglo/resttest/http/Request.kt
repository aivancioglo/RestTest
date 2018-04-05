package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.http.Settings.Companion.contentType
import com.github.aivancioglo.resttest.setters.Setter
import io.restassured.RestAssured.given
import io.restassured.http.Method
import io.restassured.internal.RequestSpecificationImpl

/**
 * Class for creating request. You can extend it using your own endpoint class.
 */
open class Request {
    val requestSpecification = given().contentType(contentType)!!
    val oAuth1 = OAuth1()
    val oAuth2 = OAuth2()
    var protocol = "http"
    var host = ""

    /**
     * Set your own setters.
     *
     * @param setters for your request.
     */
    protected fun set(vararg setters: Setter) {
        for (setter in setters)
            setter.update(this)
    }

    /**
     * Sending your requestSpecification.
     *
     * @param method of your requestSpecification.
     * @param setters are setting up requestSpecification specification.
     * @return Response class instance.
     */
    protected fun send(method: Method, vararg setters: Setter): Response {
        for (setter in setters)
            setter.update(this)

        if (oAuth1.used && oAuth2.used)
            throw RuntimeException("You can not use OAuth 1.0 and OAuth 2 in the same request!")

        if (oAuth1.used)
            requestSpecification
                    .auth()
                    .oauth(oAuth1.consumerKey, oAuth1.consumerSecret, oAuth1.token, oAuth1.tokenSecret)

        if (oAuth2.used)
            requestSpecification.auth().oauth2(oAuth2.token)

        requestSpecification.baseUri("$protocol://$host")

        return ResponseImpl(requestSpecification as RequestSpecificationImpl, requestSpecification.request(method)!!)
    }

    /**
     * Sending your requestSpecification.
     *
     * @param method of your request.
     * @param path is a path param.
     * @param setters are setting up request specification.
     * @return Response class instance.
     */
    protected fun send(method: Method, path: String, vararg setters: Setter): Response {
        for (setter in setters)
            setter.update(this)

        if (oAuth1.used && oAuth2.used)
            throw RuntimeException("You can not use OAuth 1.0 and OAuth 2 in the same request!")

        if (oAuth1.used)
            requestSpecification
                    .auth()
                    .oauth(oAuth1.consumerKey, oAuth1.consumerSecret, oAuth1.token, oAuth1.tokenSecret)

        if (oAuth2.used)
            requestSpecification.auth().oauth2(oAuth2.token)

        requestSpecification.baseUri("$protocol://$host")

        return ResponseImpl(requestSpecification as RequestSpecificationImpl, requestSpecification.request(method, path)!!)
    }
}