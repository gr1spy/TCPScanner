package com.scanner.tcp.model;

import java.util.Objects;
import java.util.Set;

public class Host {
    String ip;
    Integer port;

    public Host(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Host host = (Host) o;
        return ip.equals(host.ip) && port.equals(host.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, port);
    }

}
