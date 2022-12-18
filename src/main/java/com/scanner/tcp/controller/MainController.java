package com.scanner.tcp.controller;

import com.scanner.tcp.model.Scan;
import com.scanner.tcp.view.PrintChoice;

/**
 * Class which realized functional of controller in MVC.
 */
public class MainController {

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
        view.printIn(model.scan(model.getHostsMapForScanning()));
    }
}
