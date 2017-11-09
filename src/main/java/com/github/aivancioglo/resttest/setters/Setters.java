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
    public static Param param(String key, Object value) {
        return new Param(key, value);
    }

    /**
     * Add query param to request.
     * @param key key of param.
     * @param value value of param.
     * @return instance of QueryParam setter.
     */
    public static QueryParam queryParam(String key, Object value) {
        return new QueryParam(key, value);
    }

    /**
     * Add form param to request.
     * @param key key of param.
     * @param value value of param.
     * @return instance of FormParam setter.
     */
    public static FormParam formParam(String key, Object value) {
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
    public static Body body(Object body) {
        return new Body(body);
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
