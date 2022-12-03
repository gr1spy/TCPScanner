package com.scanner.tcp.controller;

import com.scanner.tcp.model.ScanByTimeout;
import com.scanner.tcp.model.ScanHost;
import com.scanner.tcp.view.OutputView;

import java.util.Set;

public class ScanController {

    private ScanByTimeout model;
    private OutputView view;

    /**
     * Конструктор класса ScanController
     * @param model объект типа сканирования
     * @param view представление результата сканирования
     */
    public ScanController(ScanByTimeout model, OutputView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Начать процесс сканирования
     * @param scanHost множество хостов для сканирования
     */
    public void startScanning(Set<ScanHost> scanHost) {
        model.setScanHost(scanHost);
    }

    /**
     * Выводим информацию о результате сканирования
     */
    public void updateView() {
        view.displayResult(model.getScanHost());
    }
}
