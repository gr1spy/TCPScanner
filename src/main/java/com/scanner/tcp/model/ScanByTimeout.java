package com.scanner.tcp.model;

import com.scanner.tcp.model.input.InputParser;
import com.scanner.tcp.model.input.RequestInput;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//todo добавить логгер

/**
 * Class which realized SYN TCP scanner.
 **/
public class ScanByTimeout {
    ConcurrentMap<String, Boolean> hosts;

    public ScanByTimeout(ConcurrentMap<String, Boolean> hosts) {
        this.hosts = hosts;
    }

    /**
     * Method has getting Map with hosts ips and ports and scan all of their.
     *
     * @param hostIn map with ips and ports hosts
     * @return result of scan
     */
    public Map<String, Boolean> scan(Map<String, Boolean> hostIn) {

        ExecutorService executorService = Executors.newFixedThreadPool(getCountOfThreads(hostIn));

        List<Ping> scannedHosts = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : hostIn.entrySet()) {

            Ping ping = new Ping(entry, entry.getKey().split("[:,]")[0],
                    entry.getKey().split("[:,]")[1], new Socket());

            scannedHosts.add(ping);
        }

        try {
            executorService.invokeAll(scannedHosts);
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        return hosts;
    }

    public Map<String, Boolean> getHostMap() {
        return this.hosts;
    }

    public ScanByTimeout getScanner() {
        RequestInput requestInput = new RequestInput();

        ScanByTimeout scanByTimeout = new ScanByTimeout(requestInput.requestInput().getParsedScanningHosts());

        return scanByTimeout;
    }

    /**
     * Due to the fact that the number of threads facing the map cannot exceed the size of the map, we reduce the number
     * of threads to a safe maximum.
     *
     * @param hosts
     */
    private int getCountOfThreads(Map<String, Boolean> hosts) {

        return Math.min(Integer.parseInt(hosts.entrySet().iterator().next().getKey().split("[:,]")[2]),
                hosts.size());
    }
}
