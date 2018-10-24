package com.github.aivancioglo.resttest.http

import java.util.*

object Property {
    private var prop = Properties()

    init {
        prop.load(Property::class.java.classLoader.getResourceAsStream("resttest.properties"))
    }

    fun getProperty(name: String): String {
        val property: String? = prop.getProperty(name)

        if (property == null)
            throw Exception("Can not find \"$name\" in \"resttest.properties\" file.")
        else
            return property
    }

    fun getProperty(name: String, default: String) = prop.getProperty(name, default)!!
}
