package com.scanner.tcp.model;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Class which realized SYN TCP scanner.
 **/
public class ScanByTimeout {
    ConcurrentMap<String, Boolean> hosts;

    int countOfThreads = 1;

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

        setCountOfThreads(hostIn);

        ExecutorService executorService = Executors.newFixedThreadPool(countOfThreads);

        List<Ping> scannedHosts = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : hostIn.entrySet()) {

            Ping ping = new Ping(entry, entry.getKey().split("[:,]")[0],
                    entry.getKey().split("[:,]")[1], new Socket());

            scannedHosts.add(ping);
        }

        try {
            List<Future<Boolean>> futures = executorService.invokeAll(scannedHosts);
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        return hosts;
    }

    public Map<String, Boolean> getHostMap() {
        return this.hosts;
    }


    /**
     * Due to the fact that the number of threads facing the map cannot exceed the size of the map, we reduce the number
     * of threads to a safe maximum.
     *
     * @param hosts
     */
    public void setCountOfThreads(Map<String, Boolean> hosts) {
        int inputCountOfThreads = 0;
        int maxCountOfThreads = 0;

        for (Map.Entry<String, Boolean> entry : hosts.entrySet()) {
            inputCountOfThreads = Integer.parseInt(entry.getKey().split("[:,]")[2]);
            break;
        }
        for (Map.Entry<String, Boolean> entry : hosts.entrySet()) {
            maxCountOfThreads++;
        }

        if (inputCountOfThreads > maxCountOfThreads) {
            this.countOfThreads = inputCountOfThreads -
                    (inputCountOfThreads - maxCountOfThreads);
        } else {
            this.countOfThreads = inputCountOfThreads;
        }
    }
}
