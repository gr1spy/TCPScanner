package com.scanner.tcp.view;

import com.google.gson.Gson;
import com.scanner.tcp.view.format.json.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;

public class Printer implements PrinterImpl {
    private static final Logger log = LoggerFactory.getLogger(Printer.class);

    @Override
    public void toConsole(JsonFormat json) {
        log.info("Make print to console.");
        System.out.println("Result:");
        System.out.println(new Gson().toJson(json.getHostsForPrint()));
    }

    @Override
    public void toJSON(String path, JsonFormat json) {
        log.info("Make print to Json file.");
        try (FileWriter writer = new FileWriter(path, false)) {
            writer.write(new Gson().toJson(json.getHostsForPrint()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
