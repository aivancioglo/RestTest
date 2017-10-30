package com.github.aivancioglo.resttest.setters;

import com.github.aivancioglo.resttest.http.HTTPRequest;

public class TokenSecret<T extends HTTPRequest> implements Setter<T> {
    private String tokenSecret;

    public TokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    /**
     * Update request.
     * @param request actual request.
     */
    @Override
    public void update(T request) {
        request.setTokenSecret(tokenSecret);
    }
}
