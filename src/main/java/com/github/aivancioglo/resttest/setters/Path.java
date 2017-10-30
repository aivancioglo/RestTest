package com.github.aivancioglo.resttest.setters;

import com.github.aivancioglo.resttest.http.HTTPRequest;

public class Path<T extends HTTPRequest> implements Setter<T> {
    private String path;

    public Path(String path) {
        this.path = path;
    }

    /**
     * Update request.
     * @param request actual request.
     */
    @Override
    public void update(T request) {
        request.setToken(path);
    }
}
