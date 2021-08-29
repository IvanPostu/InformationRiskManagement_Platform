package com.irme.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class AppTest {
    private final Logger logger = LoggerFactory.getLogger(AppTest.class);


    @Test
    public void shouldAnswerWithTrue() {
        logger.info("Hello");

        Assertions.assertTrue(true);
    }
}
