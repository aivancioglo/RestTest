package com.github.aivancioglo.resttest.setters

import com.github.aivancioglo.resttest.http.HTTPRequest

interface Setter<in T : HTTPRequest<*>> {
    fun update(request: T)

    companion object {
        fun <K : HTTPRequest<*>> set(request: K, vararg setters: Setter<K>) {
            for (setter in setters)
                setter.update(request)
        }
    }
}