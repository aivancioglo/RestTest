package com.github.aivancioglo.resttest.http

import java.io.IOException
import java.util.*

object Property {
    private var prop = Properties()

    init {
        try {
            prop.load(Property::class.java.classLoader.getResourceAsStream("resttest.properties"))
        } catch (e: IOException) {
        }
    }

    fun getProperty(name: String) = prop.getProperty(name)!!

    fun getProperty(name: String, default: String) = prop.getProperty(name, default)!!
}
