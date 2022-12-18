package com.scanner.tcp.model.input;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InputParserTest {

    @Test
    public void testParseFirst() {
        ConcurrentMap<String, Boolean> correctResult = new ConcurrentHashMap<>();
        correctResult.put("8.8.8.8:80,10", false);
        correctResult.put("8.8.8.8:443,10", false);
        correctResult.put("8.8.8.9:80,10", false);
        correctResult.put("8.8.8.9:443,10", false);
        correctResult.put("8.8.8.10:80,10", false);
        correctResult.put("8.8.8.10:443,10", false);

        InputParser inputParser = new InputParser();
        ConcurrentMap<String, Boolean> result = inputParser.parse("scan -h 8.8.8.8-10 -p 80,443 -t 10");

        assertThat(result, is(correctResult));

    }

    @Test
    public void testParseSecond() {
        ConcurrentMap<String, Boolean> correctResult = new ConcurrentHashMap<>();
        correctResult.put("8.8.8.8:80,9", false);
        correctResult.put("8.8.8.8:81,9", false);
        correctResult.put("8.8.8.9:80,9", false);
        correctResult.put("8.8.8.9:81,9", false);

        InputParser inputParser = new InputParser();
        ConcurrentMap<String, Boolean> result = inputParser.parse("scan -h 8.8.8.8,8.8.8.9 -p 80-81 -t 9");

        for (Map.Entry<String, Boolean> str : correctResult.entrySet()) {
            if (!result.containsKey(str.getKey())) {
                Assert.fail();
            }
        }
    }

    @Test
    public void testParseThird() {
        ConcurrentMap<String, Boolean> correctResult = new ConcurrentHashMap<>();
        correctResult.put("8.8.8.8:80,8", false);

        InputParser inputParser = new InputParser();
        ConcurrentMap<String, Boolean> result = inputParser.parse("scan -h 8.8.8.8 -p 80 -t 8");

        assertThat(result, is(correctResult));
    }

    @Test
    public void testSubstringSplitFirst() {
        Set<String> correctResult = new HashSet<>();
        correctResult.add("8.8.8.8");
        correctResult.add("8.8.8.9");
        correctResult.add("8.8.8.10");

        InputParser inputParser = new InputParser();
        Set<String> result = inputParser.substringSplit("8.8.8.8-10");

        assertThat(result, is(correctResult));

    }

    @Test
    public void testSubstringSplitSecond() {
        Set<String> correctResult = new HashSet<>();
        correctResult.add("80");
        correctResult.add("81");
        correctResult.add("82");
        correctResult.add("83");


        InputParser inputParser = new InputParser();
        Set<String> result = inputParser.substringSplit("80-83");

        for (String str : result) {
            if (!correctResult.contains(str)) {
                Assert.fail();
            }
        }
    }
}