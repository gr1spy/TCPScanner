package com.scanner.tcp.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class ScanTest {

    @Test
    public void testScan() {
        ConcurrentMap<String, Boolean> hostsForScan = new ConcurrentHashMap<>();
        hostsForScan.put("8.8.8.8:443,10", false);
        hostsForScan.put("8.8.8.8:5000,10", false);

        Map<String, Boolean> correctResult = new HashMap<>();
        correctResult.put("8.8.8.8:443,10", true);
        correctResult.put("8.8.8.8:5000,10", false);

        Scan scanner = new Scan(hostsForScan);

        Map<String, Boolean> result = scanner.scan(hostsForScan);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertThat(result, is(correctResult));
    }

    @Test
    public void testGetCountOfThreads() {
        Set<String> strForCheck = new HashSet<>();
        strForCheck.add("192.168.1.1:80,10");

        ConcurrentMap<String, Boolean> mapForConstructor = new ConcurrentHashMap<>();
        mapForConstructor.put("1", true);
        mapForConstructor.put("2", true);

        Scan scan = new Scan(mapForConstructor);

        Assert.assertEquals(2, scan.getCountOfThreads(strForCheck));

    }
}