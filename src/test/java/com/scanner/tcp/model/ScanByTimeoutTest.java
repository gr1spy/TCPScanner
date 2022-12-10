package com.scanner.tcp.model;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class ScanByTimeoutTest {

    @Test
    public void scan() {

        ScanByTimeout scanner = new ScanByTimeout(new ConcurrentHashMap<>());

        Map<String, Boolean> correctMap = new HashMap<>();
        Map<String, Boolean> checkMap = new HashMap<>();

        correctMap.put("8.8.8.8:443,", true);
        correctMap.put("8.8.8.8:5000,", false);

        checkMap.put("8.8.8.8:443,", false);
        checkMap.put("8.8.8.8:5000,", false);

        Map<String, Boolean> result = new HashMap<>(scanner.scan(checkMap));

        assertThat(correctMap, is(result));
    }
}