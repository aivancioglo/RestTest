package com.github.aivancioglo.resttest.matchers

import org.hamcrest.Description
import org.hamcrest.Factory
import org.hamcrest.TypeSafeMatcher
import java.util.regex.Pattern

class RegexMatcher(private val regex: String) : TypeSafeMatcher<String>() {
    override fun describeTo(description: Description) {
        description.appendText("Regular expression = \"$regex\"")
    }

    override fun matchesSafely(text: String) = Pattern.compile(regex).matcher(text).find()

    companion object {
        @Factory
        @JvmStatic
        fun regex(regex: Any) = RegexMatcher(regex as String)
    }
}
