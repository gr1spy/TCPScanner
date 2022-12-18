package com.scanner.tcp.view.format.json;

import com.scanner.tcp.view.format.json.JsonFormat;
import com.scanner.tcp.view.format.json.JsonHost;

import java.util.List;
import java.util.Map;

public interface JsonConverterImpl {

    /**
     * Generate JSON format list from beginning data.
     */
    JsonFormat convertTo();

    /**
     * If <strong>stringFromScanResult</strong> ("ip:port",bool) is in <strong>hostsForPrint</strong>, then added new port in hosts list. And if isn't, then created new host and add to <strong>hostsForPrint</strong>.
     */
    void selectWayForHost(Map.Entry<String, Boolean> stringFromScanResult, List<JsonHost> hostsForPrint);

    /**
     * If host with same IP was find, then we found this Host in our List and add new ports to him.
     *
     * @param ipPlusPortPlusBool string like ("ip:port",boolean)
     * @param hosts              hostsForPrint list from print() method
     */
    void addPortResult(Map.Entry<String, Boolean> ipPlusPortPlusBool, List<JsonHost> hosts);

    /**
     * If host with same IP wasn't find, then we create new host and add him to our host list.
     *
     * @param stringInHostsMap string like ("ip:port",boolean)
     */
    JsonHost getNewJSONHost(Map.Entry<String, Boolean> stringInHostsMap);
}
