package com.github.aivancioglo.resttest.http;

import java.net.MalformedURLException;
import java.net.URL;

import static io.restassured.http.Method.*;

/**
 * Simple endpoint for simple request.
 */
public class Endpoint extends HTTPRequest<Endpoint> {
    private static final String PROTOCOL = "com/github/aivancioglo/resttest/http";

    public Endpoint() {
        setProtocol(PROTOCOL);
    }

    /**
     * Method for GET request.
     *
     * @return HTTPResponse instance.
     */
    public HTTPResponse get() {
        return send(GET);
    }

    /**
     * Method for POST request.
     *
     * @return HTTPResponse instance.
     */
    public HTTPResponse post() {
        return send(POST);
    }

    /**
     * Method for PATCH request.
     *
     * @return HTTPResponse instance.
     */
    public HTTPResponse patch() {
        return send(PATCH);
    }

    /**
     * Method for DELETE request.
     *
     * @return HTTPResponse instance.
     */
    public HTTPResponse delete() {
        return send(DELETE);
    }

    /**
     * Method for PUT request.
     *
     * @return HTTPResponse instance.
     */
    public HTTPResponse put() {
        return send(PUT);
    }

    /**
     * Method for HEAD request.
     *
     * @return HTTPResponse instance.
     */
    public HTTPResponse head() {
        return send(HEAD);
    }

    /**
     * Method for TRACE request.
     *
     * @return HTTPResponse instance.
     */
    public HTTPResponse trace() {
        return send(TRACE);
    }

    /**
     * Method for OPTIONS request.
     *
     * @return HTTPResponse instance.
     */
    public HTTPResponse options() {
        return send(OPTIONS);
    }

    /**
     * Set URL for request.
     *
     * @param endpoint String.
     * @return instance of this class.
     */
    public Endpoint setURL(String endpoint) {
        URL url;

        try {
            url = new URL(endpoint);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        setProtocol(url.getProtocol());
        setHost(url.getHost());
        setPath(url.getPath() + "?" + url.getQuery());

        return this;
    }
}