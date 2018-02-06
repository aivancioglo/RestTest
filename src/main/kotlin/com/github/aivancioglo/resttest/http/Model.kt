package com.github.aivancioglo.resttest.http

import io.restassured.mapper.ObjectMapper
import io.restassured.mapper.ObjectMapperType
import io.restassured.module.jsv.JsonSchemaValidator
import io.restassured.response.Response
import kotlin.reflect.KClass

abstract class Model {
    internal lateinit var responseSpecification: Response
    lateinit var response: HTTPResponse
        internal set

    /**
     * Making responseSpecification validation.
     *
     * @param verifiers for responseSpecification validation.
     */
    fun assertThat(vararg verifiers: (Response) -> Unit) {
        verifiers.forEach { it(responseSpecification) }
    }

    /**
     * Making responseSpecification validation.
     *
     * @param code of responseSpecification.
     * @param verifiers for responseSpecification validation.
     */
    fun assertThat(code: Int, vararg verifiers: (Response) -> Unit) {
        responseSpecification.then().statusCode(code)
        verifiers.forEach { it(responseSpecification) }
    }

    /**
     * Making responseSpecification validation.
     *
     * @param statusCode of responseSpecification.
     * @param verifiers for responseSpecification validation.
     */
    fun assertThat(statusCode: StatusCode, vararg verifiers: (Response) -> Unit) {
        responseSpecification.then().statusCode(statusCode.code)
        verifiers.forEach { it(responseSpecification) }
    }

    /**
     * Making responseSpecification validation.
     *
     * @param code of responseSpecification.
     * @param jsonSchema for responseSpecification validation.
     * @param verifiers for responseSpecification validation.
     */
    fun assertThat(code: Int, jsonSchema: String, vararg verifiers: (Response) -> Unit) {
        responseSpecification.then().statusCode(code).body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema))
        verifiers.forEach { it(responseSpecification) }
    }

    /**
     * Making responseSpecification validation.
     *
     * @param statusCode of responseSpecification.
     * @param jsonSchema for responseSpecification validation.
     * @param verifiers for responseSpecification validation.
     */
    fun assertThat(statusCode: StatusCode, jsonSchema: String, vararg verifiers: (Response) -> Unit) {
        responseSpecification.then()
                .statusCode(statusCode.code).body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema))
        verifiers.forEach { it(responseSpecification) }
    }

    /**
     * Deserialize responseSpecification body as your model class.
     *
     * @param cls that of your module.
     * @param T is responseSpecification model.
     * @return deserialize body as your model class.
     */
    fun <T: Any> to(cls: KClass<T>): T {
        val model = if (responseSpecification.body.asString().trim().isEmpty() ||
                (!responseSpecification.contentType.contains("JSON", true) &&
                        !responseSpecification.contentType.contains("XML", true)))
            cls.java.newInstance()!!
        else
            responseSpecification.`as`(cls.java)!!

        if (Model::class.java.isAssignableFrom(cls.java)) {
            (model as Model).responseSpecification = responseSpecification
            (model as Model).response = response
        }

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
    fun <T: Any> to(cls: KClass<T>, objectMapper: ObjectMapper): T {
        val model = if (responseSpecification.body.asString().trim().isEmpty() ||
                (!responseSpecification.contentType.contains("JSON", true) &&
                        !responseSpecification.contentType.contains("XML", true)))
            cls.java.newInstance()!!
        else
            responseSpecification.`as`(cls.java, objectMapper)!!

        if (Model::class.java.isAssignableFrom(cls.java)) {
            (model as Model).responseSpecification = responseSpecification
            (model as Model).response = response
        }

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
    fun <T: Any> to(cls: KClass<T>, objectMapperType: ObjectMapperType): T {
        val model = if (responseSpecification.body.asString().trim().isEmpty() ||
                (!responseSpecification.contentType.contains("JSON", true) &&
                        !responseSpecification.contentType.contains("XML", true)))
            cls.java.newInstance()!!
        else
            responseSpecification.`as`(cls.java, objectMapperType)!!

        if (Model::class.java.isAssignableFrom(cls.java)) {
            (model as Model).responseSpecification = responseSpecification
            (model as Model).response = response
        }

        return model
    }
}
