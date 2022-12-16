package com.scanner.tcp.view;

import java.util.Map;

public interface OutputViewImpl {

    /**
     * Method has realizing algorithm for output way.
     *
     * @param hosts Map with all scanned hosts
     */
    void onDraw(Map<String, Boolean> hosts);
}
