package com.github.aivancioglo.resttest.setters;

import com.github.aivancioglo.resttest.http.HTTPRequest;

public interface Setter<T extends HTTPRequest> {
    /**
     * Update request.
     * @param request actual request.
     */
    void update(T request);

    /**
     * Update request params using setters.
     * @param request actual request.
     * @param setters array of setters.
     * @param <K> request class.
     */
    static <K extends HTTPRequest> void set(K request, Setter<K>... setters) {
        for (Setter<K> setter : setters)
            setter.update(request);
    }
}
