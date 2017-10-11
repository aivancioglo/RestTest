package http;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static http.HTTPResponse.resultsOf;

public class HTTPRequest<T> {
    protected RequestSpecification send;
    protected OAuth1 oAuth;
    protected String protocol;
    protected String host;

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

    public T addHeader(String name, String value) {
        send.header(name, value);
        return (T) this;
    }

    public T setConsumerKey(String consumerKey) {
        if (oAuth == null)
            oAuth = new OAuth1();

        oAuth.setConsumerKey(consumerKey);
        return (T) this;
    }

    public T setConsumerSecret(String consumerSecret) {
        if (oAuth == null)
            oAuth = new OAuth1();

        oAuth.setConsumerSecret(consumerSecret);
        return (T) this;
    }

    public T setToken(String token) {
        if (oAuth == null)
            oAuth = new OAuth1();

        oAuth.setToken(token);
        return (T) this;
    }

    public T setTokenSecret(String tokenSecret) {
        if (oAuth == null)
            oAuth = new OAuth1();

        oAuth.setTokenSecret(tokenSecret);
        return (T) this;
    }

    public T setProtocol(String protocol) {
        this.protocol = protocol + "://";
        return (T) this;
    }

    public T addParam(String key, String value) {
        send.param(key, value);
        return (T) this;
    }

    public T addQueryParam(String key, String value) {
        send.queryParam(key, value);
        return (T) this;
    }

    public T setBody(Object body) {
        send.body(body);
        return (T) this;
    }

    public T setBody(String body) {
        send.body(body);
        return (T) this;
    }

    public T setContentType(ContentType type) {
        send.contentType(type);
        return (T) this;
    }

    public T setContentType(String type) {
        send.contentType(type);
        return (T) this;
    }

    public T setHost(String host) {
        this.host = host;
        return (T) this;
    }

    public T setPath(String path) throws UnsupportedEncodingException {
        Matcher m = Pattern.compile("[?&]([^&=]+)=([^&=]+)").matcher(path);
        while (m.find()) addQueryParam(m.group(1), URLDecoder.decode(m.group(2), "UTF-8"));

        send.basePath(path);
        return (T) this;
    }

    protected HTTPResponse send(Method method) throws Exception {
        if (oAuth != null)
            send.auth().oauth(oAuth.consumerKey(), oAuth.consumerSecret(), oAuth.token(), oAuth.tokenSecret());

        send.baseUri(protocol + host);

        return resultsOf(send.request(method));
    }

    protected HTTPResponse send(Method method, String path) throws Exception {
        if (oAuth != null)
            send.auth().oauth(oAuth.consumerKey(), oAuth.consumerSecret(), oAuth.token(), oAuth.tokenSecret());

        send.baseUri(protocol + host);

        return resultsOf(send.request(method, path));
    }
}