package com.irme.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        String confidentialMarkerText = "CONFIDENTIAL";
        Marker confidentialMarker = MarkerFactory.getMarker(confidentialMarkerText);
        logger.debug("Hello world from slf4j");
        logger.debug("This logger supports confidentail messages....");
        logger.debug(confidentialMarker, "This is a confidential message....");
        logger.debug("Just logged a confidential message");
    }
}
