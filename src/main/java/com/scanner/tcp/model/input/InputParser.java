package com.scanner.tcp.model.input;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Class has realizing parsing users input
 */
public class InputParser {
    private static final Logger log = LoggerFactory.getLogger(InputParser.class);

    public InputParser() {
    }

    /**
     * @param in users cmd input
     * @return map with uniq hosts for scan. example  entry -> ("192.168.1.1:443,10", true)
     */
    public ConcurrentMap<String, Boolean> parse(String in) {
        ConcurrentMap<String, Boolean> hostsForScan = new ConcurrentHashMap<>();

        List<String> parsedSubstrings = new ArrayList<>();
        Set<String> uniqIp = new HashSet<>();
        Set<Integer> uniqPort = new HashSet<>();
        String countOfThreads = "";

        int checkThatItIsNotBegin = 0;
        for (String s : in.split("\s-[hpt]\s")) {
            if (checkThatItIsNotBegin > 0) {
                parsedSubstrings.add(s);
            }
            checkThatItIsNotBegin++;
        }

        int iteratorBySubstrings = 0;
        for (String substring : parsedSubstrings) {
            if (iteratorBySubstrings == 0) {
                Set<String> ips = substringSplit(substring);
                uniqIp.addAll(ips);
                iteratorBySubstrings++;
            } else if (iteratorBySubstrings == 1) {
                Set<String> ports = substringSplit(substring);
                for (String numPort : ports) {
                    uniqPort.add(Integer.parseInt(numPort));
                }
                iteratorBySubstrings++;
            } else if (iteratorBySubstrings == 2) {
                countOfThreads = substring;
            }
        }

        for (String ipNum : uniqIp) {
            for (Integer portNum : uniqPort) {
                hostsForScan.put(ipNum + ":" + portNum + "," + countOfThreads, false);
            }
        }

        log.info("Input parsing complete");
        return hostsForScan;
    }

    /**
     * @param stringForSplit "8.8.8.8-10"
     * @return ("8.8.8.8","8.8.8.9","8.8.8.10")
     */
    public Set<String> substringSplit(String stringForSplit) {
        log.info("Found substring with range: {}",stringForSplit);
        Set<String> bufResult = new HashSet<>();
        List<String> incorrectCount = new ArrayList<>();

        Set<String> result = new HashSet<>(Arrays.asList(stringForSplit.split(",")));

        for (String substring : result) {
            if (substring.contains("-")) {
                incorrectCount.add(substring);
                if (substring.contains(".")) {
                    List<String> ipWithRange = new ArrayList<>(Arrays.asList(substring.split("\\.")));
                    List<String> minAndMaxIp = new ArrayList<>(Arrays.asList(ipWithRange.get(3).split("-")));
                    StringBuilder threeByteIp = new StringBuilder();
                    for (int i = 0; i < 3; i++) {
                        threeByteIp.append(ipWithRange.get(i));
                        threeByteIp.append(".");
                    }

                    for (int i = Integer.parseInt(minAndMaxIp.get(0)); i <= Integer.parseInt(minAndMaxIp.get(1)); i++) {
                        bufResult.add(threeByteIp + Integer.toString(i));
                    }

                } else {
                    List<String> minAndMaxPorts = new ArrayList<>(Arrays.asList(substring.split("-")));
                    for (int i = Integer.parseInt(minAndMaxPorts.get(0)); i <= Integer.parseInt(minAndMaxPorts.get(1)); i++) {
                        bufResult.add(Integer.toString(i));
                    }
                }
            }
        }

        result.addAll(bufResult);

        for (String count : incorrectCount) {
            result.remove(count);
        }

        log.info("Substring parsing complete!");
        return result;
    }
}
