package com.github.aivancioglo.resttest.setters;

import com.github.aivancioglo.resttest.http.HTTPRequest;

public class ContentType<T extends HTTPRequest> implements Setter<T> {
    private io.restassured.http.ContentType eType;
    private String sType;

    public ContentType(io.restassured.http.ContentType type) {
        eType = type;
    }

    public ContentType(String type) {
        sType = type;
    }

    /**
     * Update request.
     * @param request actual request.
     */
    @Override
    public void update(T request) {
        if (eType == null)
            request.setContentType(sType);

        else
            request.setContentType(eType);
    }
}
