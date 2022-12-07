package com.scanner.tcp.view;

import java.util.Map;

/**
 * Class has realizing output result
 */
public class OutputView {

    public void displayResult(Map<String, Boolean> host) {
        System.out.println("Opened:");
        for (Map.Entry<String, Boolean> resultScan : host.entrySet()) {
            if (resultScan.getValue()) {
                System.out.println(" " + resultScan.getKey());
            }
        }
    }
}
