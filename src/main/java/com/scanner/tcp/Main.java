package com.scanner.tcp;

import com.scanner.tcp.controller.ScanController;
import com.scanner.tcp.model.InputParser;
import com.scanner.tcp.model.ScanByTimeout;
import com.scanner.tcp.view.OutputView;

/**
 * Класс, реализующий основной функционал ПО, а также работу с пользовательским вводом/выводом
 */
public class Main {

    public static void main(String[] args) {

        //Создаем сканер и передаем в него параметры сканирования
        ScanByTimeout scanner = retrieveTimeoutScanner();

        //Создаем объект для вывода информации в консоль
        OutputView printToConsole = new OutputView();

        //Создаем объект контроллера и передаем в него сканер с отсканируемыми объектами и печатаем это в консоль
        ScanController controller = new ScanController(scanner, printToConsole);

        //Начинаем выполнять программу
        controller.updateView();

    }

    /**
     * @return Возвращаем объект сканера с отсканируемым хостом
     */
    private static ScanByTimeout retrieveTimeoutScanner() {
        InputParser in = new InputParser();
        in.requestInput();

        ScanByTimeout scanByTimeout = new ScanByTimeout();
        scanByTimeout.setScanHost(in.getHosts());

        return scanByTimeout;
    }
}
