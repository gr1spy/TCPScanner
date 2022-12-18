package com.scanner.tcp.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.Callable;


/**
 * Class for multithreading scan by ICMP protocol.
 */
public class Ping implements Callable<Boolean> {

    private static final Logger log = LoggerFactory.getLogger(Ping.class);
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
            log.info("Trying connect to {} with {} port..",ip,port);
            socket.connect(new InetSocketAddress(ip, Integer.parseInt(port)), 500);
            isOpened = socket.isConnected() && !socket.isClosed();
        } catch (Exception e) {
            log.warn("Error connect to {} with {} port!",ip,port);
        }
        ipPlusPort.setValue(isOpened);
        return isOpened;
    }
}
