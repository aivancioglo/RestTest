package com.github.aivancioglo.resttest.setters;

import com.github.aivancioglo.resttest.http.HTTPRequest;

public class Body<T extends HTTPRequest> implements Setter {
    private Object oBody;
    private String sBody;

    public Body(Object oBody) {
        this.oBody = oBody;
    }

    public Body(String sBody) {
        this.sBody = sBody;
    }

    /**
     * Update request.
     * @param request actual request.
     */
    @Override
    public void update(HTTPRequest request) {
        if (oBody == null)
            request.setBody(sBody);
        else
            request.setBody(oBody);
    }
}
