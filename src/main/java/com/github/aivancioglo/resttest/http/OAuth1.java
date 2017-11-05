package com.github.aivancioglo.resttest.http;

import com.github.aivancioglo.resttest.setters.OAuth1ConsumerKey;
import com.github.aivancioglo.resttest.setters.OAuth1ConsumerSecret;
import com.github.aivancioglo.resttest.setters.OAuth1Token;
import com.github.aivancioglo.resttest.setters.OAuth1TokenSecret;

/**
 * This class is using for requests with OAuth 1.0 security
 */
public class OAuth1 {
    private String consumerKey = "";
    private String consumerSecret = "";
    private String token = "";
    private String tokenSecret = "";

    /**
     * Use this method to get consumer key.
     *
     * @return consumer key.
     */
    public String consumerKey() {
        return consumerKey;
    }

    /**
     * Use this method to set/update consumer key.
     *
     * @param consumerKey is sting value.
     */
    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    /**
     * Use this method to get consumer secret.
     *
     * @return consumer secret.
     */
    public String consumerSecret() {
        return consumerSecret;
    }

    /**
     * Use this method to set/update consumer secret.
     *
     * @param consumerSecret is string value.
     */
    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    /**
     * Use this method to get token.
     *
     * @return token.
     */
    public String token() {
        return token;
    }

    /**
     * Use this method to set/update token.
     *
     * @param token is sting value.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Use this method to get token secret.
     *
     * @return token secret.
     */
    public String tokenSecret() {
        return tokenSecret;
    }

    /**
     * Use this method to set/update token secret.
     *
     * @param tokenSecret is sting value.
     */
    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    /**
     * Add consumer key to request.
     * @param consumerKey oAuth1 consumer key.
     * @return instance of OAuth1ConsumerKey setter.
     */
    public static OAuth1ConsumerKey consumerKey(String consumerKey) {
        return new OAuth1ConsumerKey(consumerKey);
    }

    /**
     * Add consumer secret to request.
     * @param consumerSecret oAuth1 consumer secret.
     * @return instance of OAuth1ConsumerSecret setter.
     */
    public static OAuth1ConsumerSecret consumerSecret(String consumerSecret) {
        return new OAuth1ConsumerSecret(consumerSecret);
    }

    /**
     * Add token to request.
     * @param token oAuth1 token.
     * @return instance of OAuth1Token setter.
     */
    public static OAuth1Token token(String token) {
        return new OAuth1Token(token);
    }

    /**
     * Add token secret to request.
     * @param tokenSecret oAuth1 token secret.
     * @return instance of OAuth1TokenSecret setter.
     */
    public static OAuth1TokenSecret tokenSecret(String tokenSecret) {
        return new OAuth1TokenSecret(tokenSecret);
    }
}