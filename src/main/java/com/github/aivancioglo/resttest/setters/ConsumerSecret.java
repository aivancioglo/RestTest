package com.github.aivancioglo.resttest.setters;

import com.github.aivancioglo.resttest.http.HTTPRequest;

public class ConsumerSecret<T extends HTTPRequest> implements Setter<T> {
    private String consumerSecret;

    public ConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    /**
     * Update request.
     * @param request actual request.
     */
    @Override
    public void update(T request) {
        request.setConsumerSecret(consumerSecret);
    }
}
