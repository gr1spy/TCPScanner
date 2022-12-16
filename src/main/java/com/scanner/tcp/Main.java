package com.scanner.tcp;

import com.scanner.tcp.controller.Controller;
import com.scanner.tcp.model.input.Input;
import com.scanner.tcp.model.input.RequestInput;
import com.scanner.tcp.model.ScanByTimeout;
import com.scanner.tcp.view.OutputView;

/**
 * Class which realized main functionality of "scanner by timeout".
 * Based on MVC and Socket class.
 *
 * @author george - observable, Max - observer
 */
public class Main {

    public static void main(String[] args) {

        while (true) {
            Input input = new Input();
            input.request();
            input.parse();

            OutputView toConsole = new OutputView();
            Controller scanToConsole = new Controller(new ScanByTimeout(input.getParsedScanningHosts()), toConsole);

            scanToConsole.updateView();
            System.out.println("----------------");
        }
    }
}

