package com.scanner.tcp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class hostInJSON {

    String ip;
    List<String> openedPorts = new ArrayList<>();
    List<String> closedPorts = new ArrayList<>();

    public hostInJSON() {}

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<String> getOpenedPorts() {
        return openedPorts;
    }

    public List<String> getClosedPorts() {
        return closedPorts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        hostInJSON that = (hostInJSON) o;
        return ip.equals(that.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}
