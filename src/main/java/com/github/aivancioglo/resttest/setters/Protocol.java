package com.github.aivancioglo.resttest.setters;

import com.github.aivancioglo.resttest.http.HTTPRequest;

public class Protocol<T extends HTTPRequest> implements Setter<T> {
    private String protocol;

    public Protocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * Update request.
     * @param request actual request.
     */
    @Override
    public void update(T request) {
        request.setProtocol(protocol);
    }
}
