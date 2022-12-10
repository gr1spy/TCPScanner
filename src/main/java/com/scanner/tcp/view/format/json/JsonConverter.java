package com.scanner.tcp.view.format.json;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonConverter {

    Map<String, Boolean> hosts;

    public JsonConverter(Map<String, Boolean> hosts) {
        this.hosts = hosts;
    }

    /**
     * Generate JSON format list from beginning data.
     */
    public JsonFormat convertTo() {

        List<JsonHost> hostsForPrint = new ArrayList<>();
        for (Map.Entry<String, Boolean> stringFromScanResult : hosts.entrySet()) {
            selectWayForHost(stringFromScanResult, hostsForPrint);
        }

        return new JsonFormat(hostsForPrint);
    }


    /**
     * If <strong>stringFromScanResult</strong> ("ip:port",bool) is in <strong>hostsForPrint</strong>, then added new port in hosts list. And if isn't, then created new host and add to <strong>hostsForPrint</strong>.
     */
    private void selectWayForHost(Map.Entry<String, Boolean> stringFromScanResult, List<JsonHost> hostsForPrint) {

        JsonHost checkInclude = new JsonHost();
        checkInclude.setIp(stringFromScanResult.getKey().split(":")[0]);

        if (hostsForPrint.contains(checkInclude)) {
            addPortResult(stringFromScanResult, hostsForPrint);
        } else {
            hostsForPrint.add(getNewJSONHost(stringFromScanResult));
        }
    }

    /**
     * If host with same IP was find, then we found this Host in our List and add new ports to him.
     *
     * @param ipPlusPortPlusBool string like ("ip:port",boolean)
     * @param hosts              hostsForPrint list from print() method
     */
    private void addPortResult(Map.Entry<String, Boolean> ipPlusPortPlusBool, List<JsonHost> hosts) {
        for (JsonHost h : hosts) {
            if (h.getIp().equals(ipPlusPortPlusBool.getKey().split(":")[0])) {
                if (ipPlusPortPlusBool.getValue()) {
                    h.getOpenedPorts().add(ipPlusPortPlusBool.getKey().split(":")[1]);
                } else {
                    h.getClosedPorts().add(ipPlusPortPlusBool.getKey().split(":")[1]);
                }
            }
        }
    }

    /**
     * If host with same IP wasn't find, then we create new host and add him to our host list.
     *
     * @param stringInHostsMap string like ("ip:port",boolean)
     */
    private JsonHost getNewJSONHost(Map.Entry<String, Boolean> stringInHostsMap) {
        JsonHost h = new JsonHost();
        h.setIp(stringInHostsMap.getKey().split(":")[0]);
        if (stringInHostsMap.getValue()) {
            h.getOpenedPorts().add(stringInHostsMap.getKey().split(":")[1]);
        } else {
            h.getClosedPorts().add(stringInHostsMap.getKey().split(":")[1]);
        }

        return h;
    }

}
