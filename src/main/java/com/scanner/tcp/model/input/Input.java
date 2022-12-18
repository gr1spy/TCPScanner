package com.scanner.tcp.model.input;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Input implements RequestImp, ParseImpl {

    private String inputStr = "";
    private ConcurrentMap<String, Boolean> parsedScanningHosts = new ConcurrentHashMap<>();

    public Input() {
    }

    @Override
    public void request() {
        InputRequest requestInput = new InputRequest();
        setInputStr(requestInput.requestInput());
    }

    @Override
    public void parse() {
        InputParser inputParser = new InputParser();
        setParsedScanningHosts(inputParser.parse(getInputStr())); ;
    }

    public String getInputStr() {
        return inputStr;
    }

    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }

    public ConcurrentMap<String, Boolean> getParsedScanningHosts() {
        return parsedScanningHosts;
    }

    public void setParsedScanningHosts(ConcurrentMap<String, Boolean> parsedScanningHosts) {
        this.parsedScanningHosts = parsedScanningHosts;
    }
}
