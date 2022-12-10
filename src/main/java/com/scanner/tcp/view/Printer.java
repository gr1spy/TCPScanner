package com.scanner.tcp.view;

import com.google.gson.Gson;
import com.scanner.tcp.view.format.json.JsonFormat;

import java.io.FileWriter;
import java.io.IOException;

public class Printer {

    public void toConsole(JsonFormat json) {
        System.out.println("Result:");
        System.out.println(new Gson().toJson(json.getHostsForPrint()));
    }

    public void toJSON(String path, JsonFormat json) {
        try (FileWriter writer = new FileWriter(path, false)) {
            writer.write(new Gson().toJson(json.getHostsForPrint()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
