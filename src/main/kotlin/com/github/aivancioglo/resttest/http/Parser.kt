package com.github.aivancioglo.resttest.http

enum class Parser(private val parser: io.restassured.parsing.Parser) {
    XML(io.restassured.parsing.Parser.XML),
    TEXT(io.restassured.parsing.Parser.TEXT),
    JSON(io.restassured.parsing.Parser.JSON),
    HTML(io.restassured.parsing.Parser.HTML);

    fun use() = parser
}