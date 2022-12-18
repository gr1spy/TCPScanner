package com.scanner.tcp.view.format.json;

import java.util.List;

public class JsonFormat {

    private final List<JsonHost> hosts;

    public JsonFormat(List<JsonHost> hosts) {
        this.hosts = hosts;
    }


    /**
     * @return list of scanned host in JSON format
     */
    public List<JsonHost> getHostsForPrint() {
        return hosts;
    }
}
