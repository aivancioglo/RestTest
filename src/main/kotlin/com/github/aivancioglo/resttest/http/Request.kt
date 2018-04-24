package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.http.Settings.Companion.contentType
import com.github.aivancioglo.resttest.http.Settings.Companion.logAllEnabled
import com.github.aivancioglo.resttest.http.Settings.Companion.logIfFailedEnabled
import com.github.aivancioglo.resttest.logger.RequestLogger
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
    /**
     * This feature will be added on the next version.
     *
    var protocol = getProperty("protocol", "http")
    var host = getProperty("host", "")
     */
    var protocol = "http"
    var host = ""

    /**
     * Initiate your default settings.
     */
    companion object {
        init {
            Settings
        }
    }

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
        return send(method, "", *setters)
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

        if (requestSpecification is RequestSpecificationImpl) {
            requestSpecification.setMethod(method)
            requestSpecification.setPath(path)
        }

        val requestLogger = RequestLogger(requestSpecification as RequestSpecificationImpl)

        if (logAllEnabled)
            requestLogger.printIfNotPrinted()

        val response : io.restassured.response.Response

        try {
            response = requestSpecification.request(method, path)
        } catch (e: Throwable) {
            if (logIfFailedEnabled)
                requestLogger.printIfNotPrinted()

            throw AssertionError(e)
        }

        return ResponseImpl(requestLogger, response)
    }

    private fun RequestSpecificationImpl.setMethod(method: Method) {
        this.method = method.toString()
    }

    private fun RequestSpecificationImpl.setPath(path: String) {
        path(path)
    }
}