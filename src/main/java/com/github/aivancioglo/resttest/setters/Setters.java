package com.github.aivancioglo.resttest.setters;

import io.restassured.http.ContentType;

public abstract class Setters {

    /**
     * Set content type for request.
     * @param type content type.
     * @return instance of ContentType setter.
     */
    public static com.github.aivancioglo.resttest.setters.ContentType contentType(ContentType type) {
        return new com.github.aivancioglo.resttest.setters.ContentType(type);
    }

    /**
     * Set content type for request.
     * @param type content type.
     * @return instance of ContentType setter.
     */
    public static com.github.aivancioglo.resttest.setters.ContentType contentType(String type) {
        return new com.github.aivancioglo.resttest.setters.ContentType(type);
    }

    /**
     * Add param to request.
     * @param key key of param.
     * @param value value of param.
     * @return instance of Param setter.
     */
    public static Param param(String key, String value) {
        return new Param(key, value);
    }

    /**
     * Add query param to request.
     * @param key key of param.
     * @param value value of param.
     * @return instance of QueryParam setter.
     */
    public static QueryParam queryParam(String key, String value) {
        return new QueryParam(key, value);
    }

    /**
     * Add form param to request.
     * @param key key of param.
     * @param value value of param.
     * @return instance of FormParam setter.
     */
    public static FormParam formParam(String key, String value) {
        return new FormParam(key, value);
    }

    /**
     * Add header to request.
     * @param name header name.
     * @param value header value.
     * @return instance of Header setter.
     */
    public static Header header(String name, String value) {
        return new Header(name, value);
    }

    /**
     * Add body to request.
     * @param body request body.
     * @return instance of Body setter.
     */
    public static Body body(String body) {
        return new Body(body);
    }

    /**
     * Add body to request.
     * @param body request body.
     * @return instance of Body setter.
     */
    public static Body body(Object body) {
        return new Body(body);
    }

    /**
     * Add consumer key to request.
     * @param consumerKey oAuth 1.0 consumer key.
     * @return instance of ConsumerKey setter.
     */
    public static ConsumerKey consumerKey(String consumerKey) {
        return new ConsumerKey(consumerKey);
    }

    /**
     * Add consumer secret to request.
     * @param consumerSecret oAuth 1.0 consumer secret.
     * @return instance of ConsumerSecret setter.
     */
    public static ConsumerSecret consumerSecret(String consumerSecret) {
        return new ConsumerSecret(consumerSecret);
    }

    /**
     * Add token to request.
     * @param token oAuth 1.0 token.
     * @return instance of Token setter.
     */
    public static Token token(String token) {
        return new Token(token);
    }

    /**
     * Add token secret to request.
     * @param tokenSecret oAuth 1.0 token secret.
     * @return instance of TokenSecret setter.
     */
    public static TokenSecret tokenSecret(String tokenSecret) {
        return new TokenSecret(tokenSecret);
    }

    /**
     * Set protocol to request.
     * @param protocol request protocol.
     * @return instance of Protocol setter.
     */
    public static Protocol protocol(String protocol) {
        return new Protocol(protocol);
    }

    /**
     * Set host to request.
     * @param host request host.
     * @return instance of Host setter.
     */
    public static Host host(String host) {
        return new Host(host);
    }

    /**
     * Set path to request.
     * @param path request path.
     * @return instance of Path setter.
     */
    public static Path path(String path) {
        return new Path(path);
    }
}
