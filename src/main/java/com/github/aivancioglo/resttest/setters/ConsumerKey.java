package com.github.aivancioglo.resttest.setters;

import com.github.aivancioglo.resttest.http.HTTPRequest;

public class ConsumerKey<T extends HTTPRequest> implements Setter<T> {
    private String consumerKey;

    public ConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    /**
     * Update request.
     * @param request actual request.
     */
    @Override
    public void update(T request) {
        request.setConsumerKey(consumerKey);
    }
}
