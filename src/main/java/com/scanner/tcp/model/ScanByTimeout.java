package com.scanner.tcp.model;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Set;

/**
 * Класс, который реализует сканнер: Half-open SYN flag
 **/
public class ScanByTimeout {
    Set<ScanHost> hosts;

    public ScanByTimeout(Set<ScanHost> hosts) {
        this.hosts = hosts;
    }

    public ScanByTimeout() {
    }

    /**
     * Основной метод сканирования
     */
    public void scanning() {
        //todo https://www.youtube.com/watch?v=fzqTdd0RPVU&list=FLrJrMAPrWPXqwNzjZBZMLGA
        for (ScanHost host : hosts) {
            for (Integer port : host.getScanPorts()) {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(host.getIpAddr(), port), 500);
                    if (socket.isConnected() && !socket.isClosed()) {
                        host.getPortMap().put(port, true);
                    } else {
                        host.getPortMap().put(port, false);
                    }
                } catch (Exception e){
                    host.getPortMap().put(port, false);
                }
            }
        }
    }

    public Set<ScanHost> getScanHost() {
        return hosts;
    }

    public void setScanHost(Set<ScanHost> hosts) {
        this.hosts = hosts;
        scanning();
    }
}
