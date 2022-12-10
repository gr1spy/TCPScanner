package com.scanner.tcp.view.format.json;

import java.util.ArrayList;
import java.util.List;

public class JsonFormat {

    private final List<JsonHost> hosts;

    public JsonFormat(List<JsonHost> hosts) {
        this.hosts = hosts;
    }

    public List<JsonHost> getHostsForPrint() {
        return hosts;
    }
}
