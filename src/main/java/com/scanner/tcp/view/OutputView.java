package com.scanner.tcp.view;

import com.scanner.tcp.view.format.json.JsonConverter;

import java.util.Map;

/**
 * Class has realizing output result
 */
public class OutputView {

    private String PATH_FOR_RESULT = "";

    public OutputView() {
    }

    public OutputView(String PATH_FOR_RESULT) {
        this.PATH_FOR_RESULT = PATH_FOR_RESULT;
    }

    /**
     * Method has realizing algorithm for output way.
     *
     * @param hosts Map with all scanned hosts
     */
    public void onDraw(Map<String, Boolean> hosts) {

        JsonConverter converter = new JsonConverter(hosts);
        Printer printer = new Printer();

        if (PATH_FOR_RESULT.isEmpty()) {
            printer.toConsole(converter.convertTo());
        } else {
            printer.toJSON(PATH_FOR_RESULT, converter.convertTo());
        }
    }
}