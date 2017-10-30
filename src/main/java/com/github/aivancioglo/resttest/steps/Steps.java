package com.github.aivancioglo.resttest.steps;

import com.github.aivancioglo.resttest.http.HTTPRequest;
import com.github.aivancioglo.resttest.setters.Setter;

public interface Steps {
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
