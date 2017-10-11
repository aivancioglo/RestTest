package com.github.resttest.http;

/**
 * Thi class is using for requests with OAuth 1.0 security
 */
public class OAuth1 {
    private String consumerKey = "";
    private String consumerSecret = "";
    private String token = "";
    private String tokenSecret = "";

    /**
     * Use this method to get consumer key.
     * @return consumer key.
     */
    public String consumerKey() {
        return consumerKey;
    }

    /**
     * Use this method to get consumer secret.
     * @return consumer secret.
     */
    public String consumerSecret() {
        return consumerSecret;
    }

    /**
     * Use this method to get token.
     * @return token.
     */
    public String token() {
        return token;
    }

    /**
     * Use this method to get token secret.
     * @return token secret.
     */
    public String tokenSecret() {
        return tokenSecret;
    }

    /**
     * Use this method to set consumer key.
     * @param consumerKey is sting value.
     */
    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    /**
     * Use this method to set consumer secret.
     * @param consumerSecret is string value.
     */
    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    /**
     * Use this method to set token.
     * @param token is sting value.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Use this method to set token secret.
     * @param tokenSecret is sting value.
     */
    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }
}