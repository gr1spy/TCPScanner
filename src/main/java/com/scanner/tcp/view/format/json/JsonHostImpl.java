package com.scanner.tcp.view.format.json;

import java.util.List;

public interface JsonHostImpl {
    String getIp();

    void setIp(String ip);

    List<String> getOpenedPorts();

    List<String> getClosedPorts();
}
