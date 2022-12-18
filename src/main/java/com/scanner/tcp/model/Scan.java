package com.scanner.tcp.model;

import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// todo добавить логгер

/**
 * Class which realized SYN TCP scanner.
 **/
public class Scan implements ScanImpl {
    ConcurrentMap<String, Boolean> hosts;

    public Scan(ConcurrentMap<String, Boolean> hosts) {
        this.hosts = hosts;
    }

    @Override
    public Map<String, Boolean> scan(Map<String, Boolean> hostIn) {

        ExecutorService executorService = Executors.newFixedThreadPool(getCountOfThreads(hostIn.keySet()));

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

    @Override
    public Map<String, Boolean> getHostsMapForScanning() {
        return this.hosts;
    }

    @Override
    public int getCountOfThreads(Set<String> entrySet) {
        return Math.min(Integer.parseInt(entrySet.iterator().next().split("[:,]")[2]),
                hosts.size());
    }
}
