package com.example.ecommercesite;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.stringContainsInOrder;

public class test {
    @Test
    public void givenString_whenContainsGivenSubstring_thenCorrect() {
        String str = "calligraphy";
        assertThat(str, stringContainsInOrder(Arrays.asList("call", "graph")));
    }
    public static class IsPositiveInteger extends TypeSafeMatcher<Integer> {

        public void describeTo(Description description) {
            description.appendText("a positive integer");
        }

        @Factory
        public Matcher<Integer> isAPositiveInteger() {
            return new IsPositiveInteger();
        }

        @Override
        protected boolean matchesSafely(Integer integer) {
            return integer > 0;
        }


        @Test
        public void givenInteger_whenAPositiveValue_thenCorrect() {
            int num = 1;
            assertThat(num, isAPositiveInteger());
        }
    }
}
