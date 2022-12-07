package com.scanner.tcp.controller;

import com.scanner.tcp.model.ScanByTimeout;
import com.scanner.tcp.view.OutputView;

/**
 * Class which realized functional of controller in MVC.
 */
public class ScanController {

    private ScanByTimeout model;
    private OutputView view;

    public ScanController(ScanByTimeout model, OutputView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Requesting scan for hosts and displaying our result in output.
     */
    public void updateView() {
        view.print(model.scan(model.getHostMap()));
    }
}
