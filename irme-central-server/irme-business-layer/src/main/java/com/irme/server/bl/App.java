package com.irme.server.bl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static String businessFunc() {

        logger.debug("123");

        return new String("B2");
    }

    public static void main(String[] args) {
        businessFunc();
    }
}
