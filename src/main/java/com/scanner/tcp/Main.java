package com.scanner.tcp;

import com.scanner.tcp.controller.MainController;
import com.scanner.tcp.controller.InputController;
import com.scanner.tcp.model.Scan;
import com.scanner.tcp.view.PrintChoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class which realized main functionality of "scanner by timeout".
 * Based on MVC and Socket class.
 *
 * @author george - observable, Max - observer
 */
public class Main {

    public static void main(String[] args) {

        while (true) {
            InputController in = new InputController();
            in.request();
            in.parse();

            MainController scanToConsole = new MainController(new Scan(in.getParsedHosts()), new PrintChoice());

            scanToConsole.beginScanning();

            System.out.println("\n[ ==== New Scan ==== ]");
        }
    }
}

