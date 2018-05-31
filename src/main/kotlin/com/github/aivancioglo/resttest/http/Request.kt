package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.http.Settings.Companion.logAllEnabled
import com.github.aivancioglo.resttest.http.Settings.Companion.logIfFailedEnabled
import com.github.aivancioglo.resttest.logger.RequestLogger
import com.github.aivancioglo.resttest.setters.Setter
import io.restassured.RestAssured.given
import io.restassured.http.Method
import io.restassured.http.Method.*
import io.restassured.internal.RequestSpecificationImpl

/**
 * Class for creating request. You can extend it using your own endpoint class.
 */
open class Request {
    val requestSpecification = given().contentType(Settings.contentType)!!
    val oAuth1 = OAuth1()
    val oAuth2 = OAuth2()
    var contentType = Settings.contentType
    val body = HashMap<String, Any>()
    val setters = ArrayList<Setter>()
    lateinit var method: Method

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
            this.setters.add(setter)
    }

    /**
     * Sending your request.
     *
     * @param method of your request.
     * @param setters are setting up request specification.
     * @return Response class instance.
     */
    protected fun send(method: Method, vararg setters: Setter): Response {
        return send(method, "", *setters)
    }

    /**
     * Sending your request.
     *
     * @param method of your request.
     * @param path is a path param.
     * @param setters are setting up request specification.
     * @return Response class instance.
     */
    protected fun send(method: Method, path: String, vararg setters: Setter): Response {
        this.method = method
        this.setters.addAll(setters)

        update()

        if (oAuth1.used && oAuth2.used)
            throw RuntimeException("You can not use OAuth 1.0 and OAuth 2 in the same request!")

        if (oAuth1.used)
            requestSpecification
                    .auth()
                    .oauth(oAuth1.consumerKey, oAuth1.consumerSecret, oAuth1.token, oAuth1.tokenSecret)

        if (oAuth2.used)
            requestSpecification.auth().oauth2(oAuth2.token)

        if (requestSpecification is RequestSpecificationImpl) {
            requestSpecification.setMethod(method)
            requestSpecification.setPath(path)
            if (host.isNotBlank())
                requestSpecification.baseUri("$protocol://$host")
        }

        val requestLogger = RequestLogger(requestSpecification as RequestSpecificationImpl)

        if (logAllEnabled)
            requestLogger.printIfNotPrinted()

        val response: io.restassured.response.Response

        try {
            response = requestSpecification.request(method, path)
        } catch (e: Throwable) {
            if (logIfFailedEnabled)
                requestLogger.printIfNotPrinted()

            throw AssertionError(e)
        }

        return ResponseImpl(requestLogger, response)
    }

    private fun update() {
        val settersNames = setters.map {
            it.javaClass.simpleName.replace(Regex("\\$.+"), "")
        }

        if (contentType.contains("json", true)
                && settersNames.contains("body") && settersNames.contains("param")
                && (method == POST || method == PUT || method == PATCH))
            throw RuntimeException("You can not use \"body\" setter and \"param\" setter at the same time, when content type is JSON")

        setters.forEach { it.update(this) }
    }

    private fun RequestSpecificationImpl.setMethod(method: Method) {
        this.method = method.toString()
    }

    private fun RequestSpecificationImpl.setPath(path: String) {
        path(path)
    }
}