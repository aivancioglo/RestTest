package com.github.aivancioglo.resttest.setters;

import com.github.aivancioglo.resttest.http.HTTPRequest;

public class FormParam<T extends HTTPRequest> implements Setter<T> {
    private String key;
    private Object value;

    public FormParam(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Update request.
     * @param request actual request.
     */
    @Override
    public void update(T request) {
        request.addFormParam(key, value);
    }
}
