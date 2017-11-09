package com.github.aivancioglo.resttest.setters;

import com.github.aivancioglo.resttest.http.HTTPRequest;

public class QueryParam<T extends HTTPRequest> implements Setter<T> {
    private String key;
    private Object value;

    public QueryParam(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Update request.
     * @param request actual request.
     */
    @Override
    public void update(T request) {
        request.addQueryParam(key, value);
    }
}
