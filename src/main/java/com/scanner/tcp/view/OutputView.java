package com.scanner.tcp.view;

import com.scanner.tcp.model.ScanHost;

import java.util.List;
import java.util.Set;

/**
 * Класс, который реализует методы представления готового результата
 */
public class OutputView {
    public void displayResult(Set<ScanHost> hosts) {
        for (ScanHost host : hosts) {
            System.out.println(host);
        }
    }
}
