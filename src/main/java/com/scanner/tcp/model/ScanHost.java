package com.scanner.tcp.model;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Класс, описывающий сканируемый хост
 */
public class ScanHost {
    //IP адрес сканируемого хоста
    private String ipAddr;
    //включает в себя набор портов и их значения: открыт/закрыт
    private Map<Integer, Boolean> portMap = new HashMap<>();
    //список сканируемых портов
    private Set<Integer> scanPorts = new HashSet<>();

    public ScanHost(String ipAddr, Set<Integer> scanPorts) {
        this.ipAddr = ipAddr;
        this.scanPorts = scanPorts;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public Map<Integer, Boolean> getPortMap() {
        return portMap;
    }

    public void setPortMap(Map<Integer, Boolean> portMap) {
        this.portMap = portMap;
    }

    public Set<Integer> getScanPorts() {
        return scanPorts;
    }

    public void setScanPorts(Set<Integer> scanPorts) {
        this.scanPorts = scanPorts;
    }


    /**
     * Map to String by Apache
     * @param map передаем мапу, которую хотим перевести в стрингу
     * @return возвращаем красивый стринг
     */
    public String convertWithApache(Map map) {
        return StringUtils.join(map);
    }

    @Override
    public String toString() {
        return "ip=" + ipAddr +
                ",\n " + convertWithApache(portMap);
    }
}
