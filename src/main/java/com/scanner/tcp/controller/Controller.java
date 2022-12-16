package com.scanner.tcp.controller;

import com.scanner.tcp.model.ScanByTimeout;
import com.scanner.tcp.view.OutputView;

/**
 * Class which realized functional of controller in MVC.
 */
public class Controller implements ControllerImpl {

    private final ScanByTimeout model;
    private final OutputView view;

    public Controller(ScanByTimeout model, OutputView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void updateView() {
        view.onDraw(model.scan(model.getHostMap()));
    }
}
