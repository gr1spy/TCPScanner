package com.scanner.tcp.view.format.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonConverter implements JsonConverterImpl {

    Map<String, Boolean> hosts;

    public JsonConverter(Map<String, Boolean> hosts) {
        this.hosts = hosts;
    }

    @Override
    public JsonFormat convertTo() {

        List<JsonHost> hostsForPrint = new ArrayList<>();
        for (Map.Entry<String, Boolean> stringFromScanResult : hosts.entrySet()) {
            selectWayForHost(stringFromScanResult, hostsForPrint);
        }

        return new JsonFormat(hostsForPrint);
    }

    @Override
    public void selectWayForHost(Map.Entry<String, Boolean> stringFromScanResult, List<JsonHost> hostsForPrint) {

        JsonHost checkInclude = new JsonHost();
        checkInclude.setIp(stringFromScanResult.getKey().split("[:,]")[0]);

        if (hostsForPrint.contains(checkInclude)) {
            addPortResult(stringFromScanResult, hostsForPrint);
        } else {
            hostsForPrint.add(getNewJSONHost(stringFromScanResult));
        }
    }

    @Override
    public void addPortResult(Map.Entry<String, Boolean> ipPlusPortPlusBool, List<JsonHost> hosts) {
        for (JsonHost h : hosts) {
            if (h.getIp().equals(ipPlusPortPlusBool.getKey().split("[:,]")[0])) {
                if (ipPlusPortPlusBool.getValue()) {
                    h.getOpenedPorts().add(ipPlusPortPlusBool.getKey().split("[:,]")[1]);
                } else {
                    h.getClosedPorts().add(ipPlusPortPlusBool.getKey().split("[:,]")[1]);
                }
            }
        }
    }

    @Override
    public JsonHost getNewJSONHost(Map.Entry<String, Boolean> stringInHostsMap) {
        JsonHost h = new JsonHost();
        h.setIp(stringInHostsMap.getKey().split("[:,]")[0]);
        if (stringInHostsMap.getValue()) {
            h.getOpenedPorts().add(stringInHostsMap.getKey().split("[:,]")[1]);
        } else {
            h.getClosedPorts().add(stringInHostsMap.getKey().split("[:,]")[1]);
        }

        return h;
    }

}
