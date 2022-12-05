package com.scanner.tcp.model;

import java.math.MathContext;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс, реализующий парсинг пользовательского ввода
 */
public class InputParser {

    private String input;
    private final Set<ScanHost> hosts = new HashSet<>();

    public InputParser() {
    }


    public void parsing() {

        List<String> parsedSubstrings = new ArrayList<>();
        Set<String> ip = new HashSet<>();
        Set<Integer> port = new HashSet<>();
        int thread = 1;

        int checkThatItIsNotBegin = 0;
        for (String s : this.input.split("\s-[hpt]\s")) {
            if (checkThatItIsNotBegin > 0) {
                parsedSubstrings.add(s);
            }
            checkThatItIsNotBegin++;
        }

        int iteratorBySubstrings = 0;
        for (String substring : parsedSubstrings) {
            if (iteratorBySubstrings == 0) {
                Set<String> ips = substringSplit(substring);
                ip.addAll(ips);
                iteratorBySubstrings++;
            } else if (iteratorBySubstrings == 1) {
                Set<String> ports = substringSplit(substring);
                for (String numPort : ports) {
                    port.add(Integer.parseInt(numPort));
                }
                iteratorBySubstrings++;
            } else if (iteratorBySubstrings == 2) {
                thread = Integer.parseInt(substring);
            }
        }

        for (String ipNum : ip) {
            hosts.add(new ScanHost(ipNum, port));
        }

    }

    /**
     * Получаем множество распарсенных строк ip or ports
     *
     * @param stringForSplit входная строка
     * @return множество ip or ports
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

    public void requestInput() {
        Scanner in = new Scanner(System.in);
        System.out.println("Type to request for scanning:");
//        this.input = in.nextLine();
        this.input = "scan -h 95.165.154.50,8.8.8.8-10 -p 80,443-444,5000-5003";
        parsing();
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }


    public Set<ScanHost> getHosts() {
        return hosts;
    }
}
