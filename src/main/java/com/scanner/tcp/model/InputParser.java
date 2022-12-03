package com.scanner.tcp.model;

import java.math.MathContext;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс, реализующий парсинг пользовательского ввода
 */
public class InputParser {
    //Строка пользовательского ввода
    private String input;
    private final Set<ScanHost> hosts = new HashSet<>();

    public InputParser() {
    }

    public void parsing() {


        Set<String> ips = new HashSet<>(); //айпишники хостов
        Set<Integer> ports = new HashSet<>(); //сканируемые порты

        Pattern h = Pattern.compile("-h\\s[0-9|.,-]+");
        Pattern ip = Pattern.compile("([0-9]{1,3}\\.){3}[0-9]{1,3}");
        Pattern rangeOfIps = Pattern.compile("([0-9]{1,3}\\.){3}[0-9]{1,3}-[0-9]{1,3}");

        Pattern p = Pattern.compile("-p\\s[0-9|.,-]+");
        Pattern port = Pattern.compile("[0-9]{1,5}");

        Pattern range = Pattern.compile("[0-9]{1,5}-[0-9]{1,5}");

        Matcher hMatch = h.matcher(this.input);
        //парсим айпишники
        while (hMatch.find()) {
            Matcher ipMatch = ip.matcher(hMatch.group());   //создаем матчер на основе строки из айпишников
            Matcher rangeIpMatch = rangeOfIps.matcher(hMatch.group()); //создаем матчер на диапазон ip

            while (ipMatch.find()) {
                ips.add(ipMatch.group());
            }
            while (rangeIpMatch.find()) {
                Pattern threeByteOfIp = Pattern.compile("([0-9]{1,3}\\.){3}");  //первые 3 байта айпишника
                String str = rangeIpMatch.group();  //айпишник+диапазон

                Matcher inclusionIp = threeByteOfIp.matcher(str); //создали матчер для поиска ip
                inclusionIp.find();
                String ipStr = inclusionIp.group(); //получили 3ч байтовый ip из диапазона

                Matcher rangeMatch = range.matcher(str); //создали матчер для поиска диапазона
                rangeMatch.find();
                String rangeStr = rangeMatch.group();

                List<String> minAndMaxIps = new ArrayList<>(); //первое значение - минимальный айпишник, второе - максимальный

                for (String s : rangeStr.split("-")) {
                    minAndMaxIps.add(s);
                }

                for (int i = Integer.parseInt(minAndMaxIps.get(0)); i <= Integer.parseInt(minAndMaxIps.get(1)); i++) {
                    ips.add(ipStr + Integer.toString(i));
                }
            }
        }

        Matcher pMatch = p.matcher(this.input);
        //парсим порты
        while (pMatch.find()) {
            Matcher portMatch = port.matcher(pMatch.group());   //создаем матчер на основе строки из портов
            Matcher rangePortMatch = range.matcher(pMatch.group()); //создаем матчер на диапазон портов

            while (portMatch.find()) {
                ports.add(Integer.parseInt(portMatch.group()));
            }
            while (rangePortMatch.find()) {
                List<Integer> minAndMaxPorts = new ArrayList<>(); //первое значение - начальный порт, второе - крайний

                for (String s : rangePortMatch.group().split("-")) {
                    minAndMaxPorts.add(Integer.parseInt(s));
                }

                for (int i = minAndMaxPorts.get(0); i < minAndMaxPorts.get(1); i++) {
                    ports.add(i);
                }
            }

        }

        for (String str : ips) {
            hosts.add(new ScanHost(str, ports));
        }
    }

    public void requestInput() {
        Scanner in = new Scanner(System.in);
        System.out.println("Type to request for scanning:");
//        this.input = in.nextLine();
        this.input = "scan -h 87.250.250.242-250,95.165.154.50,8.8.8.8 -p 80,443,5000";
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
