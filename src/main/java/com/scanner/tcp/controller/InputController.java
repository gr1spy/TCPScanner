package com.scanner.tcp.controller;

import com.scanner.tcp.model.input.InputParser;
import com.scanner.tcp.model.input.InputRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class InputController {

    private static final Logger log = LoggerFactory.getLogger(InputController.class);

    private String inputStr = "";
    private ConcurrentMap<String, Boolean> parsedHosts = new ConcurrentHashMap<>();

    public InputController() {
    }

    /**
     * Request input from user
     */
    public void request() {
        log.info("Waiting user input..");
        InputRequest in = new InputRequest();
        setInputStr(in.requestInput());
    }

    /**
     * Make parse user's input
     */
    public void parse() {
        log.info("Make input parsing.");
        InputParser inputParser = new InputParser();
        setParsedHosts(inputParser.parse(getInputStr()));
    }

    public String getInputStr() {
        return inputStr;
    }


    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }


    public ConcurrentMap<String, Boolean> getParsedHosts() {
        return parsedHosts;
    }

    public void setParsedHosts(ConcurrentMap<String, Boolean> parsedHosts) {
        this.parsedHosts = parsedHosts;
    }
}
