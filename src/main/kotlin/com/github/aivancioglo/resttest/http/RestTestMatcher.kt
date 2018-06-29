package com.github.aivancioglo.resttest.http

import org.hamcrest.Description
import org.hamcrest.Factory
import org.hamcrest.TypeSafeMatcher
import java.util.*
import java.util.regex.Pattern

object RestTestMatcher {
    @Factory
    @JvmStatic
    @JvmOverloads
    fun isSorted(ignoreCase: Boolean = false) = IsSortedMatcher(ignoreCase)

    @Factory
    @JvmStatic
    fun regex(regex: Any) = RegexMatcher(regex as String)

    @Factory
    @JvmStatic
    fun containsIgnoringCase(subString: String) =
            CaseInsensitiveSubstringMatcher(subString)
}

class CaseInsensitiveSubstringMatcher(private val subString: String) : TypeSafeMatcher<String>() {

    override fun describeTo(description: Description) {
        description.appendText("containing substring \"${this.subString}\"")
    }

    override fun matchesSafely(actualString: String) =
            actualString.toLowerCase().contains(this.subString.toLowerCase())
}

class RegexMatcher(private val regex: String) : TypeSafeMatcher<String>() {

    override fun describeTo(description: Description) {
        description.appendText("Regular expression = \"$regex\"")
    }

    override fun matchesSafely(text: String) = Pattern.compile(regex).matcher(text).find()
}

class IsSortedMatcher(private var ignoreCase: Boolean = false) : TypeSafeMatcher<Iterable<*>>() {
    private lateinit var sortedList: List<*>

    lateinit var comparator: Comparator<*>
    private var message = ""

    constructor(ignoreCase: Boolean = false, comparator: Comparator<*>) : this(ignoreCase) {
        this.comparator = comparator
    }

    override fun describeTo(description: Description) {
        description.appendText(message)
    }

    override fun matchesSafely(list: Iterable<*>): Boolean {
        sortedList = list.sortedBy { it.toString() }
        val listWithIndexes = list.toList()

        for (i in 0 until sortedList.size)
            if (sortedList[i] != listWithIndexes[i])
                message = "Value \"${listWithIndexes[i]}\" should be before \"${sortedList[i]}\"."

        return list == sortedList
    }

}
