package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.http.Settings.Companion.logAllEnabled
import com.github.aivancioglo.resttest.http.Settings.Companion.logIfFailedEnabled
import com.github.aivancioglo.resttest.http.Settings.Companion.softAssertionsEnabled
import com.github.aivancioglo.resttest.logger.LogType
import com.github.aivancioglo.resttest.logger.LogType.ALL
import com.github.aivancioglo.resttest.logger.Logger
import com.github.aivancioglo.resttest.logger.RequestLogger
import com.github.aivancioglo.resttest.logger.ResponseLogger
import com.github.aivancioglo.resttest.verifiers.Verifier
import com.github.aivancioglo.resttest.verifiers.Verifiers.Companion.schema
import com.github.aivancioglo.resttest.verifiers.Verifiers.Companion.statusCode
import io.restassured.http.Header
import io.restassured.mapper.ObjectMapper
import io.restassured.mapper.ObjectMapperType
import io.restassured.response.Response
import io.restassured.response.ValidatableResponse
import java.util.*
import kotlin.reflect.KClass

/**
 * This class is using for HTTP/HTTPS response validation and processing.
 */
abstract class Response() {
    var rootPath = ""
        private set

    protected lateinit var response: Response
    protected lateinit var validatableResponse: ValidatableResponse
    protected lateinit var logger: Logger
    private val errors: ArrayList<AssertionError> = ArrayList()

    constructor(requestLogger: RequestLogger, response: Response) : this() {
        this.response = response
        logger = Logger(requestLogger, ResponseLogger(response))
        validatableResponse = response.then()!!

        if (logAllEnabled)
            log()
    }

    constructor(logger: Logger, response: Response) : this() {
        this.logger = logger
        this.response = response
        validatableResponse = response.then()!!

        if (logAllEnabled)
            log()
    }

    /**
     * Making response validation.
     *
     * @param verifiers for response validation.
     */
    @SafeVarargs
    fun assertThat(verifier: Verifier, vararg verifiers: Verifier) = printFailuresIfExist(verifier, *verifiers)

    /**
     * Making response validation.
     *
     * @param verifiers for response validation.
     */
    @SafeVarargs
    fun assertThat(verifiers: Array<Verifier>) = printFailuresIfExist(*verifiers)

    /**
     * Making response validation.
     *
     * @param code of response.
     * @param verifiers for response validation.
     */
    @SafeVarargs
    fun assertThat(code: Int, vararg verifiers: Verifier) = printFailuresIfExist(statusCode(code), *verifiers)

    /**
     * Making response validation.
     *
     * @param statusCode of response.
     * @param verifiers for response validation.
     */
    @SafeVarargs
    fun assertThat(statusCode: StatusCode, vararg verifiers: Verifier) = printFailuresIfExist(
            statusCode(statusCode), *verifiers)

    /**
     * Making response validation.
     *
     * @param statusCodes of response.
     * @param verifiers for response validation.
     */
    @SafeVarargs
    fun assertThat(statusCodes: Pair<StatusCode, StatusCode>, vararg verifiers: Verifier) = printFailuresIfExist(
            statusCode(statusCodes), *verifiers)

    /**
     * Making response validation.
     *
     * @param code of response.
     * @param schema for response validation.
     * @param verifiers for response validation.
     */
    @SafeVarargs
    fun assertThat(code: Int, schema: String, vararg verifiers: Verifier) = printFailuresIfExist(
            statusCode(code), schema(schema), *verifiers)

    /**
     * Making response validation.
     *
     * @param statusCode of response.
     * @param verifiers for response validation.
     */
    @SafeVarargs
    fun assertThat(statusCode: StatusCode, schema: String, vararg verifiers: Verifier) = printFailuresIfExist(
            statusCode(statusCode), schema(schema), *verifiers)

    /**
     * Making response validation.
     *
     * @param statusCodes of response.
     * @param verifiers for response validation.
     */
    @SafeVarargs
    fun assertThat(statusCodes: Pair<StatusCode, StatusCode>, schema: String, vararg verifiers: Verifier) = printFailuresIfExist(
            statusCode(statusCodes), schema(schema), *verifiers)

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
     * Set root path.
     */
    fun root(root: String) {
        rootPath = root
    }

    /**
     * Add to root path.
     */
    fun appendRoot(path: String) {
        rootPath += path
    }

    /**
     * Detach from root path.
     */
    fun detachRoot(path: String) {
        rootPath = rootPath.removeSuffix(path)
    }

    /**
     * Returns Rest Assured response.
     */
    fun then() = validatableResponse

    /**
     * For getting response code of last then.
     *
     * @return response code.
     */
    fun getStatusCode() = response.statusCode

    /**
     * For getting body of last response as string.
     *
     * @return body as string.
     */
    fun getBody() = response.body.asString()!!

    /**
     * Deserialize response body as your model class.
     *
     * @param cls that of your module.
     * @param T is response model.
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
            (model as Model).set(logger, response)

        return model
    }

    /**
     * Deserialize response body as your model class.
     *
     * @param cls that of your module.
     * @param T is response model.
     * @return deserialize body as your model class.
     */
    @JvmName("as")
    fun <T : Any> to(cls: KClass<T>): T {
        val model = if (response.body.asString().trim().isEmpty() ||
                (!response.contentType.contains("JSON", true) &&
                        !response.contentType.contains("XML", true)))
            cls.java.newInstance()!!
        else
            response.`as`(cls.java)!!

        if (Model::class.java.isAssignableFrom(cls.java))
            (model as Model).set(logger, response)

        return model
    }

    /**
     * Deserialize response body as your model class.
     *
     * @param cls that of your module.
     * @param objectMapper for response body deserializing.
     * @param T is response model.
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
            (model as Model).set(logger, response)

        return model
    }

    /**
     * Deserialize response body as your model class.
     *
     * @param cls that of your module.
     * @param objectMapper for response body deserializing.
     * @param T is response model.
     * @return deserialize body as your model class.
     */
    @JvmName("as")
    fun <T : Any> to(cls: KClass<T>, objectMapper: ObjectMapper): T {
        val model = if (response.body.asString().trim().isEmpty() ||
                (!response.contentType.contains("JSON", true) &&
                        !response.contentType.contains("XML", true)))
            cls.java.newInstance()!!
        else
            response.`as`(cls.java, objectMapper)!!

        if (Model::class.java.isAssignableFrom(cls.java))
            (model as Model).set(logger, response)

        return model
    }

    /**
     * Deserialize response body as your model class.
     *
     * @param cls that of your module.
     * @param objectMapperType for response body deserializing.
     * @param T is response model.
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
            (model as Model).set(logger, response)

        return model
    }

    /**
     * Deserialize response body as your model class.
     *
     * @param cls that of your module.
     * @param objectMapperType for response body deserializing.
     * @param T is response model.
     * @return deserialize body as your model class.
     */
    @JvmName("as")
    fun <T : Any> to(cls: KClass<T>, objectMapperType: ObjectMapperType): T {
        val model = if (response.body.asString().trim().isEmpty() ||
                (!response.contentType.contains("JSON", true) &&
                        !response.contentType.contains("XML", true)))
            cls.java.newInstance()!!
        else
            response.`as`(cls.java, objectMapperType)!!

        if (Model::class.java.isAssignableFrom(cls.java))
            (model as Model).set(logger, response)

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
     * Get response header by name.
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
     * Returns cookie.
     *
     * @return cookie.
     */
    fun cookie(name: String) = response.cookie(name)!!

    /**
     * Returns list of cookies.
     *
     * @return list.
     */
    fun cookies(): Map<String, String> = response.cookies!!

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

    /**
     * This method can be used for setting of all required variables of default response.
     */
    protected fun set(logger: Logger, response: io.restassured.response.Response) {
        this.response = response
        this.logger = logger
    }


    private fun printFailuresIfExist(vararg verifiers: Verifier): Logger {
        verifiers.find {
            try {
                it.verify(this)
            } catch (e: Throwable) {
                errors.add(AssertionError(e))

                if (!softAssertionsEnabled)
                    return@find true
            }

            false
        }

        if (errors.size > 0) {
            if (!logAllEnabled && logIfFailedEnabled)
                log()

            throw AssertionError("\n\n============= F A I L U R E S =============\n" + errors.map {
                it.message!!
                        .replace(it.javaClass.name, "")
                        .replace(Regex(": \\d+ expectation failed."), "")
            }.joinToString("\n-------------------------------------------\n") + "\n===========================================")

        }

        return logger
    }
}