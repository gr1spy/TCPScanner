package com.scanner.tcp.view;

import com.google.gson.Gson;
import com.scanner.tcp.model.hostInJSON;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class has realizing output result
 */
public class OutputView {

    private String PATH_FOR_RESULT = "";

    public OutputView() {
    }

    public OutputView(String PATH_FOR_RESULT) {
        this.PATH_FOR_RESULT = PATH_FOR_RESULT;
    }

    /**
     * Method has realizing algorithm for output way.
     *
     * @param hosts Map with all scanned hosts
     */
    public void print(Map<String, Boolean> hosts) {

        List<hostInJSON> hostsForPrint = new ArrayList<>();

        for (Map.Entry<String, Boolean> stringFromScanResult : hosts.entrySet()) {
            selectWayForHost(stringFromScanResult, hostsForPrint);
        }

        if (PATH_FOR_RESULT.isEmpty()) {
            toConsole(hostsForPrint);
        } else {
            toJSON(hostsForPrint);
        }
    }

    /**
     * If <strong>stringFromScanResult</strong> ("ip:port",bool) is in <strong>hostsForPrint</strong>, then added new port in hosts list. And if isn't, then created new host and add to <strong>hostsForPrint</strong>.
     */
    private void selectWayForHost(Map.Entry<String, Boolean> stringFromScanResult, List<hostInJSON> hostsForPrint) {

        hostInJSON checkInclude = new hostInJSON();
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
    private void addPortResult(Map.Entry<String, Boolean> ipPlusPortPlusBool, List<hostInJSON> hosts) {
        for (hostInJSON h : hosts) {
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
    private hostInJSON getNewJSONHost(Map.Entry<String, Boolean> stringInHostsMap) {
        hostInJSON h = new hostInJSON();
        h.setIp(stringInHostsMap.getKey().split(":")[0]);
        if (stringInHostsMap.getValue()) {
            h.getOpenedPorts().add(stringInHostsMap.getKey().split(":")[1]);
        } else {
            h.getClosedPorts().add(stringInHostsMap.getKey().split(":")[1]);
        }

        return h;
    }

    private void toConsole(List<hostInJSON> hosts) {
        System.out.println("Result:");
        System.out.println(new Gson().toJson(hosts));
    }

    private void toJSON(List<hostInJSON> hosts) {
        try (FileWriter writer = new FileWriter(PATH_FOR_RESULT, false)) {
            writer.write(new Gson().toJson(hosts));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}