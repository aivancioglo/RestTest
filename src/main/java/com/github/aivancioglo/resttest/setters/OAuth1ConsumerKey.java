package com.github.aivancioglo.resttest.setters;

import com.github.aivancioglo.resttest.http.HTTPRequest;

public class OAuth1ConsumerKey<T extends HTTPRequest> implements Setter<T> {
    private String consumerKey;

    public OAuth1ConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    /**
     * Update request.
     * @param request actual request.
     */
    @Override
    public void update(T request) {
        request.setOAuth1ConsumerKey(consumerKey);
    }
}
