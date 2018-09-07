package com.github.aivancioglo.resttest.http

import org.hamcrest.Description
import org.hamcrest.Factory
import org.hamcrest.TypeSafeMatcher
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.HashSet


object RestTestMatcher {
    @Factory
    @JvmStatic
    fun isSorted(ignoreCase: Boolean = false) = IsSortedMatcher(ignoreCase)

    @Factory
    @JvmStatic
    fun isSorted(ignoreCase: Boolean = false, comparator: Comparator<*>) = IsSortedMatcher(ignoreCase, comparator)

    @Factory
    @JvmStatic
    fun regex(regex: Any) = RegexMatcher(regex as String)

    @Factory
    @JvmStatic
    fun containsIgnoringCase(subString: String) =
            CaseInsensitiveSubstringMatcher(subString)

    @Factory
    @JvmStatic
    fun isDistinct() = DistinctMatcher()
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
        sortedList = list.sortedWith(kotlin.Comparator { v1, v2 ->
            when {
                v1 is Byte && v2 is Byte -> v1.compareTo(v2)
                v1 is Short && v2 is Short -> v1.compareTo(v2)
                v1 is Long && v2 is Long -> v1.compareTo(v2)
                v1 is Int && v2 is Int -> v1.compareTo(v2)
                v1 is Float && v2 is Float -> v1.compareTo(v2)
                v1 is Double && v2 is Double -> v1.compareTo(v2)
                v1 is Boolean && v2 is Boolean -> v1.compareTo(v2)
                else -> v1.toString().compareTo(v2.toString())
            }
        })

        val listWithIndexes = list.toList()

        for (i in 0 until sortedList.size)
            if (sortedList[i] != listWithIndexes[i])
                message = "Value \"${listWithIndexes[i]}\" should be before \"${sortedList[i]}\"."

        return list == sortedList
    }

}

class DistinctMatcher : TypeSafeMatcher<Iterable<*>>() {
    private val set = HashSet<Any?>()
    private var item: Any? = null

    override fun describeTo(description: Description) {
        description.appendText("element found twice: $item")
    }

    override fun matchesSafely(items: Iterable<*>?): Boolean {
        item = items!!.find { !set.add(it) }
        return item == null
    }
}