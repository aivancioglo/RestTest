package com.github.aivancioglo.resttest.http

enum class ContentType(var value: String, vararg values: String) {
    /**
     * &#42;/&#42;
     */
    ANY("*/*"),

    /**
     * `text/plain`
     */
    TEXT("text/plain"),

    /**
     *
     *  * `application/json`
     *  * `application/javascript`
     *  * `text/javascript`
     *
     */
    JSON("application/json", "application/javascript", "text/javascript", "text/json"),

    /**
     *
     *  * `application/xml`
     *  * `text/xml`
     *  * `application/xhtml+xml`
     *
     */
    XML("application/xml", "text/xml", "application/xhtml+xml"),

    /**
     * `text/html`
     */
    HTML("text/html"),

    /**
     * `application/x-www-form-urlencoded`
     */
    URLENC("application/x-www-form-urlencoded"),

    /**
     * `multipart/form-data`
     */
    MULTIPART("multipart/form-data"),

    /**
     * `application/octet-stream`
     */
    BINARY("application/octet-stream");
}