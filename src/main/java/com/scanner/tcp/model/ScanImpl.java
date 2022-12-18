package com.scanner.tcp.model;

import java.util.Map;
import java.util.Set;

public interface ScanImpl {

    /**
     * Method has getting Map with hosts ips and ports and scan all of their.
     *
     * @param hostIn map with ips and ports hosts
     * @return result of scan
     */
    Map<String, Boolean> scan(Map<String, Boolean> hostIn);


    /**
     * Method allows to get hosts map for further scan.
     * @return
     */
    Map<String, Boolean> getHostsMapForScanning();

    /**
     * Due to the fact that the number of threads facing the map cannot exceed the size of the map, we reduce the number
     * of threads to a safe maximum.
     *
     * @param keySet
     */
    int getCountOfThreads(Set<String> keySet);
}
