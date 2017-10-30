package com.github.aivancioglo.resttest.setters;

import com.github.aivancioglo.resttest.http.HTTPRequest;

public interface Setter<T extends HTTPRequest> {
    /**
     * Update request.
     * @param request actual request.
     */
    void update(T request);
}
