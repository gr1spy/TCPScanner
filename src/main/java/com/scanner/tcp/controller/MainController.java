package com.scanner.tcp.controller;

import com.scanner.tcp.model.Scan;
import com.scanner.tcp.view.PrintChoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class which realized functional of controller in MVC.
 */
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    private final Scan model;
    private final PrintChoice view;

    /**
     * Enter the path to file when create PrintCoise object for write result in this file.
     * @param model scanner obj
     * @param view view obj
     */
    public MainController(Scan model, PrintChoice view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Requesting scan for hosts and displaying our result in output.
     */
    public void beginScanning() {
        log.info("Scanning is beginning..");
        view.printIn(model.scan(model.getHostsMapForScanning()));
    }
}
