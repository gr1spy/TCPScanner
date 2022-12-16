package com.scanner.tcp.view;

import com.scanner.tcp.view.format.json.JsonFormat;

public interface PrinterImpl {

    void toConsole(JsonFormat json);
    void toJSON(String path, JsonFormat json);
}
