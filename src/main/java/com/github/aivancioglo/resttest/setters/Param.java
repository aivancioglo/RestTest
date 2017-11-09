package com.github.aivancioglo.resttest.setters;

import com.github.aivancioglo.resttest.http.HTTPRequest;

public class Param<T extends HTTPRequest> implements Setter<T> {
    private String key;
    private Object value;

    public Param(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Update request.
     * @param request actual request.
     */
    @Override
    public void update(T request) {
        request.addParam(key, value);
    }
}
