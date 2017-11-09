package com.github.aivancioglo.resttest.setters;

import com.github.aivancioglo.resttest.http.HTTPRequest;

public class Body<T extends HTTPRequest> implements Setter {
    private Object body;

    public Body(Object body) {
        this.body = body;
    }

    /**
     * Update request.
     *
     * @param request actual request.
     */
    @Override
    public void update(HTTPRequest request) {
        request.setBody(body);
    }
}
