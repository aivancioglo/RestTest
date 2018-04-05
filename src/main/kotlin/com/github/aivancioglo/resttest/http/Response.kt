package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.http.Settings.Companion.logAllRequestsEnabled
import com.github.aivancioglo.resttest.http.Settings.Companion.logIfFailedEnabled
import com.github.aivancioglo.resttest.http.Settings.Companion.softAssertionEnabled
import com.github.aivancioglo.resttest.logger.LogType
import com.github.aivancioglo.resttest.logger.LogType.ALL
import com.github.aivancioglo.resttest.logger.Logger
import com.github.aivancioglo.resttest.verifiers.Verifier
import com.github.aivancioglo.resttest.verifiers.Verifiers.Companion.jsonSchema
import com.github.aivancioglo.resttest.verifiers.Verifiers.Companion.statusCode
import io.restassured.http.Header
import io.restassured.internal.RequestSpecificationImpl
import io.restassured.mapper.ObjectMapper
import io.restassured.mapper.ObjectMapperType
import io.restassured.response.Response

/**
 * This class is using for HTTP/HTTPS response validation and processing.
 */
abstract class Response() {
    protected lateinit var request: RequestSpecificationImpl
    protected lateinit var response: Response
    protected lateinit var logger: Logger
    private val errors: ArrayList<AssertionError> = ArrayList()

    constructor(request: RequestSpecificationImpl, response: Response) : this() {
        this.request = request
        this.response = response
        logger = Logger(request, response)

        if (logAllRequestsEnabled)
            log()
    }

    /**
     * Making responseSpecification validation.
     *
     * @param verifiers for responseSpecification validation.
     */
    @SafeVarargs
    fun assertThat(verifier: Verifier, vararg verifiers: Verifier) = printFailuresIfExist(verifier, *verifiers)

    /**
     * Making responseSpecification validation.
     *
     * @param code of responseSpecification.
     * @param verifiers for responseSpecification validation.
     */
    @SafeVarargs
    fun assertThat(code: Int, vararg verifiers: Verifier) = printFailuresIfExist(statusCode(code), *verifiers)

    /**
     * Making responseSpecification validation.
     *
     * @param statusCode of responseSpecification.
     * @param verifiers for responseSpecification validation.
     */
    @SafeVarargs
    fun assertThat(statusCode: StatusCode, vararg verifiers: Verifier) = printFailuresIfExist(
            statusCode(statusCode), *verifiers)

    /**
     * Making responseSpecification validation.
     *
     * @param code of responseSpecification.
     * @param jsonSchema for responseSpecification validation.
     * @param verifiers for responseSpecification validation.
     */
    @SafeVarargs
    fun assertThat(code: Int, jsonSchema: String, vararg verifiers: Verifier) = printFailuresIfExist(
            statusCode(code), jsonSchema(jsonSchema), *verifiers)

    /**
     * Making responseSpecification validation.
     *
     * @param statusCode of responseSpecification.
     * @param jsonSchema for responseSpecification validation.
     * @param verifiers for responseSpecification validation.
     */
    @SafeVarargs
    fun assertThat(statusCode: StatusCode, jsonSchema: String, vararg verifiers: Verifier) = printFailuresIfExist(
            statusCode(statusCode), jsonSchema(jsonSchema), *verifiers)

    /**
     * Response logging.
     *
     * @return this class instance.
     */
    @JvmOverloads
    fun log(type: LogType = ALL) = apply {
        logger.log(type)
        return this
    }

    /**
     * For getting responseSpecification code of last responseSpecification.
     *
     * @return responseSpecification code.
     */
    fun getStatusCode() = response.statusCode

    /**
     * For getting body of last responseSpecification as string.
     *
     * @return body as string.
     */
    fun getBody() = response.body.asString()!!

    /**
     * Deserialize responseSpecification body as your model class.
     *
     * @param cls that of your module.
     * @param T is responseSpecification model.
     * @return deserialize body as your model class.
     */
    @JvmName("as")
    fun <T> to(cls: Class<T>): T {
        val model = if (response.body.asString().trim().isEmpty() ||
                (!response.contentType.contains("JSON", true) &&
                        !response.contentType.contains("XML", true)))
            cls.newInstance()!!
        else
            response.`as`(cls)!!

        if (Model::class.java.isAssignableFrom(cls))
            (model as Model).set(request, response)

        return model
    }

    /**
     * Deserialize responseSpecification body as your model class.
     *
     * @param cls that of your module.
     * @param objectMapper for response body deserializing.
     * @param T is responseSpecification model.
     * @return deserialize body as your model class.
     */
    @JvmName("as")
    fun <T> to(cls: Class<T>, objectMapper: ObjectMapper): T {
        val model = if (response.body.asString().trim().isEmpty() ||
                (!response.contentType.contains("JSON", true) &&
                        !response.contentType.contains("XML", true)))
            cls.newInstance()!!
        else
            response.`as`(cls, objectMapper)!!

        if (Model::class.java.isAssignableFrom(cls))
            (model as Model).set(request, response)

        return model
    }

    /**
     * Deserialize responseSpecification body as your model class.
     *
     * @param cls that of your module.
     * @param objectMapperType for response body deserializing.
     * @param T is responseSpecification model.
     * @return deserialize body as your model class.
     */
    @JvmName("as")
    fun <T> to(cls: Class<T>, objectMapperType: ObjectMapperType): T {
        val model = if (response.body.asString().trim().isEmpty() ||
                (!response.contentType.contains("JSON", true) &&
                        !response.contentType.contains("XML", true)))
            cls.newInstance()!!
        else
            response.`as`(cls, objectMapperType)!!

        if (Model::class.java.isAssignableFrom(cls))
            (model as Model).set(request, response)

        return model
    }

    /**
     * Extract value by JSON path.
     *
     * @param path1 JSON path1.
     * @param path2 JSON path2.
     * @param T is returning type.
     * @return path value.
     */
    fun <T> path(path1: String, vararg path2: String): T = response.path(path1, *path2)

    /**
     * Get responseSpecification header by name.
     *
     * @param name of header.
     * @return header value.
     */
    fun header(name: String) = response.getHeader(name)!!

    /**
     * Returns list of headers.
     *
     * @return list.
     */
    fun headers(): List<Header> = response.headers.asList()

    /**
     * Get the content type of the response
     *
     * @return the content type value or <code>null</code> if not found.
     */
    fun contentType() = response.contentType()!!

    /**
     * Check if header exist.
     *
     * @param name of header.
     * @return boolean value.
     */
    fun isHeaderExist(name: String) = response.headers.hasHeaderWithName(name)

    private fun printFailuresIfExist(vararg verifiers: Verifier): Logger {
        verifiers.forEach {
            try {
                it.verify(response)
            } catch (e: AssertionError) {
                if (softAssertionEnabled)
                    throw e
                else
                    errors.add(e)
            }
        }

        if (errors.size > 0) {
            if (!logAllRequestsEnabled && logIfFailedEnabled)
                log()

            if (softAssertionEnabled)
                throw AssertionError("\n\n============= F A I L U R E S =============\n${errors[0]}\n===========================================")
            else
                throw AssertionError("\n\n============= F A I L U R E S =============\n" + errors.map {
                    it.message!!.replace(Regex("^1 expectation failed\\."), "")
                }.joinToString("\n-------------------------------------------\n") + "\n===========================================")

        }

        return logger
    }
}