package com.scanner.tcp;

import com.scanner.tcp.controller.Controller;
import com.scanner.tcp.model.InputParser;
import com.scanner.tcp.model.ScanByTimeout;
import com.scanner.tcp.view.OutputView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//todo сделать многопоточку

/**
 * Class which realized main functionality of "scanner by timeout".
 * Based on MVC and Socket class.
 *
 * @author george - observable, Max - observer
 */
public class Main {

    public static void main(String[] args) {

        ScanByTimeout scanner = getScanner();

        OutputView toConsole = new OutputView();
        Controller scanToConsole = new Controller(scanner, toConsole);

        OutputView toJSON = new OutputView("src/main/resources/ScanResult.json");
        Controller scanToJSON = new Controller(scanner, toJSON);

        scanToConsole.updateView();
        scanToJSON.updateView();

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
    private static Map<String, Boolean> requestInput() {
        InputParser hostsForScanning = new InputParser();
        Scanner in = new Scanner(System.in);
        System.out.println("Example: scan -h 8.8.8.8-10 -p 443-444");
        System.out.println("Type to request for scanning:");
        String input = in.nextLine();

        return new HashMap<>(hostsForScanning.parse(input));
    }
}

