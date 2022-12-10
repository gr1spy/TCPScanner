package com.scanner.tcp;

import com.scanner.tcp.controller.Controller;
import com.scanner.tcp.model.InputParser;
import com.scanner.tcp.model.ScanByTimeout;
import com.scanner.tcp.view.OutputView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Class which realized main functionality of "scanner by timeout".
 * Based on MVC and Socket class.
 *
 * @author george - observable, Max - observer
 */
public class Main {

    public static void main(String[] args) {

        while(true) {
            ScanByTimeout scanner = getScanner();

            OutputView toConsole = new OutputView();
            Controller scanToConsole = new Controller(scanner, toConsole);

            scanToConsole.updateView();
            System.out.println("----------------");
        }
    }

    /**
     * @return scanner object
     */
    private static ScanByTimeout getScanner() {

        ScanByTimeout scanByTimeout = new ScanByTimeout(requestInput());

        return scanByTimeout;
    }

    /**
     * @return map with uniq hosts for scan. example  entry -> ("192.168.1.1:443", true)
     */
    private static ConcurrentMap<String, Boolean> requestInput() {
        InputParser request = new InputParser();
        Scanner in = new Scanner(System.in);
        System.out.println("Example: scan -h 8.8.8.8-10,95.165.154.50 -p 443-444,5000-5001 -t 10");
        System.out.println("Type to request for scanning:");
        String input = in.nextLine();

        return new ConcurrentHashMap<>(request.parse(input));
    }
}

