package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.http.Property.getProperty
import io.restassured.RestAssured.config
import io.restassured.RestAssured.useRelaxedHTTPSValidation
import io.restassured.config.HttpClientConfig.httpClientConfig

abstract class Settings {
    companion object {
        var logIfFailedEnabled = getProperty("log_if_failed", "true").toBoolean()
            private set

        var logAllEnabled = getProperty("log_all", "false").toBoolean()
            private set

        var softAssertionsEnabled = getProperty("soft_assertions", "false").toBoolean()
            private set

        var contentType = getProperty("content_type", "application/json")
            private set

        init {
            config.httpClient(httpClientConfig()
                    .setParam("http.connection.timeout", getProperty("connection_timeout", "20000").toInt())
                    .setParam("http.socket.timeout", getProperty("socket_timeout", "60000").toInt()))

            if (getProperty("decoder_charset", "").trim() != "")
                config.decoderConfig.defaultContentCharset(getProperty("decoder_charset"))

            if (getProperty("encoder_charset", "").trim() == "")
                config.encoderConfig.appendDefaultContentCharsetToContentTypeIfUndefined(false)
            else
                config.encoderConfig.defaultContentCharset(getProperty("encoder_charset"))

            if (getProperty("use_relaxed_https_validation", "true").toBoolean())
                useRelaxedHTTPSValidation()
        }
    }
}
