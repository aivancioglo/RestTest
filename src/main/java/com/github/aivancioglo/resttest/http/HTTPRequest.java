package com.github.aivancioglo.resttest.http;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is using for simple HTTP/HTTPS requests.
 */
public class HTTPRequest<T> {
    protected RequestSpecification send;
    protected OAuth1 oAuth1;
    protected OAuth2 oAuth2;
    protected String protocol;
    protected String host;

    /**
     * Setting default Rest Assured configurations.
     */
    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.config = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig()
                .setParam("http.connection.timeout", 3000)
                .setParam("http.socket.timeout", 3000));
    }

    public HTTPRequest() {
        send = RestAssured.given()
                .contentType(ContentType.JSON);
    }

    /**
     * Add new request header.
     *
     * @param name  header name.
     * @param value header value.
     * @return this class instance.
     */
    public T addHeader(String name, String value) {
        send.header(name, value);
        return (T) this;
    }

    /**
     * Set consumer key for request.
     *
     * @param consumerKey OAuth1 consumer key.
     * @return this class instance.
     */
    public T setOAuth1ConsumerKey(String consumerKey) {
        if (oAuth1 == null)
            oAuth1 = new OAuth1();

        oAuth1.setConsumerKey(consumerKey);
        return (T) this;
    }

    /**
     * Set consumer secret for request.
     *
     * @param consumerSecret OAuth1 consumer secret.
     * @return this class instance.
     */
    public T setOAuth1ConsumerSecret(String consumerSecret) {
        if (oAuth1 == null)
            oAuth1 = new OAuth1();

        oAuth1.setConsumerSecret(consumerSecret);
        return (T) this;
    }

    /**
     * Set token for request.
     *
     * @param token OAuth1 token.
     * @return this class instance.
     */
    public T setOAuth2Token(String token) {
        if (oAuth2 == null)
            oAuth2 = new OAuth2();

        oAuth2.setToken(token);
        return (T) this;
    }

    /**
     * Set token secret for request.
     *
     * @param tokenSecret OAuth1 token secret.
     * @return this class instance.
     */
    public T setOAuth1TokenSecret(String tokenSecret) {
        if (oAuth1 == null)
            oAuth1 = new OAuth1();

        oAuth1.setTokenSecret(tokenSecret);
        return (T) this;
    }

    /**
     * Set token for request.
     *
     * @param token OAuth2 token.
     * @return this class instance.
     */
    public T setOAuth1Token(String token) {
        if (oAuth1 == null)
            oAuth1 = new OAuth1();

        oAuth1.setToken(token);
        return (T) this;
    }

    /**
     * Set protocol for request.
     *
     * @param protocol protocol of endpoint.
     * @return this class instance.
     */
    public T setProtocol(String protocol) {
        this.protocol = protocol + "://";
        return (T) this;
    }

    /**
     * Add parameter for request.
     *
     * @param key   parameter key.
     * @param value parameter value.
     * @return this class instance.
     */
    public T addParam(String key, String value) {
        send.param(key, value);
        return (T) this;
    }

    /**
     * Add parameter as collection for request.
     *
     * @param name       collection name.
     * @param collection collection of values.
     * @return this class instance.
     */
    public T addParam(String name, Collection<?> collection) {
        send.param(name, collection);
        return (T) this;
    }

    /**
     * Add query parameter for request.
     *
     * @param key   parameter key.
     * @param value parameter value.
     * @return this class instance.
     */
    public T addQueryParam(String key, String value) {
        send.queryParam(key, value);
        return (T) this;
    }

    /**
     * Add form-param for request.
     *
     * @param key   parameter key.
     * @param value parameter value.
     * @return this class instance.
     */
    public T addFormParam(String key, String value) {
        send.formParam(key, value);
        return (T) this;
    }

    /**
     * Add form-param as collection for request.
     *
     * @param name       collection name.
     * @param collection collection of values.
     * @return this class instance.
     */
    public T addFormParam(String name, Collection<?> collection) {
        send.formParam(name, collection);
        return (T) this;
    }

    /**
     * Add body as Object for request. Automatically serialization.
     *
     * @param body class Object.
     * @return this class instance.
     */
    public T setBody(Object body) {
        send.body(body);
        return (T) this;
    }

    /**
     * Add body as string.
     *
     * @param body String.
     * @return this class instance.
     */
    public T setBody(String body) {
        send.body(body);
        return (T) this;
    }

    /**
     * Set content type.
     *
     * @param type Rest assured ContentType enum.
     * @return this class instance.
     */
    public T setContentType(ContentType type) {
        send.contentType(type);
        return (T) this;
    }

    /**
     * Set content type.
     *
     * @param type String.
     * @return this class instance.
     */
    public T setContentType(String type) {
        send.contentType(type);
        return (T) this;
    }

    /**
     * Set host for request.
     *
     * @param host String.
     * @return this class instance.
     */
    public T setHost(String host) {
        this.host = host;
        return (T) this;
    }

    /**
     * Set path for request.
     *
     * @param path String.
     * @return this class instance.
     */
    public T setPath(String path) {
        Matcher m = Pattern.compile("[?&]([^&=]+)=([^&=]+)").matcher(path);

        while (m.find()) {
            try {
                addQueryParam(m.group(1), URLDecoder.decode(m.group(2), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        send.basePath(path);
        return (T) this;
    }

    /**
     * Setting up path param.
     *
     * @param key   of param.
     * @param value of param.
     * @return this class instance.
     */
    public T setPathParam(String key, Object value) {
        send.pathParam(key, value);
        return (T) this;
    }

    /**
     * Logging request.
     *
     * @return this class instance.
     */
    public T logRequest() {
        send.request().log().all();
        return (T) this;
    }

    /**
     * Returns instance of HTTPResponse class.
     *
     * @param method Rest Assured Method enum.
     * @return HTTPResponse instance.
     */
    protected HTTPResponse send(Method method) {
        if (oAuth1 != null && oAuth2 != null)
            throw new RuntimeException("You can not use OAuth 1.0 and OAuth 2 in the same request!");

        if (oAuth1 != null)
            send.auth().oauth(oAuth1.consumerKey(), oAuth1.consumerSecret(), oAuth1.token(), oAuth1.tokenSecret());

        if (oAuth2 != null)
            send.auth().oauth2(oAuth2.token());

        send.baseUri(protocol + host);

        return new HTTPResponse(send.request(method));
    }

    /**
     * Returns instance of HTTPResponse class.
     *
     * @param method Rest Assured Method enum.
     * @param path   Rest Assured path param.
     * @return HTTPResponse instance.
     */
    protected HTTPResponse send(Method method, String path) {
        if (oAuth1 != null)
            send.auth().oauth(oAuth1.consumerKey(), oAuth1.consumerSecret(), oAuth1.token(), oAuth1.tokenSecret());

        send.baseUri(protocol + host);

        return new HTTPResponse(send.request(method, path));
    }
}
