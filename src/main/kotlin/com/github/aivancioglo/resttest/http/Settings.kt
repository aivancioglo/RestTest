package com.github.aivancioglo.resttest.http

import com.github.aivancioglo.resttest.http.Property.getProperty
import io.restassured.RestAssured.config
import io.restassured.RestAssured.useRelaxedHTTPSValidation
import io.restassured.config.DecoderConfig.decoderConfig
import io.restassured.config.EncoderConfig.encoderConfig
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

        val decoderCharset = getProperty("decoder_charset", "")
        val encoderCharset = getProperty("encoder_charset", "")

        init {
            config = config.httpClient(httpClientConfig()
                    .setParam("http.connection.timeout", getProperty("connection_timeout", "20000").toInt())
                    .setParam("http.socket.timeout", getProperty("socket_timeout", "60000").toInt()))

            if (decoderCharset.trim() != "")
                config = config.decoderConfig(decoderConfig().defaultContentCharset(getProperty("decoder_charset")))

            config = config.encoderConfig(
                    if (encoderCharset.trim() == "")
                        encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)
                    else
                        encoderConfig().defaultContentCharset(encoderCharset))

            if (getProperty("use_relaxed_https_validation", "true").toBoolean())
                useRelaxedHTTPSValidation()
        }
    }
}
