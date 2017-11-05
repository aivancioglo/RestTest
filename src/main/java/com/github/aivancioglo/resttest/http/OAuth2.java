package com.github.aivancioglo.resttest.http;

import com.github.aivancioglo.resttest.setters.OAuth1Token;

public class OAuth2 {
    private String token;

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
     * Add token to request.
     * @param token oAuth2 token.
     * @return instance of OAuth1Token setter.
     */
    public static OAuth1Token token(String token) {
        return new OAuth1Token(token);
    }
}
