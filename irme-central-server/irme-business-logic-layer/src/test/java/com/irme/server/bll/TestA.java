package com.irme.server.bll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestA {
    private final Logger logger = LoggerFactory.getLogger(TestA.class);

    @Test
    public void aTest() {
        logger.info("Hello");
        Assertions.assertEquals(22, 22);
    }
}
