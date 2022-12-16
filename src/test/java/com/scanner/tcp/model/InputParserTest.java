package com.scanner.tcp.model;

import com.scanner.tcp.model.input.InputParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InputParserTest {

    @Test
    public void testParseWithDiffIP() {

        String test1 = "scan -h 8.8.8.8,87.250.250.242 -p 80,443 -t 10";

        InputParser inputParser = new InputParser();
        Map<String, Boolean> mapForChecked = new HashMap<>();

        mapForChecked.put("8.8.8.8:80,10", false);
        mapForChecked.put("8.8.8.8:443,10", false);
        mapForChecked.put("87.250.250.242:80,10", false);
        mapForChecked.put("87.250.250.242:443,10", false);

        Map<String, Boolean> result = new HashMap<>(inputParser.parse(test1));
        for (Map.Entry<String, Boolean> str : mapForChecked.entrySet()) {
            if (!result.containsKey(str.getKey())) {
                Assert.fail();
            }
        }
    }

    @Test
    public void testParseWithRangeIP() {

        String test2 = "scan -h 8.8.8.8,8.8.8.9-10 -p 80,443";

        InputParser inputParser = new InputParser();
        Map<String, Boolean> mapForChecked = new HashMap<>();
        Map<String, Boolean> result = new HashMap<>(inputParser.parse(test2));

        mapForChecked.put("8.8.8.8:80,", false);
        mapForChecked.put("8.8.8.8:443,", false);
        mapForChecked.put("8.8.8.9:80,", false);
        mapForChecked.put("8.8.8.9:443,", false);
        mapForChecked.put("8.8.8.10:80,", false);
        mapForChecked.put("8.8.8.10:443,", false);

        result.putAll(inputParser.parse(test2));

        assertThat(mapForChecked, is(result));
    }

    @Test
    public void testParseWithTwoRangesIP() {

        String test3 = "scan -h 8.8.8.8-9,8.8.8.10-11 -p 80-81,443";

        InputParser inputParser = new InputParser();
        Map<String, Boolean> mapForChecked = new HashMap<>();
        Map<String, Boolean> result = new HashMap<>(inputParser.parse(test3));

        mapForChecked.put("8.8.8.8:80,", false);
        mapForChecked.put("8.8.8.8:81,", false);
        mapForChecked.put("8.8.8.8:443,", false);
        mapForChecked.put("8.8.8.9:80,", false);
        mapForChecked.put("8.8.8.9:81,", false);
        mapForChecked.put("8.8.8.9:443,", false);
        mapForChecked.put("8.8.8.10:80,", false);
        mapForChecked.put("8.8.8.10:81,", false);
        mapForChecked.put("8.8.8.10:443,", false);
        mapForChecked.put("8.8.8.11:80,", false);
        mapForChecked.put("8.8.8.11:81,", false);
        mapForChecked.put("8.8.8.11:443,", false);

        result.putAll(inputParser.parse(test3));
        assertThat(mapForChecked, is(result));
    }

    @Test
    public void testSubstringSplitIP() {
        InputParser inputParser = new InputParser();
        Set<String> setForChecked = new HashSet<>();

        String test1 = "8.8.8.8,87.250.250.242";

        setForChecked.add("8.8.8.8");
        setForChecked.add("87.250.250.242");
        Set<String> result = inputParser.substringSplit(test1);
        assertThat(setForChecked, is(result));
    }

    @Test
    public void testSubstringSplitWithRange() {

        String test2 = "8.8.8.8-9,8.8.8.10";

        InputParser inputParser = new InputParser();
        Set<String> setForChecked = new HashSet<>();
        Set<String> result;

        setForChecked.add("8.8.8.8");
        setForChecked.add("8.8.8.9");
        setForChecked.add("8.8.8.10");
        result = inputParser.substringSplit(test2);
        assertThat(setForChecked, is(result));
    }

    @Test
    public void testSubstringSplitPort() {

        String test3 = "80,443";

        InputParser inputParser = new InputParser();
        Set<String> setForChecked = new HashSet<>();
        Set<String> result;

        setForChecked.add("80");
        setForChecked.add("443");
        result = inputParser.substringSplit(test3);
        assertThat(setForChecked, is(result));


    }

    @Test
    public void testSubstringSplitPortRange() {
        String test4 = "80-81,443";

        InputParser inputParser = new InputParser();
        Set<String> setForChecked = new HashSet<>();
        Set<String> result;

        setForChecked.add("80");
        setForChecked.add("81");
        setForChecked.add("443");
        result = inputParser.substringSplit(test4);
        assertThat(setForChecked, is(result));

    }
}