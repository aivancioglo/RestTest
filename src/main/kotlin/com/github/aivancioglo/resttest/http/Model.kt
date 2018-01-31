package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.verifiers.Verifier
import io.restassured.mapper.ObjectMapper
import io.restassured.mapper.ObjectMapperType
import io.restassured.module.jsv.JsonSchemaValidator
import io.restassured.response.Response

abstract class Model {
    internal lateinit var responseSpecification: Response
    lateinit var response: HTTPResponse
        internal set

    /**
     * Making responseSpecification validation.
     *
     * @param verifiers for responseSpecification validation.
     */
    @SafeVarargs
    fun assertThat(vararg verifiers: Verifier) {
        verifiers.forEach { it.verify(responseSpecification) }
    }

    /**
     * Making responseSpecification validation.
     *
     * @param code of responseSpecification.
     * @param verifiers for responseSpecification validation.
     */
    @SafeVarargs
    fun assertThat(code: Int, vararg verifiers: Verifier) {
        responseSpecification.then().statusCode(code)
        verifiers.forEach { it.verify(responseSpecification) }
    }

    /**
     * Making responseSpecification validation.
     *
     * @param statusCode of responseSpecification.
     * @param verifiers for responseSpecification validation.
     */
    @SafeVarargs
    fun assertThat(statusCode: StatusCode, vararg verifiers: Verifier) {
        responseSpecification.then().statusCode(statusCode.code)
        verifiers.forEach { it.verify(responseSpecification) }
    }

    /**
     * Making responseSpecification validation.
     *
     * @param code of responseSpecification.
     * @param jsonSchema for responseSpecification validation.
     * @param verifiers for responseSpecification validation.
     */
    @SafeVarargs
    fun assertThat(code: Int, jsonSchema: String, vararg verifiers: Verifier) {
        responseSpecification.then().statusCode(code).body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema))
        verifiers.forEach { it.verify(responseSpecification) }
    }

    /**
     * Making responseSpecification validation.
     *
     * @param statusCode of responseSpecification.
     * @param jsonSchema for responseSpecification validation.
     * @param verifiers for responseSpecification validation.
     */
    @SafeVarargs
    fun assertThat(statusCode: StatusCode,
                   jsonSchema: String,
                   vararg verifiers: Verifier) {
        responseSpecification.then()
                .statusCode(statusCode.code).body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchema))
        verifiers.forEach { it.verify(responseSpecification) }
    }

    /**
     * Deserialize responseSpecification body as your model class.
     *
     * @param cls that of your module.
     * @param T is responseSpecification model.
     * @return deserialize body as your model class.
     */
    @JvmName("as")
    fun <T> to(cls: Class<T>): T {
        val model = if (responseSpecification.body.asString().trim().isEmpty())
            cls.newInstance()!!
        else
            responseSpecification.`as`(cls)!!

        if (Model::class.java.isAssignableFrom(cls)) {
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
    @JvmName("as")
    fun <T> to(cls: Class<T>, objectMapper: ObjectMapper): T {
        val model = if (responseSpecification.body.asString().trim().isEmpty())
            cls.newInstance()!!
        else
            responseSpecification.`as`(cls, objectMapper)!!

        if (Model::class.java.isAssignableFrom(cls)) {
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
    @JvmName("as")
    fun <T> to(cls: Class<T>, objectMapperType: ObjectMapperType): T {
        val model = if (responseSpecification.body.asString().trim().isEmpty())
            cls.newInstance()!!
        else
            responseSpecification.`as`(cls, objectMapperType)!!

        if (Model::class.java.isAssignableFrom(cls)) {
            (model as Model).responseSpecification = responseSpecification
            (model as Model).response = response
        }

        return model
    }
}
