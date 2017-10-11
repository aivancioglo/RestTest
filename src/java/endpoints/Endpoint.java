package endpoints;

import http.HTTPRequest;
import http.HTTPResponse;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import static io.restassured.http.Method.*;

public class Endpoint extends HTTPRequest<Endpoint> {
    private static final String PROTOCOL = "http";

    public Endpoint() {
        setProtocol(PROTOCOL);
    }

    public HTTPResponse get() throws Exception {
        return send(GET);
    }

    public HTTPResponse post() throws Exception {
        return send(POST);
    }

    public HTTPResponse patch() throws Exception {
        return send(PATCH);
    }

    public HTTPResponse delete() throws Exception {
        return send(DELETE);
    }

    public HTTPResponse put() throws Exception {
        return send(PUT);
    }

    public HTTPResponse head() throws Exception {
        return send(HEAD);
    }

    public HTTPResponse trace() throws Exception {
        return send(TRACE);
    }

    public HTTPResponse options() throws Exception {
        return send(OPTIONS);
    }

    public Endpoint setURL(String endpoint) throws MalformedURLException, UnsupportedEncodingException {
        URL url = new URL(endpoint);
        setProtocol(url.getProtocol());
        setHost(url.getHost());
        setPath(url.getPath() + "?" + url.getQuery());

        return this;
    }
}