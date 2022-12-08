package com.scanner.tcp.model;

import java.util.*;

/**
 * Class has realizing parsing users input
 */
public class InputParser {

    public InputParser() {
    }

    /**
     * @param in users cmd input
     * @return map with uniq hosts for scan. example  entry -> ("192.168.1.1:443", true)
     */
    public Map<String, Boolean> parse(String in) {
        //todo у нас никак не учитываются треды, обработать это. надо куда то их добавить
        Map<String, Boolean> hostsForScan = new HashMap<>();

        List<String> parsedSubstrings = new ArrayList<>();
        Set<String> uniqIp = new HashSet<>();
        Set<Integer> uniqPort = new HashSet<>();
        int thread = 1;

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
                thread = Integer.parseInt(substring);
            }
        }

        for (String ipNum : uniqIp) {
            for (Integer portNum : uniqPort) {
                hostsForScan.put(ipNum + ":" + portNum, false);
            }
        }

        return hostsForScan;
    }

    /**
     * @return Set of ips or ports
     */
    public Set<String> substringSplit(String stringForSplit) {
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

        return result;
    }
}
