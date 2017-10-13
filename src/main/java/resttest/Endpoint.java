package resttest;

import java.io.UnsupportedEncodingException;
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
     * @return HTTPResponse instance.
     * @throws Exception if incorrect request.
     */
    public HTTPResponse get() throws Exception {
        return send(GET);
    }

    /**
     * Method for POST request.
     * @return HTTPResponse instance.
     * @throws Exception if incorrect request.
     */
    public HTTPResponse post() throws Exception {
        return send(POST);
    }

    /**
     * Method for PATCH request.
     * @return HTTPResponse instance.
     * @throws Exception if incorrect request.
     */
    public HTTPResponse patch() throws Exception {
        return send(PATCH);
    }

    /**
     * Method for DELETE request.
     * @return HTTPResponse instance.
     * @throws Exception if incorrect request.
     */
    public HTTPResponse delete() throws Exception {
        return send(DELETE);
    }

    /**
     * Method for PUT request.
     * @return HTTPResponse instance.
     * @throws Exception if incorrect request.
     */
    public HTTPResponse put() throws Exception {
        return send(PUT);
    }

    /**
     * Method for HEAD request.
     * @return HTTPResponse instance.
     * @throws Exception if incorrect request.
     */
    public HTTPResponse head() throws Exception {
        return send(HEAD);
    }

    /**
     * Method for TRACE request.
     * @return HTTPResponse instance.
     * @throws Exception if incorrect request.
     */
    public HTTPResponse trace() throws Exception {
        return send(TRACE);
    }

    /**
     * Method for OPTIONS request.
     * @return HTTPResponse instance.
     * @throws Exception if incorrect request.
     */
    public HTTPResponse options() throws Exception {
        return send(OPTIONS);
    }

    /**
     * Set URL for request.
     * @param endpoint String.
     * @return instance of this class.
     * @throws MalformedURLException if incorrect URL.
     * @throws UnsupportedEncodingException if encoding invalid.
     */
    public Endpoint setURL(String endpoint) throws MalformedURLException, UnsupportedEncodingException {
        URL url = new URL(endpoint);
        setProtocol(url.getProtocol());
        setHost(url.getHost());
        setPath(url.getPath() + "?" + url.getQuery());

        return this;
    }
}