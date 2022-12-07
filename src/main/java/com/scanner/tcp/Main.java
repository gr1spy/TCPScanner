package com.scanner.tcp;

import com.scanner.tcp.controller.ScanController;
import com.scanner.tcp.model.InputParser;
import com.scanner.tcp.model.ScanByTimeout;
import com.scanner.tcp.view.OutputView;

//todo сделать многопоточку
/**
 * Class which realized main functionality of "scanner by timeout".
 * Based on MVC and Socket class.
 * @author george - observable, Max - observer
 */
public class Main {

    public static void main(String[] args) {

        InputParser hostsForScanning = new InputParser();

        ScanByTimeout scanner = getScanner(hostsForScanning);

        OutputView printToConsole = new OutputView();

        ScanController controller = new ScanController(scanner, printToConsole);

        controller.updateView();

    }

    /**
     *
     * @param in object for request user input
     * @return scanner object
     */
    private static ScanByTimeout getScanner(InputParser in) {

        ScanByTimeout scanByTimeout = new ScanByTimeout(in.requestInput());

        return scanByTimeout;
    }
}
