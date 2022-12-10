package com.scanner.tcp.model;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.Callable;

public class Ping implements Callable<Boolean> {

    Socket socket;
    String ip;
    String port;
    Map.Entry<String, Boolean> ipPlusPort;

    public Ping(Map.Entry<String, Boolean> ipPlusPort, String ip, String port, Socket socket) {
        this.ipPlusPort = ipPlusPort;
        this.socket = socket;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public Boolean call() {
        boolean isOpened = false;
        try {
            socket.connect(new InetSocketAddress(ip, Integer.parseInt(port)), 500);
            isOpened = socket.isConnected() && !socket.isClosed();
        } catch (Exception ignored) {
        }
        ipPlusPort.setValue(isOpened);
        return isOpened;
    }
}
