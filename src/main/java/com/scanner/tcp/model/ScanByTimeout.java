package com.scanner.tcp.model;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;

/**
 * Class which realized SYN TCP scanner.
 **/
public class ScanByTimeout {
    Map<String, Boolean> hosts;

    String countOfThreads = "";

    public ScanByTimeout(Map<String, Boolean> hosts) {
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

        for (Map.Entry<String, Boolean> entry : hostIn.entrySet()) {
            hosts.put(entry.getKey(), ping(entry.getKey().split("[:,]")[0], entry.getKey().split("[:,]")[1]));
        }
        return hosts;
    }

    /**
     * Method check host port for opened or closed.
     *
     * @return <strong>true - </strong>if port is opened, and <strong>false - </strong>if closed
     */
    private Boolean ping(String ip, String port) {
        boolean isOpened = false;
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, Integer.parseInt(port)), 500);
            isOpened = socket.isConnected() && !socket.isClosed();
        } catch (Exception ignored) {
        }
        return isOpened;
    }

    public Map<String, Boolean> getHostMap() {
        return this.hosts;
    }

    public String getCountOfThreads() {
        return countOfThreads;
    }

    public void setCountOfThreads(Map<String, Boolean> hosts) {
        for (Map.Entry<String, Boolean> entry : hosts.entrySet()) {
            this.countOfThreads =  entry.getKey().split("[,:]")[2];
        }
    }
}
