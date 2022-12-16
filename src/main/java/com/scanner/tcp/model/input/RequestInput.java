package com.scanner.tcp.model.input;

import com.scanner.tcp.model.input.InputParser;

import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RequestInput {

    public RequestInput() {}

    /**
     * @return map with uniq hosts for scan. example  entry -> ("192.168.1.1:443", true)
     */
    public Input requestInput() {

        Input input = new Input();

        Scanner scan = new Scanner(System.in);
        System.out.println("Example: scan -h 8.8.8.8-10,95.165.154.50 -p 443-444,5000-5001 -t 10");
        System.out.println("Type to request for scanning:");
        String str = scan.nextLine();

        input.setInputStr(str);

        return input;
    }
}
