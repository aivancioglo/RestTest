package com.github.aivancioglo.resttest.http

enum class OAuthSignature(var value: io.restassured.authentication.OAuthSignature) {
    HEADER(io.restassured.authentication.OAuthSignature.HEADER),
    QUERY_STRING(io.restassured.authentication.OAuthSignature.QUERY_STRING)
}