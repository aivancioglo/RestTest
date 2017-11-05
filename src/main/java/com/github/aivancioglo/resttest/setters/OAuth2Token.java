package com.github.aivancioglo.resttest.setters;

import com.github.aivancioglo.resttest.http.HTTPRequest;

public class OAuth2Token<T extends HTTPRequest> implements Setter<T> {
    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    /**
     * Update request.
     * @param request actual request.
     */
    @Override
    public void update(T request) {
        request.setOAuth2Token(token);
    }
}
