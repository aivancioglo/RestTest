package com.github.aivancioglo.resttest.setters;

import com.github.aivancioglo.resttest.http.HTTPRequest;

public class OAuth1TokenSecret<T extends HTTPRequest> implements Setter<T> {
    private String tokenSecret;

    public OAuth1TokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    /**
     * Update request.
     * @param request actual request.
     */
    @Override
    public void update(T request) {
        request.setOAuth1TokenSecret(tokenSecret);
    }
}
