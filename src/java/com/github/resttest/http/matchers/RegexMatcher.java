package com.github.resttest.http.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher extends TypeSafeMatcher<String> {

    private final String regex;

    public RegexMatcher(final String regex) {
        this.regex = regex;
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("Regular expression = \"" + regex + "\"");
    }

    @Override
    public boolean matchesSafely(final String string) {
        Matcher m = Pattern.compile(regex).matcher(string);
        if (m.find()) return true;
        return false;
    }


    // matcher classMethod you can call on this matcher class
    public static RegexMatcher regex(final Object regex) {
        return new RegexMatcher((String) regex);
    }
}